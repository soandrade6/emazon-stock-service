package com.emazon.stock_service.domain.api;

import com.emazon.stock_service.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryServicePort {
    void saveCategory(Category category);

    List<Category> getAllCategory();

}
