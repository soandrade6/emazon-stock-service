package com.emazon.stock_service.application.handler;

import com.emazon.stock_service.application.dto.CategoryRequestDto;
import com.emazon.stock_service.application.dto.CategoryResponseDto;
import com.emazon.stock_service.application.mapper.ICategoryRequestMapper;
import com.emazon.stock_service.application.mapper.ICategoryResponseMapper;
import com.emazon.stock_service.domain.api.ICategoryServicePort;
import com.emazon.stock_service.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;


    @Override
    public void saveCategory(CategoryRequestDto categoryRequestDto) {
        Category category = categoryRequestMapper.toCategory(categoryRequestDto);
        categoryServicePort.saveCategory(category);
    }

    @Override
    public List<CategoryResponseDto> getAllCategory() {
        return categoryResponseMapper.toResponseList(categoryServicePort.getAllCategory());
    }
    @Override
    public Page<CategoryResponseDto> getAllCategoryOrderedByName(String order, int page, int size) {
        Sort sort = "asc".equalsIgnoreCase(order)
                ? Sort.by("name").descending()
                : Sort.by("name").ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        List<CategoryResponseDto> categoryResponseDtoList = categoryServicePort
                .getAllCategory()
                .stream()
                .map(categoryResponseMapper::toResponse)
                .toList();

        Comparator<CategoryResponseDto> comparator = "asc".equalsIgnoreCase(order)
                ? Comparator.comparing(CategoryResponseDto::getName)
                : Comparator.comparing(CategoryResponseDto::getName).reversed();

        List<CategoryResponseDto> sortedCategoryDto = categoryResponseDtoList.stream()
                .sorted(comparator)
                .toList();

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), sortedCategoryDto.size());

        return new PageImpl<>(sortedCategoryDto.subList(start, end), pageable, sortedCategoryDto.size());



}}
