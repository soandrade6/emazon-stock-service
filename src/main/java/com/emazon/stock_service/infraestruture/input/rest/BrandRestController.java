package com.emazon.stock_service.infraestruture.input.rest;

import com.emazon.stock_service.application.dto.BrandRequestDto;
import com.emazon.stock_service.application.dto.BrandResponseDto;
import com.emazon.stock_service.application.dto.CategoryResponseDto;
import com.emazon.stock_service.application.handler.IBrandHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandRestController {

    private final IBrandHandler brandHandler;

    @PostMapping
    @Operation(
            summary = "Create a new brand",
            description = "Endpoint to create a new brand in the system.",
            tags = { "Brand" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ResponseEntity<Void> saveBrand(@RequestBody @Parameter(description = "Category request data", required = true)
                                          BrandRequestDto brandRequestDto){
        brandHandler.saveBrand(brandRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{order}")
    @Operation(
            summary = "Get brands ordered by name",
            description = "Retrieve a paginated list of brands ordered by name in ascending or descending order."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved paginated and ordered list of brands",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BrandResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid order parameter",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public Page<BrandResponseDto> getAllPaginatedBrand(
            @PathVariable String order,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return brandHandler.getPaginatedBrand(order, page,size);
    }
}
