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

import java.util.Arrays;
import java.util.List;

class CategoryUseCaseTest {

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
        // Configurar la categoría con un nombre demasiado largo (más de 50 caracteres)
        Category category = new Category(1L, "A".repeat(51), "Valid description");

        // Verificar que se lanza la excepción correcta
        assertThrows(NameTooLongException.class, () -> {
            categoryUseCase.saveCategory(category);
        });

        // Verificar que el método saveCategory no fue llamado
        verify(categoryPersistencePort, never()).saveCategory(any(Category.class));
    }

    @Test
    void testSaveCategory_DescriptionTooLong() {
        // Configurar la categoría con una descripción demasiado larga (más de 90 caracteres)
        Category category = new Category(1L, "Valid name", "A".repeat(91));

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

    @Test
    void testGetAllCategory() {
        // Configurar las categorías de prueba
        Category category1 = new Category(1L, "Electronics", "Devices and gadgets");
        Category category2 = new Category(2L, "Books", "Books and magazines");

        // Simular el comportamiento del puerto de persistencia
        when(categoryPersistencePort.getAllCategory()).thenReturn(Arrays.asList(category1, category2));

        // Llamar al método que se va a probar
        List<Category> result = categoryUseCase.getAllCategory();

        // Verificar el resultado
        assertEquals(2, result.size());
        assertEquals("Electronics", result.get(0).getName());
        assertEquals("Books", result.get(1).getName());

        // Verificar que el método getAllCategory del puerto fue llamado una vez
        verify(categoryPersistencePort, times(1)).getAllCategory();
    }


}
