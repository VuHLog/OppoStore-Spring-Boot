package com.oppo.oppo.Service;

import com.oppo.oppo.DTO.Request.OrderRequest;
import com.oppo.oppo.DTO.Request.OrderStatusRequest;
import com.oppo.oppo.DTO.Response.OrderDetailResponse;
import com.oppo.oppo.DTO.Response.OrderResponse;
import com.oppo.oppo.DTO.Response.OrderStatusResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    public Page<OrderResponse> getOrders(String customerId, Pageable pageable) ;

    public Page<OrderResponse> getOrdersByStatus(String status,String customerId, Pageable pageable);

    public OrderResponse getById(String id) ;

    public OrderResponse addOrder(OrderRequest request);
    public OrderStatusResponse updateOrderStatus(String orderId, OrderStatusRequest request);

    public OrderResponse updatePaymentMethod(String orderId,int paymentStatus);

    public List<OrderDetailResponse> getOrderDetails(String orderId);
}
