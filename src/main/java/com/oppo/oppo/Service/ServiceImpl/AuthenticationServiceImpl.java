package com.oppo.oppo.Service.ServiceImpl;

import com.oppo.oppo.DAO.InvalidatedTokenRepository;
import com.oppo.oppo.DAO.RoleRepository;
import com.oppo.oppo.DAO.UsersRepository;
import com.oppo.oppo.DTO.Request.AuthenticationRequest;
import com.oppo.oppo.DTO.Request.IntrospectRequest;
import com.oppo.oppo.DTO.Request.LogoutRequest;
import com.oppo.oppo.DTO.Request.RefreshRequest;
import com.oppo.oppo.DTO.Response.AuthenticationResponse;
import com.oppo.oppo.DTO.Response.IntrospectResponse;
import com.oppo.oppo.Entities.InvalidatedToken;
import com.oppo.oppo.Entities.User_Role;
import com.oppo.oppo.Entities.Users;
import com.oppo.oppo.Exception.AppException;
import com.oppo.oppo.Exception.ErrorCode;
import com.oppo.oppo.Service.AuthenticationService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private InvalidatedTokenRepository invalidatedTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGN_KEY="ZcCAeQKy0ly5bPdCVHM8bLQp5KJX9eczTqZl7zhjzK42U7SXfALZcWpSjitA5tLX";

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        boolean isValid = true;

        try {
            verifyToken(token, false);
        } catch (AppException e) {
            isValid = false;
        }

        return IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = usersRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        boolean authenticated =  passwordEncoder.matches(request.getPassword(), user.getPassword());

        if(!authenticated)
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        // login success -> create token
        var token = generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();

    }

    @Override
    public AuthenticationResponse authenticateGoogle(Map<String, String> body) {
        String tokenRequest = body.get("token");
        String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + tokenRequest;

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        Map<String, Object> userInfo = response.getBody();

        Users user = new Users();
        Optional<Users> userOptional = usersRepository.findByUsername((String) userInfo.get("email"));
        //neu khong ton tai thi them moi user
        if(!userOptional.isPresent()){
            user = Users.builder()
                    .username((String) userInfo.get("email"))
                    .phone(null)
                    .password(null)
                    .fullName((String) userInfo.get("name"))
                    .avatarUrl((String) userInfo.get("picture"))
                    .email((String) userInfo.get("email"))
                    .build();

            //add role User
            Set<User_Role> user_roles = new HashSet<>();
            User_Role user_role = new User_Role();
            user_role.setRole(roleRepository.findByRoleName("User"));
            user_role.setUser(user);
            user_roles.add(user_role);
            user.setUser_roles(user_roles);
            usersRepository.save(user);
        }else {
            user = userOptional.get();
        }

        // login success -> create token
        var token = generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();

    }


    @Override
    public String generateToken(Users user){
        // header : xac dinh thuat toan ma hoa cho jwt
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        //tao body cho payload JWTClaimsSet
        JWTClaimsSet jwtClaimsSet =  new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("oppo.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli() // het han sau VALID_DURATION giây
                ))
                .jwtID(UUID.randomUUID().toString())  //token ID
//                .claim("customClaim", "custom") //custom claim
                .claim("scope", buildScope(user)) // để spring security biết role thì cần claim có scope trong jwt
                .claim("name", user.getFullName())
                .claim("avatarUrl", user.getAvatarUrl())
                .build();

        //tao payload
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header,payload);

        //signature
        try {
            jwsObject.sign(new MACSigner(SIGN_KEY.getBytes()));
            return jwsObject.serialize();
        }catch (JOSEException e){
            log.error("Cannot create token",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void logout(LogoutRequest request) throws JOSEException, ParseException {
        try {
            var signToken = verifyToken(request.getToken(), true);

            String jit = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedToken =
                    InvalidatedToken.builder().id(jit).expiryTime(expiryTime).build();

            invalidatedTokenRepository.save(invalidatedToken);
        } catch (AppException exception){
            log.info("Token already expired");
        }
    }

    public AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        var signedJWT = verifyToken(request.getToken(), true);

        var jit = signedJWT.getJWTClaimsSet().getJWTID();
        var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken =
                InvalidatedToken.builder()
                        .id(jit)
                        .expiryTime(expiryTime).build();

        invalidatedTokenRepository.save(invalidatedToken);

        var username = signedJWT.getJWTClaimsSet().getSubject();

        var user =
                usersRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        var token = generateToken(user);

        return AuthenticationResponse.builder().token(token).authenticated(true).build();
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {

        JWSVerifier verifier = new MACVerifier(SIGN_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        var verified = signedJWT.verify(verifier);

        //check thoi han cua token
        Date expiryTime = (isRefresh)
                ? new Date(signedJWT.getJWTClaimsSet().getIssueTime()
                    .toInstant().plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS).toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        if (!(verified && expiryTime.after(new Date()))) throw new AppException(ErrorCode.UNAUTHENTICATED);

        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        return signedJWT;
    }

    private String buildScope(Users user) {
        StringJoiner stringJoiner = new StringJoiner(" ");

        if (!CollectionUtils.isEmpty(user.getUser_roles()))
            user.getUser_roles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getRole().getRoleName());
            });

        return stringJoiner.toString();
    }
}
