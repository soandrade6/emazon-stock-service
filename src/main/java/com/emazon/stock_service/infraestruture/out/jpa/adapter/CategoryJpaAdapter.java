package com.emazon.stock_service.infraestruture.out.jpa.adapter;

import com.emazon.stock_service.domain.model.Category;
import com.emazon.stock_service.domain.spi.ICategoryPersistencePort;
import com.emazon.stock_service.infraestruture.exeption.CategoryAlreadyExistsException;
import com.emazon.stock_service.infraestruture.out.jpa.mapper.ICategoryEntityMapper;
import com.emazon.stock_service.infraestruture.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        if (categoryRepository.findByName(category.getName()).isPresent()){
            throw new CategoryAlreadyExistsException();
        }

        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }
}
