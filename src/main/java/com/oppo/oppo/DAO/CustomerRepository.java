package com.oppo.oppo.DAO;

import com.oppo.oppo.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    Customer findByUser_Username(String username);
}
