package com.emazon.stock_service.domain;

import com.emazon.stock_service.domain.api.ICategoryServicePort;
import com.emazon.stock_service.domain.exception.DescriptionTooLongException;
import com.emazon.stock_service.domain.exception.NameTooLongException;
import com.emazon.stock_service.domain.model.Category;
import com.emazon.stock_service.domain.spi.ICategoryPersistencePort;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void save(Category category) {
        if(category.getName().length() > 50){
            throw new NameTooLongException();
        }else if (category.getDescription().length() > 90){
            throw new DescriptionTooLongException();
        }
        categoryPersistencePort.save(category);

    }
}
