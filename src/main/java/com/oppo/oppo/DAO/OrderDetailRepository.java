package com.oppo.oppo.DAO;

import com.oppo.oppo.Entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    @Query(value = "select * from order_detail as od where od.order_id =:orderId",nativeQuery = true)
    List<OrderDetail> findByOrderId(@Param("orderId") String orderId);
}