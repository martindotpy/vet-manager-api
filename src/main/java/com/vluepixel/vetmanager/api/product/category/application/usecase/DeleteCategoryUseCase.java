package com.vluepixel.vetmanager.api.product.category.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.product.category.application.port.in.DeleteCategoryPort;
import com.vluepixel.vetmanager.api.product.category.domain.repository.CategoryRepository;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Delete category use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class DeleteCategoryUseCase implements DeleteCategoryPort {
    private final CategoryRepository categoryRepository;

    @Override
    public void deleteById(Integer id) {
        MDC.put("operationId", "Category id " + id);
        log.info("Deleting category by id");

        categoryRepository.deleteById(id);

        log.info("Category deleted");
    }
}
