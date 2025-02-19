package com.vluepixel.vetmanager.api.product.category.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;

import com.vluepixel.vetmanager.api.product.category.application.dto.CategoryDto;
import com.vluepixel.vetmanager.api.product.category.domain.model.Category;
import com.vluepixel.vetmanager.api.product.category.domain.request.CreateCategoryRequest;
import com.vluepixel.vetmanager.api.product.category.domain.request.UpdateCategoryRequest;
import com.vluepixel.vetmanager.api.shared.application.mapper.CrudMapper;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;

/**
 * Category mapper.
 */
@Mapper(componentModel = "spring", uses = { StringUtilsMapper.class })
public interface CategoryMapper
        extends CrudMapper<Category, CategoryDto, Category.CategoryBuilder> {
    /**
     * Creates a new {@link Category} builder.
     *
     * @return the builder
     */
    @ObjectFactory
    default Category.CategoryBuilder createCategoryBuilder() {
        return Category.builder();
    }

    /**
     * Create category from request.
     *
     * <ul>
     * <li><strong>Ignores:</strong>
     * <ul>
     * <li><code>id</code></li>
     * </ul>
     * </li>
     * </ul>
     *
     * @param request the create category request.
     * @return the category builder
     */
    @Mapping(target = "id", ignore = true)
    Category.CategoryBuilder fromRequest(CreateCategoryRequest request);

    /**
     * Update category from request.
     *
     * <ul>
     * <li><strong>Ignores:</strong>
     * <ul>
     * <li><code>id</code></li>
     * </ul>
     * </li>
     * </ul>
     *
     * @param request the update category request.
     * @return the category builder
     */
    Category.CategoryBuilder fromRequest(UpdateCategoryRequest request);
}
