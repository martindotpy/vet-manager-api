package com.vluepixel.vetmanager.api.product.category.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.product.category.application.dto.CategoryDto;
import com.vluepixel.vetmanager.api.product.category.application.mapper.CategoryMapper;
import com.vluepixel.vetmanager.api.product.category.application.port.in.UpdateCategoryPort;
import com.vluepixel.vetmanager.api.product.category.domain.model.Category;
import com.vluepixel.vetmanager.api.product.category.domain.repository.CategoryRepository;
import com.vluepixel.vetmanager.api.product.category.domain.request.UpdateCategoryRequest;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Update category use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class UpdateCategoryUseCase implements UpdateCategoryPort {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto update(UpdateCategoryRequest request) {
        MDC.put("operationId", "Category id " + request.getId());
        log.info("Updating category");

        Category updatedCategory = categoryMapper.fromRequest(request).build();
        updatedCategory = categoryRepository.save(updatedCategory);

        log.info("Category updated");

        return categoryMapper.toDto(updatedCategory);
    }
}
