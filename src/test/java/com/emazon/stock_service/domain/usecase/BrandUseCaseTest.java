package com.emazon.stock_service.domain.usecase;

import com.emazon.stock_service.domain.exception.DescriptionTooLongException;
import com.emazon.stock_service.domain.exception.NameTooLongException;
import com.emazon.stock_service.domain.model.Brand;
import com.emazon.stock_service.domain.spi.IBrandPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BrandUseCaseTest {
    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveBrand_NameTooLong() {
        Brand brand = new Brand(1L, "A".repeat(51), "Valid description");

        assertThrows(NameTooLongException.class, () -> {
            brandUseCase.saveBrand(brand);
        });

        verify(brandPersistencePort, never()).saveBrand(any(Brand.class));
    }

    @Test
    void testSaveBrand_DescriptionTooLong() {
        Brand brand = new Brand(1L, "Valid name", "A".repeat(121));

        assertThrows(DescriptionTooLongException.class, () -> {
            brandUseCase.saveBrand(brand);
        });

        verify(brandPersistencePort, never()).saveBrand(any(Brand.class));
    }

    @Test
    void testSaveBrand_Success() {
        Brand brand = new Brand(1L, "Valid name", "Valid description");

        brandUseCase.saveBrand(brand);

        verify(brandPersistencePort, times(1)).saveBrand(brand);
    }

    @Test
    void testGetAllBrand() {
        // Configurar las marcas de prueba
        Brand brand1 = new Brand(1L, "Electronics", "Devices and gadgets");
        Brand brand2 = new Brand(2L, "Books", "Books and magazines");

        // Simular el comportamiento del puerto de persistencia
        when(brandPersistencePort.getAllBrand()).thenReturn(Arrays.asList(brand1, brand2));

        // Llamar al método que se va a probar
        List<Brand> result = brandUseCase.getAllBrand();

        // Verificar el resultado
        assertEquals(2, result.size());
        assertEquals("Electronics", result.get(0).getName());
        assertEquals("Books", result.get(1).getName());

        // Verificar que el método getAllBrand del puerto fue llamado una vez
        verify(brandPersistencePort, times(1)).getAllBrand();
    }

}
