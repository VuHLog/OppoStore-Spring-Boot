package com.oppo.oppo.Service;

import com.oppo.oppo.DTO.Response.TransactionStatusPaymentResponse;
import com.oppo.oppo.DTO.Response.VNPayResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;

public interface VNPAYService {
    public VNPayResponse createPayment(HttpServletRequest req,long price,String orderId) throws UnsupportedEncodingException;

    public TransactionStatusPaymentResponse getTransaction(String responseCode, String orderInfo);
}
