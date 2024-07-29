package com.oppo.oppo.DTO.Request;

import com.oppo.oppo.Entities.Customer;
import com.oppo.oppo.Entities.OrderDetail;
import com.oppo.oppo.Entities.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    private int totalPrice;
    private String note;
    private String paymentMethod;
    private Status status;
    private Customer customer;
    private Set<OrderDetail> orderDetails;
}
