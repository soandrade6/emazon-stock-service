package com.emazon.stock_service.infraestruture.out.jpa.mapper;

import com.emazon.stock_service.domain.model.Category;
import com.emazon.stock_service.infraestruture.out.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryEntityMapper {

    CategoryEntity toEntity(Category category);
    Category toCategory(CategoryEntity categoryEntity);
}
