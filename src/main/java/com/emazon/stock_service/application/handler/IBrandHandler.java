package com.emazon.stock_service.application.handler;

import com.emazon.stock_service.application.dto.BrandRequestDto;

public interface IBrandHandler {
    void saveBrand(BrandRequestDto brandRequestDto);
}
