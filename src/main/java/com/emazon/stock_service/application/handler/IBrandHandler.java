package com.emazon.stock_service.application.handler;

import com.emazon.stock_service.application.dto.BrandRequestDto;
import com.emazon.stock_service.application.dto.BrandResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBrandHandler {
    void saveBrand(BrandRequestDto brandRequestDto);

    List<BrandResponseDto> getAllBrand();

    Page<BrandResponseDto> getPaginatedBrand(String order, int page, int size);
}
