package com.oppo.oppo.DAO;

import com.oppo.oppo.Entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, String> {
}