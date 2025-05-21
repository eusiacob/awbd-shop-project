package com.unibuc.awbd.service;

import com.unibuc.awbd.model.Category;
import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Long id);
}

