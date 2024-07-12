package com.oppo.oppo.DAO;

import com.oppo.oppo.Entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}