package com.emazon.stock_service.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class CategoryResponseDto {
    @Schema(description = "Name of the category", example = "Electronics", required = true)
    private String name;

    @Schema(description = "Description of the category", example = "Devices and gadgets", required = true)
    private String description;

}
