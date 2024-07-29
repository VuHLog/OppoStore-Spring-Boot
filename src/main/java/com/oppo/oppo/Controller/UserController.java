package com.oppo.oppo.Controller;

import com.oppo.oppo.DTO.Request.UserCreationRequest;
import com.oppo.oppo.DTO.Request.UserUpdateRequest;
import com.oppo.oppo.DTO.Response.ApiResponse;
import com.oppo.oppo.DTO.Response.UserResponse;
import com.oppo.oppo.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    public ApiResponse<UserResponse> createUser(@RequestBody UserCreationRequest request){

        return ApiResponse.<UserResponse>builder()
                .result(userService.addUser(request))
        .build();
    }

    @GetMapping("")
    public Page<UserResponse> getUsers(
            @RequestParam(name = "field", required = false, defaultValue = "id") String field,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "search", required = false, defaultValue = "") String search
    ){

//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        log.info("username: {}",authentication.getName());
//        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        Sort sortable = null;
        if(sort.toUpperCase().equals("ASC")){
            sortable = Sort.by(field).ascending();
        }
        if(sort.toUpperCase().equals("DESC")){
            sortable = Sort.by(field).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber,pageSize,sortable);
        Page<UserResponse> users = null;
        if(!search.trim().equals("")){
            users = userService.getUsersContains(search,pageable);
        }else users = userService.getUsers(pageable);
        return users;
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable String userId){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getById(userId))
                .build();
    }

    @GetMapping("/username/{username}")
    public ApiResponse<UserResponse> getUserByUsername(@PathVariable String username){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getByUsername(username))
                .build();
    }

    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo(){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String userId,@RequestBody UserUpdateRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(userId,request))
                .build();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public ApiResponse<String> deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return ApiResponse.<String>builder()
                .result("User has been deleted")
                .build();
    }
}
