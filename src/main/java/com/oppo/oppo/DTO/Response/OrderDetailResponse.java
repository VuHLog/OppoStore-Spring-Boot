package com.oppo.oppo.DTO.Response;

import com.oppo.oppo.Entities.MobilePhone;
import com.oppo.oppo.Entities.Orders;
import com.oppo.oppo.Entities.Variants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailResponse {
    private String id;

    private int quantity;

    private int price;

    private Variants variant;
}
