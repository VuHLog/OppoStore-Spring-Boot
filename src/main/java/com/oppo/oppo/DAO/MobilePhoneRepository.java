package com.oppo.oppo.DAO;

import com.oppo.oppo.Entities.MobilePhone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

public interface MobilePhoneRepository extends JpaRepository<MobilePhone, String> {
    boolean existsByName(String name);

    Optional<MobilePhone> findByName(String name);

    Page<MobilePhone> findByNameContainsIgnoreCase(String name, Pageable pageable);

    @Query(value = "select mp.*,tq.total_quantity from mobile_phone as mp\n" +
            "join (\n" +
            "\tselect mp.id , sum(quantity) as total_quantity\n" +
            "from order_detail as od\n" +
            "join variants as v on od.variant_id = v.id\n" +
            "join mobile_phone as mp on v.mobile_phone_id = mp.id\n" +
            "group by mp.id\n" +
            ") as tq on mp.id = tq.id\n" +
            "order by total_quantity desc OFFSET 0 ROWS FETCH FIRST :top ROWS ONLY", nativeQuery = true)
    List<MobilePhone> getBestSeller(@Param("top") int top);

    @Query(value = "select mp.*\n" +
            "from mobile_phone as mp\n" +
            "join category as c on mp.category_id = c.id\n" +
            "where c.name = :name\n" +
            "order by mp.id OFFSET 0 ROWS FETCH FIRST :top ROWS ONLY", nativeQuery = true)
    List<MobilePhone> findMobilePhoneByCategoryName(@Param("name") String name, @Param("top") int top);

    Page<MobilePhone> findAll(Specification<MobilePhone> spec, Pageable pageable);
}