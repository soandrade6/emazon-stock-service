package com.emazon.stock_service.domain.usecase;

import com.emazon.stock_service.domain.model.Brand;
import com.emazon.stock_service.domain.spi.IBrandPersistencePort;

public class BrandUseCase implements IBrandPersistencePort {

    private final IBrandPersistencePort brandPersistencePort;
    private final int MAX_NAME_LENGTH = 50;
    private final int MAX_DESCRIPTION_LENGTH = 120;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        if (brand.getName().length() > MAX_NAME_LENGTH){
            //Excepcion
        } else if (brand.getDescription().length() > MAX_DESCRIPTION_LENGTH){
            //Excepcion
        }

        brandPersistencePort.saveBrand(brand);
    }
}
