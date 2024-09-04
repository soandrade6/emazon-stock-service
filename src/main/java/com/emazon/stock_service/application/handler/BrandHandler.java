package com.emazon.stock_service.application.handler;

import com.emazon.stock_service.application.dto.BrandRequestDto;
import com.emazon.stock_service.application.dto.BrandResponseDto;
import com.emazon.stock_service.application.mapper.IBrandRequestMapper;
import com.emazon.stock_service.application.mapper.IBrandResponseMapper;
import com.emazon.stock_service.domain.api.IBrandServicePort;
import com.emazon.stock_service.domain.model.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandHandler implements IBrandHandler{
    private final IBrandServicePort brandServicePort;
    private final IBrandRequestMapper brandRequestMapper;
    private final IBrandResponseMapper brandResponseMapper;

    @Override
    public void saveBrand(BrandRequestDto brandRequestDto) {
      Brand brand = brandRequestMapper.toBrand(brandRequestDto);
      brandServicePort.saveBrand(brand);
    }

    @Override
    public List<BrandResponseDto> getAllBrand() {
        return brandResponseMapper.toResponseList(brandServicePort.getAllBrand());
    }

    @Override
    public Page<BrandResponseDto> getPaginatedBrand(String order, int page, int size) {
        Sort sort = "asc".equalsIgnoreCase(order)
                ? Sort.by("name").descending()
                : Sort.by("name").ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        List<BrandResponseDto> brandResponseDtoList = brandServicePort
                .getAllBrand()
                .stream()
                .map(brandResponseMapper::toResponse)
                .toList();


        Comparator<BrandResponseDto> comparator = "asc".equalsIgnoreCase(order)
                ? Comparator.comparing(BrandResponseDto::getName)
                : Comparator.comparing(BrandResponseDto::getName).reversed();

        List<BrandResponseDto> sortedBrandDto = brandResponseDtoList.stream()
                .sorted(comparator)
                .toList();

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), sortedBrandDto.size());

        return new PageImpl<>(sortedBrandDto.subList(start, end), pageable, sortedBrandDto.size());
    }

}
