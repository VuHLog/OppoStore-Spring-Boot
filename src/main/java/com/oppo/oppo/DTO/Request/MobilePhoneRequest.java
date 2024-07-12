package com.oppo.oppo.DTO.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oppo.oppo.Entities.Category;
import com.oppo.oppo.Entities.OrderDetail;
import com.oppo.oppo.Entities.Variants;
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
public class MobilePhoneRequest {
    private String name;

    private String frontCamera;

    private String rearCamera;

    private String CPU;

    private int battery;

    private String screen;

    private String resolution;

    private int RAM;

    private String memoryStick;

    private String sim;

    private String operatingSystem;

    private String size;

    private String weight;

    private int charger;

    private String brand;

    private String status;

    private Category category;

    private Set<Variants> variants;

    private Set<OrderDetail> orderDetails;
}
