package com.emazon.stock_service.infraestruture.out.jpa.adapter;

import com.emazon.stock_service.domain.model.Category;
import com.emazon.stock_service.domain.spi.ICategoryPersistencePort;
import com.emazon.stock_service.infraestruture.exeption.NameAlreadyExistsException;
import com.emazon.stock_service.infraestruture.out.jpa.entity.CategoryEntity;
import com.emazon.stock_service.infraestruture.out.jpa.mapper.ICategoryEntityMapper;
import com.emazon.stock_service.infraestruture.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        // Eliminar espacios adicionales en el nombre y descripción
        String trimmedName = category.getName().trim();
        String trimmedDescription = category.getDescription().trim();

        // Crear una nueva categoría con los valores ajustados
        Category trimmedCategory = new Category();
        trimmedCategory.setId(category.getId());
        trimmedCategory.setName(trimmedName);
        trimmedCategory.setDescription(trimmedDescription);
        if (categoryRepository.findByName(trimmedName).isPresent()){
            throw new NameAlreadyExistsException("Category name already exists");
        }

        categoryRepository.save(categoryEntityMapper.toEntity(trimmedCategory));
    }

    @Override
    public List<Category> getAllCategory() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        if (categoryEntityList.isEmpty()){
            //data no foued exception
        }
        return categoryEntityMapper.toCategoryList(categoryEntityList);
    }


}
