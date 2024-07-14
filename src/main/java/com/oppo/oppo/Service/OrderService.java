package com.oppo.oppo.Service;

import com.oppo.oppo.DTO.Request.OrderStatusRequest;
import com.oppo.oppo.DTO.Response.OrderDetailResponse;
import com.oppo.oppo.DTO.Response.OrderResponse;
import com.oppo.oppo.DTO.Response.OrderStatusResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    public Page<OrderResponse> getOrders(Pageable pageable) ;

//    public Page<OrderResponse> getOrdersContains(String s, Pageable pageable);

    public OrderResponse getById(String id) ;
    public OrderStatusResponse updateOrderStatus(String orderId, OrderStatusRequest request);

    public List<OrderDetailResponse> getOrderDetails(String orderId);
}