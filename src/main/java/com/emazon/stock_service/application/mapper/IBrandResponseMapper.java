package com.emazon.stock_service.application.mapper;

import com.emazon.stock_service.application.dto.BrandResponseDto;
import com.emazon.stock_service.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBrandResponseMapper {
    BrandResponseDto toResponse(Brand brand);

    default List<BrandResponseDto> toResponseList(List<Brand> brandList){
        return brandList.stream().map(brand -> {
            BrandResponseDto brandResponseDto = new BrandResponseDto();
            brandResponseDto.setName(brand.getName());
            brandResponseDto.setDescription(brand.getDescription());
            return brandResponseDto;
        }).toList();
    }
}
