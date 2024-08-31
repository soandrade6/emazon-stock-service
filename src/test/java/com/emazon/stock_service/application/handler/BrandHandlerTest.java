package com.emazon.stock_service.application.handler;

import com.emazon.stock_service.application.dto.BrandRequestDto;
import com.emazon.stock_service.application.dto.CategoryRequestDto;
import com.emazon.stock_service.application.mapper.IBrandRequestMapper;
import com.emazon.stock_service.application.mapper.IBrandResponseMapper;
import com.emazon.stock_service.application.mapper.ICategoryRequestMapper;
import com.emazon.stock_service.application.mapper.ICategoryResponseMapper;
import com.emazon.stock_service.domain.api.IBrandServicePort;
import com.emazon.stock_service.domain.api.ICategoryServicePort;
import com.emazon.stock_service.domain.model.Brand;
import com.emazon.stock_service.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class BrandHandlerTest {
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
