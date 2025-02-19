package com.vluepixel.vetmanager.api.product.category.application.usecase;

import static com.vluepixel.vetmanager.api.shared.adapter.in.util.AnsiShortcuts.fgBrightBlue;
import static com.vluepixel.vetmanager.api.shared.adapter.in.util.AnsiShortcuts.fgBrightGreen;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.product.category.application.dto.CategoryDto;
import com.vluepixel.vetmanager.api.product.category.application.mapper.CategoryMapper;
import com.vluepixel.vetmanager.api.product.category.application.port.in.FindCategoryPort;
import com.vluepixel.vetmanager.api.product.category.domain.model.Category;
import com.vluepixel.vetmanager.api.product.category.domain.repository.CategoryRepository;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;
import com.vluepixel.vetmanager.api.shared.domain.criteria.PaginatedCriteria;
import com.vluepixel.vetmanager.api.shared.domain.exception.NotFoundException;
import com.vluepixel.vetmanager.api.shared.domain.query.Paginated;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Find category use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public final class FindCategoryUseCase implements FindCategoryPort {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Paginated<CategoryDto> findPaginatedBy(PaginatedCriteria criteria) {
        MDC.put("operationId", "Category by criteria: " + fgBrightBlue(criteria.hashCode()));
        log.info("Finding category by {}",
                fgBrightBlue(criteria));

        Paginated<Category> paginatedCategory = categoryRepository.findPaginatedBy(criteria);

        log.info("{} category found",
                fgBrightGreen(paginatedCategory.getContent().size()));

        return paginatedCategory.map(categoryMapper::toDto);
    }

    @Override
    public CategoryDto findById(Integer id) {
        MDC.put("operationId", "Category id " + id);
        log.info("Finding category by id");

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Category.class, id));

        log.info("Category found");

        return categoryMapper.toDto(category);
    }
}
