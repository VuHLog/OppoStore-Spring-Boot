package com.oppo.oppo.Mapper;

import com.oppo.oppo.DTO.Request.OrderRequest;
import com.oppo.oppo.DTO.Request.OrderStatusRequest;
import com.oppo.oppo.DTO.Response.OrderResponse;
import com.oppo.oppo.DTO.Response.OrderStatusResponse;
import com.oppo.oppo.Entities.OrderDetail;
import com.oppo.oppo.Entities.Orders;
import com.oppo.oppo.Utils.Times;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class OrderMapperImpl implements OrderMapper{
    public Orders toOrders(OrderRequest request) {
        if ( request == null ) {
            return null;
        }

        Orders.OrdersBuilder orders = Orders.builder();

        orders.status( request.getStatus() );

        return orders.build();
    }


    public Orders toOrdersStatus(OrderStatusRequest request) {
        if ( request == null ) {
            return null;
        }

        Orders.OrdersBuilder orders = Orders.builder();

        orders.status( request.getStatus() );

        return orders.build();
    }


    public OrderResponse toOrderResponse(Orders order) {
        if ( order == null ) {
            return null;
        }

        OrderResponse.OrderResponseBuilder orderResponse = OrderResponse.builder();

        orderResponse.id( order.getId() );
        orderResponse.createdTime(Times.timestampToString(order.getCreatedTime()));
        orderResponse.totalPrice( order.getTotalPrice() );
        orderResponse.note( order.getNote() );
        orderResponse.status( order.getStatus() );
        orderResponse.customer( order.getCustomer() );
        Set<OrderDetail> set = order.getOrderDetails();
        if ( set != null ) {
            orderResponse.orderDetails( new LinkedHashSet<OrderDetail>( set ) );
        }

        return orderResponse.build();
    }


    public OrderStatusResponse toOrderStatusResponse(Orders order) {
        if ( order == null ) {
            return null;
        }

        OrderStatusResponse.OrderStatusResponseBuilder orderStatusResponse = OrderStatusResponse.builder();

        orderStatusResponse.id( order.getId() );
        orderStatusResponse.status( order.getStatus() );

        return orderStatusResponse.build();
    }


    public void updateOrder(Orders order, OrderRequest request) {
        if ( request == null ) {
            return;
        }

        order.setStatus( request.getStatus() );
    }


    public void updateOrderStatus(Orders order, OrderStatusRequest request) {
        if ( request == null ) {
            return;
        }

        order.setStatus( request.getStatus() );
    }
}
