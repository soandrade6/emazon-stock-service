package com.emazon.stock_service.domain.usecase;

import com.emazon.stock_service.domain.api.ICategoryServicePort;
import com.emazon.stock_service.domain.exception.DescriptionTooLongException;
import com.emazon.stock_service.domain.exception.NameTooLongException;
import com.emazon.stock_service.domain.model.Category;
import com.emazon.stock_service.domain.spi.ICategoryPersistencePort;
import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;
    private final int MAX_NAME_LENGTH = 50;
    private final int MAX_DESCRIPTION_LENGTH = 90;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        if(category.getName().length() > this.MAX_NAME_LENGTH){
            throw new NameTooLongException("Category name exceeds maximum length.");
        }else if (category.getDescription().length() > this.MAX_DESCRIPTION_LENGTH){
            throw new DescriptionTooLongException("Category description exceeds maximum length.");
        }
        categoryPersistencePort.saveCategory(category);

    }

    @Override
    public List<Category> getAllCategory() {
        return categoryPersistencePort.getAllCategory();
    }

}