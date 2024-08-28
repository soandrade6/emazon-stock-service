package com.emazon.stock_service.domain.spi;

import com.emazon.stock_service.domain.model.Brand;

public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
}
