package com.oppo.oppo.Service;

import com.oppo.oppo.DTO.Request.AuthenticationRequest;
import com.oppo.oppo.DTO.Request.IntrospectRequest;
import com.oppo.oppo.DTO.Request.LogoutRequest;
import com.oppo.oppo.DTO.Request.RefreshRequest;
import com.oppo.oppo.DTO.Response.AuthenticationResponse;
import com.oppo.oppo.DTO.Response.IntrospectResponse;
import com.oppo.oppo.Entities.Users;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;
import java.util.Map;

public interface AuthenticationService {

    //verify token
    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;

    //check username, password -> generate token
    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticationResponse authenticateGoogle(Map<String,String> body);

    public String generateToken(Users user);

    public void logout(LogoutRequest request) throws JOSEException, ParseException;

    public AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;
}
