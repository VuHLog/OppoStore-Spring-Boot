package com.oppo.oppo.DAO;

import com.oppo.oppo.Entities.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Orders, String> {
    Page<Orders> findByStatus_Status(String status, Pageable pageable);
}