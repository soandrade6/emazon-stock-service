package com.emazon.stock_service.domain.spi;

import com.emazon.stock_service.domain.model.Category;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
}
