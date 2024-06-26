package com.oppo.oppo.DTO.Response;

import com.oppo.oppo.Entities.User_Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private String id;

    private String fullName;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String avatarUrl;

    private Set<User_Role> user_roles;
}
