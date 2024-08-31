package com.emazon.stock_service.infraestruture.configuration;

import com.emazon.stock_service.application.mapper.IBrandRequestMapper;
import com.emazon.stock_service.domain.api.IBrandServicePort;
import com.emazon.stock_service.domain.api.ICategoryServicePort;
import com.emazon.stock_service.domain.spi.IBrandPersistencePort;
import com.emazon.stock_service.domain.spi.ICategoryPersistencePort;
import com.emazon.stock_service.domain.usecase.BrandUseCase;
import com.emazon.stock_service.domain.usecase.CategoryUseCase;
import com.emazon.stock_service.infraestruture.out.jpa.adapter.BrandJpaAdapter;
import com.emazon.stock_service.infraestruture.out.jpa.adapter.CategoryJpaAdapter;
import com.emazon.stock_service.infraestruture.out.jpa.mapper.IBrandEntityMapper;
import com.emazon.stock_service.infraestruture.out.jpa.mapper.ICategoryEntityMapper;
import com.emazon.stock_service.infraestruture.out.jpa.repository.IBrandRepository;
import com.emazon.stock_service.infraestruture.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BenConfiguration {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public IBrandPersistencePort brandPersistencePort(){
        return new BrandJpaAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    public IBrandServicePort brandServicePort(){
        return new BrandUseCase(brandPersistencePort());
    }
}
