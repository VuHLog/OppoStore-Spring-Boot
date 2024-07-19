package com.oppo.oppo.DAO;

import com.oppo.oppo.Entities.Variants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VariantsRepository extends JpaRepository<Variants, String>, JpaSpecificationExecutor<Variants> {
}