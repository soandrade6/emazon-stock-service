package com.emazon.stock_service.infraestruture.out.jpa.repository;

import com.emazon.stock_service.infraestruture.out.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(String categoryName);
}