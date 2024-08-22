package com.emazon.stock_service.infraestruture.configuration;

import com.emazon.stock_service.domain.api.ICategoryServicePort;
import com.emazon.stock_service.domain.spi.ICategoryPersistencePort;
import com.emazon.stock_service.domain.usecase.CategoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BenConfiguration {

    //@Bean
    //public ICategoryServicePort categoryServicePort() {
        //return new CategoryUseCase(categoryPersistencePort());
    //}

    //private ICategoryPersistencePort categoryPersistencePort() {
    //}
}
