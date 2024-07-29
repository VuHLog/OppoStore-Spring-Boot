package com.oppo.oppo.Service.ServiceImpl;

import com.oppo.oppo.DAO.CustomerRepository;
import com.oppo.oppo.DTO.Request.CustomerRequest;
import com.oppo.oppo.DTO.Response.CustomerResponse;
import com.oppo.oppo.Entities.Customer;
import com.oppo.oppo.Mapper.CustomerMapper;
import com.oppo.oppo.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;


    @Override
    public CustomerResponse getByUsername(String username) {
        Customer customer = customerRepository.findByUser_Username(username);
        return customerMapper.toCustomerResponse(customerRepository.findByUser_Username(username));
    }

    @Override
    public CustomerResponse getById(String id) {
        return customerMapper.toCustomerResponse(customerRepository.findById(id).get());
    }

    @Override
    public CustomerResponse addCustomer(CustomerRequest request) {
        Customer customer = customerMapper.toCustomer(request);
        return customerMapper.toCustomerResponse(customerRepository.saveAndFlush(customer));
    }

    @Override
    public CustomerResponse updateCustomer(String customerId, CustomerRequest request) {
        Customer customer = customerRepository.findById(customerId).get();
        customerMapper.updateCustomer(customer,request);
        return customerMapper.toCustomerResponse(customerRepository.saveAndFlush(customer));
    }
}
