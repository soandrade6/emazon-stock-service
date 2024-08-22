package com.emazon.stock_service.application.handler;

import com.emazon.stock_service.application.dto.CategoryRequestDto;

public interface ICategoryHandler {
    void saveCategory(CategoryRequestDto categoryRequestDto);
}
