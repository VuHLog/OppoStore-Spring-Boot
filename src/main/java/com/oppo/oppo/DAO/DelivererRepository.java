package com.oppo.oppo.DAO;

import com.oppo.oppo.Entities.Deliverer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DelivererRepository extends JpaRepository<Deliverer, String> {
}
