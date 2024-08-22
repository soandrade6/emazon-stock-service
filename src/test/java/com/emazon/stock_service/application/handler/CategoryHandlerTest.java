package com.emazon.stock_service.application.handler;

import static org.mockito.Mockito.*;

import com.emazon.stock_service.application.dto.CategoryRequestDto;
import com.emazon.stock_service.application.mapper.ICategoryRequestMapper;
import com.emazon.stock_service.domain.api.ICategoryServicePort;
import com.emazon.stock_service.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CategoryHandlerTest {

    @Mock
    private ICategoryServicePort categoryServicePort;

    @Mock
    private ICategoryRequestMapper categoryRequestMapper;

    @InjectMocks
    private CategoryHandler categoryHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCategory() {
        // Configuración del DTO de solicitud
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto();
        categoryRequestDto.setName("Electronics");
        categoryRequestDto.setDescription("Devices and gadgets");

        // Configuración del comportamiento del mapper
        Category category = new Category(1L, "Test", "Devices and gadgets");
        when(categoryRequestMapper.toCategory(categoryRequestDto)).thenReturn(category);

        // Llamada al método que se va a probar
        categoryHandler.saveCategory(categoryRequestDto);

        // Verificaciones
        verify(categoryRequestMapper, times(1)).toCategory(categoryRequestDto);
        verify(categoryServicePort, times(1)).saveCategory(category);
    }
}

