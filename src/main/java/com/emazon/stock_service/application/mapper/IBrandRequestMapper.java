package com.emazon.stock_service.application.mapper;

import com.emazon.stock_service.application.dto.BrandRequestDto;
import com.emazon.stock_service.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBrandRequestMapper {
    Brand toBrand(BrandRequestDto brandRequestDto);
}
