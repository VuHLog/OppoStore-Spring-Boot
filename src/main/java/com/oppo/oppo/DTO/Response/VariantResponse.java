package com.oppo.oppo.DTO.Response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oppo.oppo.Entities.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VariantResponse {
    private String id;
    private int stock;
    private int price;
    private String image;
    private MobilePhone mobilePhone;
    private Colors color;
    private ROM ROM;
    private Set<OrderDetail> orderDetails;
}
