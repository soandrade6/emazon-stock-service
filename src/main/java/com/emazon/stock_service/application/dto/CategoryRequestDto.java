package com.emazon.stock_service.application.dto;

import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
public class CategoryRequestDto {
    @Schema(description = "Name of the category", example = "Electronics")
    private String name;

    @Schema(description = "Description of the category", example = "Devices and gadgets")
    private String description;
}
