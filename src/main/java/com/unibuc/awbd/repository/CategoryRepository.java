package com.unibuc.awbd.repository;

import com.unibuc.awbd.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
