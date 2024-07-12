package com.oppo.oppo.DAO;

import com.oppo.oppo.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}