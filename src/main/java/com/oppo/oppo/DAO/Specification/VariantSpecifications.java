package com.oppo.oppo.DAO.Specification;

import com.oppo.oppo.Entities.MobilePhone;
import com.oppo.oppo.Entities.Variants;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class VariantSpecifications {

    public static Specification<Variants> equalRAM(int ram) {
        return (root, query, criteriaBuilder) ->{
            Join<Object, Object> join = root.join("mobilePhone", JoinType.INNER);
            return criteriaBuilder.equal(join.get("RAM"), ram);
        };
    }

    public static Specification<Variants> gtCharge(int charge) {
        return (root, query, criteriaBuilder) ->{
            Join<Object, Object> join = root.join("mobilePhone", JoinType.INNER);
            return criteriaBuilder.gt(join.get("charger"), charge);
        };
    }

    public static Specification<Variants> equalRom(int rom) {
        return (root, query, criteriaBuilder) -> {
            Join<Object, Object> join = root.join("ROM", JoinType.INNER);
            return criteriaBuilder.equal(join.get("capacity"), rom);
        };
    }

    public static Specification<Variants> betweenPrice(Integer small, Integer big) {
        return (root, query, criteriaBuilder) ->
        {
            if(small == null){
                return criteriaBuilder.lessThanOrEqualTo(root.get("price"),big);
            }
            if(big == null){
                return criteriaBuilder.greaterThanOrEqualTo(root.get("price"),small);
            }
            return criteriaBuilder.between(root.get("price"),small, big);
        };
    }

}
