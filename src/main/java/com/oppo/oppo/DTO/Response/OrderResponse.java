package com.oppo.oppo.DTO.Response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oppo.oppo.Entities.Customer;
import com.oppo.oppo.Entities.OrderDetail;
import com.oppo.oppo.Entities.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private String id;
    private String createdTime;
    private int totalPrice;
    private String note;
    private String paymentMethod;
    private Status status;
    private Customer customer;
    private Set<OrderDetail> orderDetails;
}
