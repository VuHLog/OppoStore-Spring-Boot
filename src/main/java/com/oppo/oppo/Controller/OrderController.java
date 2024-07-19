package com.oppo.oppo.Controller;

import com.oppo.oppo.DTO.Request.OrderStatusRequest;
import com.oppo.oppo.DTO.Response.ApiResponse;
import com.oppo.oppo.DTO.Response.OrderDetailResponse;
import com.oppo.oppo.DTO.Response.OrderResponse;
import com.oppo.oppo.DTO.Response.OrderStatusResponse;
import com.oppo.oppo.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public Page<OrderResponse> getOrders(
            @RequestParam(name = "field", required = false, defaultValue = "createdTime") String field,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
            @RequestParam(name = "sort", required = false, defaultValue = "DESC") String sort,
            @RequestParam(name = "search", required = false, defaultValue = "") String search,
            @RequestParam(name = "status", required = false, defaultValue = "") String status
    ) {

        Sort sortable = sort.equalsIgnoreCase("ASC") ? Sort.by(field).ascending() : Sort.by(field).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortable);
        Page<OrderResponse> orders = null;
        if(!status.isEmpty()){
            orders = orderService.getOrdersByStatus(status,pageable);
            return orders;
        }
        if (!search.trim().equals("")) {
//            orders = orderService.getOrdersContains(search, pageable);
        } else orders = orderService.getOrders(pageable);
        return orders;
    }

    @GetMapping("/{orderId}")
    public ApiResponse<OrderResponse> getOrderById(@PathVariable String orderId) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.getById(orderId))
                .build();
    }

    @GetMapping("/{orderId}/details")
    public ApiResponse<List<OrderDetailResponse>> getOrderDetailByOrderId(@PathVariable String orderId) {
        return ApiResponse.<List<OrderDetailResponse>>builder()
                .result(orderService.getOrderDetails(orderId))
                .build();
    }

    @PutMapping("/{orderId}/status")
    public ApiResponse<OrderStatusResponse> updateOrderStatus(@PathVariable String orderId, @RequestBody OrderStatusRequest request){
        return ApiResponse.<OrderStatusResponse>builder()
                .result(orderService.updateOrderStatus(orderId,request))
                .build();
    }
}
