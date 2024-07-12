package com.oppo.oppo.DAO;

import com.oppo.oppo.Entities.Colors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorsRepository extends JpaRepository<Colors, String> {
}
