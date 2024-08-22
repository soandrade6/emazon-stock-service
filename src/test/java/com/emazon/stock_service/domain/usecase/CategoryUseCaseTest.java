package com.emazon.stock_service.domain.usecase;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.emazon.stock_service.domain.exception.DescriptionTooLongException;
import com.emazon.stock_service.domain.exception.NameTooLongException;
import com.emazon.stock_service.domain.model.Category;
import com.emazon.stock_service.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCategory_NameTooLong() {
        // Configurar la categoría con un nombre demasiado largo
        Category category = new Category(1L, "A very very very very very very very long name", "Valid description");

        // Verificar que se lanza la excepción correcta
        assertThrows(NameTooLongException.class, () -> {
            categoryUseCase.saveCategory(category);
        });

        // Verificar que el método saveCategory no fue llamado
        verify(categoryPersistencePort, never()).saveCategory(any(Category.class));
    }

    @Test
    void testSaveCategory_DescriptionTooLong() {
        // Configurar la categoría con una descripción demasiado larga
        Category category = new Category(1L, "Valid name", "A very very very very very very very very long description");

        // Verificar que se lanza la excepción correcta
        assertThrows(DescriptionTooLongException.class, () -> {
            categoryUseCase.saveCategory(category);
        });

        // Verificar que el método saveCategory no fue llamado
        verify(categoryPersistencePort, never()).saveCategory(any(Category.class));
    }

    @Test
    void testSaveCategory_Success() {
        // Configurar una categoría válida
        Category category = new Category(1L, "Valid name", "Valid description");

        // Llamar al método que se va a probar
        categoryUseCase.saveCategory(category);

        // Verificar que el método saveCategory fue llamado una vez con la categoría correcta
        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }
}
