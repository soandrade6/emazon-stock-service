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
    @PostMapping
    public ResponseEntity<Void> saveCategory(@RequestBody @Parameter(description = "Category request data", required = true)
                                                 CategoryRequestDto categoryRequestDto){
        categoryHandler.saveCategory(categoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategory(){
        return ResponseEntity.ok(categoryHandler.getAllCategory());
    }

    @GetMapping("/{order}")
    public Page<CategoryResponseDto> getCategories(
            @PathVariable String order,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return categoryHandler.getAllCategoryOrderedByName(order, page, size);
    }


}
