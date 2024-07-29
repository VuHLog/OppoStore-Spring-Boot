package com.oppo.oppo.DTO.Response;


import com.oppo.oppo.Entities.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private String id;

    private String name;

    private String phone;

    private String email;

    private String address;

    private Users user;
}
