package com.oppo.oppo.DTO.Request;

import com.oppo.oppo.Entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateRequest {
    private String fullName;


    private String password;

    private String email;

    private String phone;

    private String avatarUrl;

    private Set<Role> roles;
}
