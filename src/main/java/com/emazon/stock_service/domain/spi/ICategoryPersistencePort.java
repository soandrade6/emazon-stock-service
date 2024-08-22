package com.emazon.stock_service.domain.spi;

import com.emazon.stock_service.domain.model.Category;

import java.util.List;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);

    List<Category> getAllCategory();
}
