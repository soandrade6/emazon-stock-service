package com.emazon.stock_service.infraestruture.input.rest;

import com.emazon.stock_service.application.dto.CategoryRequestDto;
import com.emazon.stock_service.application.dto.CategoryResponseDto;
import com.emazon.stock_service.application.handler.ICategoryHandler;
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

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryRestController {

    private final ICategoryHandler categoryHandler;
    @PostMapping
    @Operation(
            summary = "Create a new category",
            description = "Endpoint to create a new category in the system.",
            tags = { "Category" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ResponseEntity<Void> saveCategory(@RequestBody @Parameter(description = "Category request data", required = true)
                                                 CategoryRequestDto categoryRequestDto){
        categoryHandler.saveCategory(categoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(
            summary = "Get all categories",
            description = "Retrieve a list of all categories."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of categories",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ResponseEntity<List<CategoryResponseDto>> getAllCategory(){
        return ResponseEntity.ok(categoryHandler.getAllCategory());
    }

    @GetMapping("/{order}")
    @Operation(
            summary = "Get categories ordered by name",
            description = "Retrieve a paginated list of categories ordered by name in ascending or descending order."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved paginated and ordered list of categories",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid order parameter",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public Page<CategoryResponseDto> getAllPaginatedCategory(
            @PathVariable String order,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return categoryHandler.getAllCategoryOrderedByName(order, page, size);
    }


}
