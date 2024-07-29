package com.oppo.oppo.Service;

import com.oppo.oppo.DTO.Request.UserCreationRequest;
import com.oppo.oppo.DTO.Request.UserUpdateRequest;
import com.oppo.oppo.DTO.Response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    public Page<UserResponse> getUsers(Pageable pageable);

    public Page<UserResponse> getUsersContains(String s,Pageable pageable);
    public UserResponse getById(String id);

    public UserResponse getByUsername(String username);

    public UserResponse addUser(UserCreationRequest request);

    public UserResponse updateUser(String userId, UserUpdateRequest request);

    public void deleteUser(String userId);

    public UserResponse getMyInfo();
}
