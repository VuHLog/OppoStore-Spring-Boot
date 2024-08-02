package com.oppo.oppo.Controller;

import com.oppo.oppo.Configuration.VNPAYConfig;
import com.oppo.oppo.DTO.Response.ApiResponse;
import com.oppo.oppo.DTO.Response.TransactionStatusPaymentResponse;
import com.oppo.oppo.DTO.Response.VNPayResponse;
import com.oppo.oppo.Service.VNPAYService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(("/api/vnpay-payment"))
public class VNPAYPaymentController {

    @Autowired
    private VNPAYService vnpayService;

    @GetMapping("/create-vnpay-payment")
    public ApiResponse<VNPayResponse> createVNPayPayment(
            HttpServletRequest req,
            @RequestParam(name = "price",required = true) long price,
            @RequestParam(name = "order_id",required = true) String orderId
    ) throws UnsupportedEncodingException {
        return ApiResponse.<VNPayResponse>builder()
                .result(vnpayService.createPayment(req,price,orderId))
                .build();
    }

    @GetMapping("/payment-info/{orderId}")
    public ApiResponse<TransactionStatusPaymentResponse> transaction(
            @RequestParam(name = "vnp_ResponseCode") String vnp_ResponseCode,
            @RequestParam(name = "vnp_OrderInfo") String vnp_OrderInfo,
            @PathVariable String orderId
    ){
        return ApiResponse.<TransactionStatusPaymentResponse>builder()
                .result(vnpayService.getTransaction(vnp_ResponseCode,vnp_OrderInfo,orderId))
                .build();
    }
}
