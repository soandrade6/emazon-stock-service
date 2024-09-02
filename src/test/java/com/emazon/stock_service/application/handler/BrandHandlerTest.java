package com.emazon.stock_service.application.handler;

import com.emazon.stock_service.application.dto.BrandRequestDto;
import com.emazon.stock_service.application.mapper.IBrandRequestMapper;
import com.emazon.stock_service.domain.api.IBrandServicePort;
import com.emazon.stock_service.domain.model.Brand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class BrandHandlerTest {
    @Mock
    private IBrandServicePort brandServicePort;

    @Mock
    private IBrandRequestMapper brandRequestMapper;

    @InjectMocks
    private BrandHandler brandHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCategory() {
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
}
