package com.emazon.stock_service.infraestruture.out.jpa;

import com.emazon.stock_service.infraestruture.out.jpa.entity.CategoryEntity;

import java.util.Optional;

public interface ICategoryRepository {
    Optional<CategoryEntity> findByName(String categoryName);
}
