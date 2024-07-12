package com.oppo.oppo.DAO;

import com.oppo.oppo.Entities.MobilePhone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MobilePhoneRepository extends JpaRepository<MobilePhone, String> {
    boolean existsByName(String name);

    Optional<MobilePhone> findByName(String name);

    Page<MobilePhone> findByNameContainsIgnoreCase(String name, Pageable pageable);

    Page<MobilePhone> findAll(Specification<MobilePhone> spec, Pageable pageable);
}