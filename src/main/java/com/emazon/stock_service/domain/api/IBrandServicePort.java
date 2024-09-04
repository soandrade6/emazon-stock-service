package com.emazon.stock_service.domain.api;

import com.emazon.stock_service.domain.model.Brand;

import java.util.List;

public interface IBrandServicePort {

    void saveBrand(Brand brand);

     List<Brand> getAllBrand();
}