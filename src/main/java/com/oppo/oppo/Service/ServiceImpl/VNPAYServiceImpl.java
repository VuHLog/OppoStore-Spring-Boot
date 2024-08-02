package com.oppo.oppo.Service.ServiceImpl;

import com.oppo.oppo.Configuration.VNPAYConfig;
import com.oppo.oppo.DAO.OrderRepository;
import com.oppo.oppo.DTO.Response.TransactionStatusPaymentResponse;
import com.oppo.oppo.DTO.Response.VNPayResponse;
import com.oppo.oppo.Entities.Orders;
import com.oppo.oppo.Service.VNPAYService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class VNPAYServiceImpl implements VNPAYService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public VNPayResponse createPayment(HttpServletRequest req,long price,String orderId) throws UnsupportedEncodingException {
        String orderType = "other";
        long amount = price*100;
//        String bankCode = req.getParameter("bankCode");

        String vnp_TxnRef = VNPAYConfig.getRandomNumber(8);
        String vnp_IpAddr = VNPAYConfig.getIpAddress(req);

        String vnp_TmnCode = VNPAYConfig.vnp_TmnCode;

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
//        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

//        if (bankCode != null && !bankCode.isEmpty()) {
//            vnp_Params.put("vnp_BankCode", bankCode);
//        }
        vnp_Params.put("vnp_BankCode", "NCb");

        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

//        String locate = req.getParameter("language");
//        if (locate != null && !locate.isEmpty()) {
//            vnp_Params.put("vnp_Locale", locate);
//        } else {
//            vnp_Params.put("vnp_Locale", "vn");
//        }
        vnp_Params.put("vnp_Locale", "vn");

        vnp_Params.put("vnp_ReturnUrl", VNPAYConfig.vnp_ReturnUrl + "/"+orderId);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPAYConfig.hmacSHA512(VNPAYConfig.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPAYConfig.vnp_PayUrl + "?" + queryUrl;
        VNPayResponse vnPayResponse = VNPayResponse.builder()
                .status("OK")
                .message("Successfully")
                .URL(paymentUrl)
                .build();
        return vnPayResponse;
    }

    @Override
    public TransactionStatusPaymentResponse getTransaction(String responseCode, String orderInfo, String orderId) {
        TransactionStatusPaymentResponse transactionStatusPaymentResponse = new TransactionStatusPaymentResponse();
        Orders orders = orderRepository.findById(orderId).get();
        if(responseCode.equals("00")){
            transactionStatusPaymentResponse = TransactionStatusPaymentResponse.builder()
                    .status("OK")
                    .message("Successfully")
                    .data(orderInfo)
                    .build();
            orders.setPaymentStatus(1);
        }else {
            transactionStatusPaymentResponse = TransactionStatusPaymentResponse.builder()
                    .status("NO")
                    .message("Failed")
                    .data(orderInfo)
                    .build();
            orders.setPaymentStatus(0);
        }
        orderRepository.saveAndFlush((orders));
        return  transactionStatusPaymentResponse;
    }
}
