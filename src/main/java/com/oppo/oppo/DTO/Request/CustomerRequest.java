package com.oppo.oppo.DTO.Request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oppo.oppo.Entities.Orders;
import com.oppo.oppo.Entities.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CustomerRequest {
    private String name;

    private String phone;

    private String email;

    private String address;

    private Users user;
}
