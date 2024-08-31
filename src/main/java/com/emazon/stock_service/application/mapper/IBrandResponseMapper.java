package com.emazon.stock_service.application.mapper;

import com.emazon.stock_service.application.dto.BrandResponseDto;
import com.emazon.stock_service.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBrandResponseMapper {
    BrandResponseDto toResponse(Brand brand);
}
