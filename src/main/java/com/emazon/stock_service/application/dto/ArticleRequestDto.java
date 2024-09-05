package com.emazon.stock_service.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class ArticleRequestDto {
    @Schema(description = "Nombre del artículo")
    private String name;

    @Schema(description = "Descripción del artículo")
    private String description;

    @Schema(description = "Cantidad disponible")
    private int quantity;

    @Schema(description = "Precio del artículo")
    private double price;

    @Schema(description = "IDs de categorías asociadas")
    private List<Long> categoryIds;
}
