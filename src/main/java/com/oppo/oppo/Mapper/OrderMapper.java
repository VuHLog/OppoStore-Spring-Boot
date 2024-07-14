package com.oppo.oppo.Mapper;

import com.oppo.oppo.DTO.Request.OrderRequest;
import com.oppo.oppo.DTO.Request.OrderStatusRequest;
import com.oppo.oppo.DTO.Response.OrderResponse;
import com.oppo.oppo.DTO.Response.OrderStatusResponse;
import com.oppo.oppo.Entities.Orders;

public interface OrderMapper {
    Orders toOrders(OrderRequest request);
    Orders toOrdersStatus(OrderStatusRequest request);

    OrderResponse toOrderResponse(Orders order);
    OrderStatusResponse toOrderStatusResponse(Orders order);

    void updateOrder(Orders order, OrderRequest request);
    void updateOrderStatus(Orders order, OrderStatusRequest request);
}
