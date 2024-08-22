package com.emazon.stock_service.application.mapper;

import com.emazon.stock_service.application.dto.CategoryRequestDto;
import com.emazon.stock_service.application.dto.CategoryResponseDto;
import com.emazon.stock_service.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryResponseMapper {
    CategoryResponseDto toResponse(Category category);

    default List<CategoryResponseDto> toResponseList(List<Category> categoryList){
        return categoryList.stream().map(category -> {
            CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
            categoryResponseDto.setName(category.getName());
            categoryResponseDto.setDescription(category.getDescription());
            return categoryResponseDto;
        }).toList();
    }
}
