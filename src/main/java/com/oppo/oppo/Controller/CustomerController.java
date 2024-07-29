package com.oppo.oppo.Controller;

import com.oppo.oppo.DTO.Request.CustomerRequest;
import com.oppo.oppo.DTO.Response.ApiResponse;
import com.oppo.oppo.DTO.Response.CustomerResponse;
import com.oppo.oppo.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/{customerId}")
    public ApiResponse<CustomerResponse> getById(@PathVariable String customerId){
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.getById(customerId))
                .build();
    }

    @GetMapping("/by-username/{username}")
    public ApiResponse<CustomerResponse> getByUsername(@PathVariable String username){
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.getByUsername(username))
                .build();
    }

    @PostMapping
    public ApiResponse<CustomerResponse> createCustomer(@RequestBody CustomerRequest request){
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.addCustomer(request))
                .build();
    }

    @PutMapping("/{customerId}")
    public ApiResponse<CustomerResponse> updateCustomer(@PathVariable String customerId ,@RequestBody CustomerRequest request){
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.updateCustomer(customerId,request))
                .build();
    }
}
