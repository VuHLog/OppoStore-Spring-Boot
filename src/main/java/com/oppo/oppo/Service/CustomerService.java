package com.oppo.oppo.Service;

import com.oppo.oppo.DTO.Request.CustomerRequest;
import com.oppo.oppo.DTO.Response.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    public CustomerResponse getByUsername(String username);

    public CustomerResponse getById(String id);

    public CustomerResponse addCustomer(CustomerRequest request);

    public CustomerResponse updateCustomer(String customerId, CustomerRequest request);
}
