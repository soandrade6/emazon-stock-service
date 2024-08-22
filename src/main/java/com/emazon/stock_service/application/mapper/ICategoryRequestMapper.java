package com.emazon.stock_service.application.mapper;

import com.emazon.stock_service.application.dto.CategoryRequestDto;
import com.emazon.stock_service.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface ICategoryRequestMapper {
    Category toCategory(CategoryRequestDto categoryRequestDto);
}
