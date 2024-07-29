package com.oppo.oppo.Mapper;

import com.oppo.oppo.DTO.Request.CustomerRequest;
import com.oppo.oppo.DTO.Response.CustomerResponse;
import com.oppo.oppo.Entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CustomerRequest request);

    CustomerResponse toCustomerResponse(Customer customer);

    void updateCustomer(@MappingTarget Customer customer, CustomerRequest request);
}
