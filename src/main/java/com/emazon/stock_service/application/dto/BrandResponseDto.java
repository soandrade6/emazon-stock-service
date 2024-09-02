package com.emazon.stock_service.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandResponseDto {
    @Schema(description = "Name of the brand", example = "Apple")
    private String name;

    @Schema(description = "Description of the brand", example = "Hardware y software")
    private String description;
}

