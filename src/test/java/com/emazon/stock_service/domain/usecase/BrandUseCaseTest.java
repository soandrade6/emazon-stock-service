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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BrandUseCaseTest {
    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCategory_NameTooLong() {
        Brand brand = new Brand(1L, "A".repeat(51), "Valid description");

        assertThrows(NameTooLongException.class, () -> {
            brandUseCase.saveBrand(brand);
        });

        verify(brandPersistencePort, never()).saveBrand(any(Brand.class));
    }

    @Test
    void testSaveCategory_DescriptionTooLong() {
        Brand brand = new Brand(1L, "Valid name", "A".repeat(121));

        assertThrows(DescriptionTooLongException.class, () -> {
            brandUseCase.saveBrand(brand);
        });

        verify(brandPersistencePort, never()).saveBrand(any(Brand.class));
    }

    @Test
    void testSaveCategory_Success() {
        Brand brand = new Brand(1L, "Valid name", "Valid description");

        brandUseCase.saveBrand(brand);

        verify(brandPersistencePort, times(1)).saveBrand(brand);
    }
}
