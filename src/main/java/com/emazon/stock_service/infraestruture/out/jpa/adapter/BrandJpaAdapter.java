package com.emazon.stock_service.infraestruture.out.jpa.adapter;

import com.emazon.stock_service.domain.model.Brand;
import com.emazon.stock_service.domain.spi.IBrandPersistencePort;
import com.emazon.stock_service.infraestruture.exeption.NameAlreadyExistsException;
import com.emazon.stock_service.infraestruture.out.jpa.mapper.IBrandEntityMapper;
import com.emazon.stock_service.infraestruture.out.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BrandJpaAdapter implements IBrandPersistencePort {
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Override
    public void saveBrand(Brand brand) {
        String trimmedName = brand.getName().trim();
        String trimmedDescription = brand.getDescription().trim();

        // Crear una nueva categoría con los valores ajustados
        Brand trimmedBrand = new Brand();
        trimmedBrand.setId(brand.getId());
        trimmedBrand.setName(trimmedName);
        trimmedBrand.setDescription(trimmedDescription);
        if(brandRepository.findByName(trimmedName).isPresent()){
            throw new NameAlreadyExistsException("Brand name already exists");
        }
        brandRepository.save(brandEntityMapper.toEntity(trimmedBrand));
    }



}
