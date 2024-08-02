package com.oppo.oppo.Service.ServiceImpl;

import com.oppo.oppo.DAO.OrderDetailRepository;
import com.oppo.oppo.DAO.OrderRepository;
import com.oppo.oppo.DTO.Request.OrderRequest;
import com.oppo.oppo.DTO.Request.OrderStatusRequest;
import com.oppo.oppo.DTO.Response.OrderDetailResponse;
import com.oppo.oppo.DTO.Response.OrderResponse;
import com.oppo.oppo.DTO.Response.OrderStatusResponse;
import com.oppo.oppo.Entities.Customer;
import com.oppo.oppo.Entities.Orders;
import com.oppo.oppo.Mapper.OrderDetailMapper;
import com.oppo.oppo.Mapper.OrderMapper;
import com.oppo.oppo.Service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public Page<OrderResponse> getOrders(String customerId,Pageable pageable) {
        return orderRepository.findByCustomer_Id(customerId, pageable).map(orderMapper::toOrderResponse);
    }

    @Override
    public Page<OrderResponse> getOrdersByStatus(String status,String customerId, Pageable pageable) {
        return orderRepository.findByCustomer_IdAndStatus_Status(status,customerId,pageable).map(orderMapper::toOrderResponse);
    }

//    @Override
//    public Page<OrderResponse> getOrdersContains(String s, Pageable pageable) {
//        return orderRepository.findByNameContainsIgnoreCase(s,pageable).map(orderMapper::toMobilePhoneResponse);
//    }

    @Override
    public OrderResponse getById(String id) {
        return orderMapper.toOrderResponse(orderRepository.findById(id).get());
    }

    @Transactional
    @Override
    public OrderResponse addOrder(OrderRequest request) {
        Orders order = orderMapper.toOrder(request);

        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        // Chuyển đổi LocalDateTime sang Timestamp
        Timestamp timestamp = Timestamp.valueOf(localDateTime);

        order.setCreatedTime(timestamp);
        order.getOrderDetails().forEach(orderDetail -> orderDetail.setOrders(order));
        order.setPaymentStatus(0);
        return orderMapper.toOrderResponse(orderRepository.save(order));
    }

    @Override
    public OrderStatusResponse updateOrderStatus(String orderId, OrderStatusRequest request) {
        Orders order = orderRepository.findById(orderId).get();
        orderMapper.updateOrderStatus(order, request);
        return orderMapper.toOrderStatusResponse(orderRepository.saveAndFlush(order));
    }

    @Override
    public OrderResponse updatePaymentMethod(String orderId,int paymentStatus) {
        Orders order = orderRepository.findById(orderId).get();
        order.setPaymentStatus(paymentStatus);
        return orderMapper.toOrderResponse(orderRepository.saveAndFlush(order));
    }

    @Override
    public List<OrderDetailResponse> getOrderDetails(String orderId) {
        return orderDetailRepository.findByOrderId(orderId).stream().map(orderDetailMapper::toOrderDetailResponse).toList();
    }
}
