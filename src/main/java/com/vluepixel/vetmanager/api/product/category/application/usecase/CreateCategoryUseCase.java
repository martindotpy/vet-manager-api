package com.vluepixel.vetmanager.api.product.category.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.product.category.application.dto.CategoryDto;
import com.vluepixel.vetmanager.api.product.category.application.mapper.CategoryMapper;
import com.vluepixel.vetmanager.api.product.category.application.port.in.CreateCategoryPort;
import com.vluepixel.vetmanager.api.product.category.domain.model.Category;
import com.vluepixel.vetmanager.api.product.category.domain.repository.CategoryRepository;
import com.vluepixel.vetmanager.api.product.category.domain.request.CreateCategoryRequest;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Create category use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class CreateCategoryUseCase implements CreateCategoryPort {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto create(CreateCategoryRequest request) {
        MDC.put("operationId", "Category name " + request.getName());
        log.info("Creating category");

        Category newCategory = categoryMapper.fromRequest(request).build();
        newCategory = categoryRepository.save(newCategory);

        log.info("Category created");

        return categoryMapper.toDto(newCategory);
    }
}
