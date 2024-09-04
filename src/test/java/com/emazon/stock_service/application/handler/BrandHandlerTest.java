package com.emazon.stock_service.application.handler;

import com.emazon.stock_service.application.dto.BrandRequestDto;
import com.emazon.stock_service.application.dto.BrandResponseDto;
import com.emazon.stock_service.application.mapper.IBrandRequestMapper;
import com.emazon.stock_service.application.mapper.IBrandResponseMapper;
import com.emazon.stock_service.domain.api.IBrandServicePort;
import com.emazon.stock_service.domain.model.Brand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BrandHandlerTest {
    @Mock
    private IBrandServicePort brandServicePort;

    @Mock
    private IBrandRequestMapper brandRequestMapper;

    @Mock
    private IBrandResponseMapper brandResponseMapper;

    @InjectMocks
    private BrandHandler brandHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveBrand() {
        // Configuración del DTO de solicitud
        BrandRequestDto brandRequestDto = new BrandRequestDto();
        brandRequestDto.setName("Apple");
        brandRequestDto.setDescription("Devices and gadgets");

        // Configuración del comportamiento del mapper
        Brand brand = new Brand(1L, "Test", "Devices and gadgets");
        when(brandRequestMapper.toBrand(brandRequestDto)).thenReturn(brand);

        // Llamada al método que se va a probar
        brandHandler.saveBrand(brandRequestDto);

        // Verificaciones
        verify(brandRequestMapper, times(1)).toBrand(brandRequestDto);
        verify(brandServicePort, times(1)).saveBrand(brand);
    }

    @Test
    void testGetAllBrand() {
        Brand brand1 = new Brand(1L, "Electronics", "Devices and gadgets");
        Brand brand2 = new Brand(2L, "Books", "Printed and digital books");
        List<Brand> brands = Arrays.asList(brand1, brand2);

        BrandResponseDto brandResponseDto1 = new BrandResponseDto();
        brandResponseDto1.setName("Electronics");
        brandResponseDto1.setDescription("Devices and gadgets");

        BrandResponseDto brandResponseDto2 = new BrandResponseDto();
        brandResponseDto2.setName("Books");
        brandResponseDto2.setDescription("Printed and digital books");

        List<BrandResponseDto> brandResponseDtos = Arrays.asList(brandResponseDto1, brandResponseDto2);

        // Configuración del comportamiento del puerto y mapper
        when(brandServicePort.getAllBrand()).thenReturn(brands);
        when(brandResponseMapper.toResponseList(brands)).thenReturn(brandResponseDtos);

        // Llamada al método que se va a probar
        List<BrandResponseDto> result = brandHandler.getAllBrand();

        // Verificaciones
        assertEquals(brandResponseDtos, result);
        verify(brandServicePort, times(1)).getAllBrand();
        verify(brandResponseMapper, times(1)).toResponseList(brands);
    }

    @Test
    void testGetPaginatedBrand() {
        // Datos simulados
        Brand brand1 = new Brand(1L, "Nike", "Sportswear brand");
        Brand brand2 = new Brand(2L, "Adidas", "Sportswear brand");
        List<Brand> brands = Arrays.asList(brand1, brand2);

        BrandResponseDto brandResponseDto1 = new BrandResponseDto();
        brandResponseDto1.setName("Nike");
        brandResponseDto1.setDescription("Sportswear brand");

        BrandResponseDto brandResponseDto2 = new BrandResponseDto();
        brandResponseDto2.setName("Adidas");
        brandResponseDto2.setDescription("Sportswear brand");

        // Configuración del comportamiento del puerto y mapper
        when(brandServicePort.getAllBrand()).thenReturn(brands);
        when(brandResponseMapper.toResponse(brand1)).thenReturn(brandResponseDto1);
        when(brandResponseMapper.toResponse(brand2)).thenReturn(brandResponseDto2);

        // Llamada al método que se va a probar
        Page<BrandResponseDto> result = brandHandler.getPaginatedBrand("asc", 0, 10);

        // Verificaciones
        assertEquals(2, result.getTotalElements());
        assertEquals("Adidas", result.getContent().get(0).getName());
        assertEquals("Nike", result.getContent().get(1).getName());
        verify(brandServicePort, times(1)).getAllBrand();
    }
}
