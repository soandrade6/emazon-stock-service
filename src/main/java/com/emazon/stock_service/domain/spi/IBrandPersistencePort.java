package com.emazon.stock_service.domain.spi;

import com.emazon.stock_service.domain.model.Brand;

import java.util.List;


public interface IBrandPersistencePort {
    void saveBrand(Brand brand);

    List<Brand> getAllBrand();

}
