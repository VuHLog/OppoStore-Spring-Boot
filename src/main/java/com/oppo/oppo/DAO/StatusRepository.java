package com.oppo.oppo.DAO;

import com.oppo.oppo.Entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, String> {
}