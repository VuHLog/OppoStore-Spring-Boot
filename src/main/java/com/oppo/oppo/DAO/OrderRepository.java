package com.oppo.oppo.DAO;

import com.oppo.oppo.Entities.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Orders, String> {
    Page<Orders> findByCustomer_IdAndStatus_Status(String id, String status, Pageable pageable);

    Page<Orders> findByCustomer_Id(String id, Pageable pageable);
}