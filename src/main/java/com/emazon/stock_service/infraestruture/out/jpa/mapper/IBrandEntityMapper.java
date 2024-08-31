package com.emazon.stock_service.infraestruture.out.jpa.mapper;

import com.emazon.stock_service.domain.model.Brand;
import com.emazon.stock_service.infraestruture.out.jpa.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBrandEntityMapper {
    BrandEntity toEntity(Brand brand);
    Brand toBrand(BrandEntity brandEntity);
}
