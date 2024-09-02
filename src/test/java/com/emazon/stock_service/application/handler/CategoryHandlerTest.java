package com.emazon.stock_service.application.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.emazon.stock_service.application.dto.CategoryRequestDto;
import com.emazon.stock_service.application.dto.CategoryResponseDto;
import com.emazon.stock_service.application.mapper.ICategoryRequestMapper;
import com.emazon.stock_service.application.mapper.ICategoryResponseMapper;
import com.emazon.stock_service.domain.api.ICategoryServicePort;
import com.emazon.stock_service.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.List;

class CategoryHandlerTest {

    @Mock
    private ICategoryServicePort categoryServicePort;

    @Mock
    private ICategoryRequestMapper categoryRequestMapper;

    @Mock
    private ICategoryResponseMapper categoryResponseMapper;

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

    @Test
    void testGetAllCategory() {
        // Datos simulados
        Category category1 = new Category(1L, "Electronics", "Devices and gadgets");
        Category category2 = new Category(2L, "Books", "Printed and digital books");
        List<Category> categories = Arrays.asList(category1, category2);

        CategoryResponseDto categoryResponseDto1 = new CategoryResponseDto();
        categoryResponseDto1.setName("Electronics");
        categoryResponseDto1.setDescription("Devices and gadgets");

        CategoryResponseDto categoryResponseDto2 = new CategoryResponseDto();
        categoryResponseDto2.setName("Books");
        categoryResponseDto2.setDescription("Printed and digital books");

        List<CategoryResponseDto> categoryResponseDtos = Arrays.asList(categoryResponseDto1, categoryResponseDto2);

        // Configuración del comportamiento del puerto y mapper
        when(categoryServicePort.getAllCategory()).thenReturn(categories);
        when(categoryResponseMapper.toResponseList(categories)).thenReturn(categoryResponseDtos);

        // Llamada al método que se va a probar
        List<CategoryResponseDto> result = categoryHandler.getAllCategory();

        // Verificaciones
        assertEquals(categoryResponseDtos, result);
        verify(categoryServicePort, times(1)).getAllCategory();
        verify(categoryResponseMapper, times(1)).toResponseList(categories);
    }

    @Test
    void testGetAllCategoryOrderedByName() {
        // Datos simulados
        Category category1 = new Category(1L, "Books", "Printed and digital books");
        Category category2 = new Category(2L, "Electronics", "Devices and gadgets");
        List<Category> categories = Arrays.asList(category1, category2);

        CategoryResponseDto categoryResponseDto1 = new CategoryResponseDto();
        categoryResponseDto1.setName("Books");
        categoryResponseDto1.setDescription("Printed and digital books");

        CategoryResponseDto categoryResponseDto2 = new CategoryResponseDto();
        categoryResponseDto2.setName("Electronics");
        categoryResponseDto2.setDescription("Devices and gadgets");

        // Configuración del comportamiento del puerto y mapper
        when(categoryServicePort.getAllCategory()).thenReturn(categories);
        when(categoryResponseMapper.toResponse(category1)).thenReturn(categoryResponseDto1);
        when(categoryResponseMapper.toResponse(category2)).thenReturn(categoryResponseDto2);

        // Llamada al método que se va a probar
        Page<CategoryResponseDto> result = categoryHandler.getAllCategoryOrderedByName("asc", 0, 10);

        // Verificaciones
        assertEquals(2, result.getTotalElements());
        assertEquals("Books", result.getContent().get(0).getName());
        assertEquals("Electronics", result.getContent().get(1).getName());
        verify(categoryServicePort, times(1)).getAllCategory();
    }
}


