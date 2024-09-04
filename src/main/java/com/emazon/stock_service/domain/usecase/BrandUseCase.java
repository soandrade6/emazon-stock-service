package com.emazon.stock_service.domain.usecase;

import com.emazon.stock_service.domain.api.IBrandServicePort;
import com.emazon.stock_service.domain.exception.DescriptionTooLongException;
import com.emazon.stock_service.domain.exception.NameTooLongException;
import com.emazon.stock_service.domain.model.Brand;
import com.emazon.stock_service.domain.spi.IBrandPersistencePort;

import java.util.List;

public class BrandUseCase implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;
    private final int MAX_NAME_LENGTH = 50;
    private final int MAX_DESCRIPTION_LENGTH = 120;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        if (brand.getName().length() > this.MAX_NAME_LENGTH){
            throw new NameTooLongException("Brand name exceeds maximum length.");
        } else if (brand.getDescription().length() > this.MAX_DESCRIPTION_LENGTH) {
            throw new DescriptionTooLongException("Brand description exceeds maximum length");
        }
        brandPersistencePort.saveBrand(brand);
    }

    @Override
    public List<Brand> getAllBrand() {
        return brandPersistencePort.getAllBrand();
    }

}
