package com.emazon.stock_service.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandRequestDto {
    @Schema(description = "Name of the brand", example = "Apple", required = true)
    private String name;

    @Schema(description = "Description of the brand", example = "Hardware y software", required = true)
    private String description;
}
