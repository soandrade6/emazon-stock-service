package com.emazon.stock_service.application.handler;

import com.emazon.stock_service.application.dto.CategoryRequestDto;
import com.emazon.stock_service.application.dto.CategoryResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryHandler {
    void saveCategory(CategoryRequestDto categoryRequestDto);

    List<CategoryResponseDto> getAllCategory();

    Page<CategoryResponseDto> getAllCategoryOrderedByName(String order, int page, int size);


}
