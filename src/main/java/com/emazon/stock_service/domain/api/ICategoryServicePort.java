package com.emazon.stock_service.domain.api;

import com.emazon.stock_service.domain.model.Category;

public interface ICategoryServicePort {
    void saveCategory(Category category);
}
