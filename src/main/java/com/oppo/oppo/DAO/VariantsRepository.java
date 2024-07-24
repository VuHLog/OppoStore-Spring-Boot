package com.oppo.oppo.DAO;

import com.oppo.oppo.Entities.Variants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface VariantsRepository extends JpaRepository<Variants, String>, JpaSpecificationExecutor<Variants> {

    List<Variants> findByMobilePhone_IdAndColor_IdOrderByROM_CapacityAsc(String mobilePhoneId, String colorId);
}