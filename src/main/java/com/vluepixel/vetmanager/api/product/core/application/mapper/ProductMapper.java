package com.vluepixel.vetmanager.api.product.core.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;

import com.vluepixel.vetmanager.api.product.category.domain.model.Category;
import com.vluepixel.vetmanager.api.product.core.application.dto.ProductDto;
import com.vluepixel.vetmanager.api.product.core.domain.model.Product;
import com.vluepixel.vetmanager.api.product.core.domain.request.CreateProductRequest;
import com.vluepixel.vetmanager.api.product.core.domain.request.UpdateProductRequest;
import com.vluepixel.vetmanager.api.shared.application.mapper.CrudMapper;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;

/**
 * Product mapper.
 */
@Mapper(componentModel = "spring", uses = { StringUtilsMapper.class })
public interface ProductMapper
        extends CrudMapper<Product, ProductDto, Product.ProductBuilder> {
    /**
     * Creates a new {@link Product} builder.
     *
     * @return the builder
     */
    @ObjectFactory
    default Product.ProductBuilder createProductBuilder() {
        return Product.builder();
    }

    /**
     * Create product from request.
     *
     * <ul>
     * <li><strong>Ignores:</strong>
     * <ul>
     * <li><code>id</code></li>
     * <li><code>updatedAt</code></li>
     * </ul>
     * </li>
     * </ul>
     *
     * @param request the create product request.
     * @return the product builder
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "categories", source = "categoryIds")
    Product.ProductBuilder fromRequest(CreateProductRequest request);

    /**
     * Update product from request.
     *
     * <ul>
     * <li><strong>Ignores:</strong>
     * <ul>
     * <li><code>id</code></li>
     * <li><code>updatedAt</code></li>
     * </ul>
     * </li>
     * </ul>
     *
     * @param request the update product request.
     * @return the product builder
     */
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "categories", source = "categoryIds")
    Product.ProductBuilder fromRequest(UpdateProductRequest request);

    /**
     * Maps category ids to categories.
     *
     * @param categoryIds the category ids.
     * @return the categories
     */
    default List<Category> mapCategoryIdsToCategories(List<Integer> categoryIds) {
        if (categoryIds == null) {
            return null;
        }

        return categoryIds.stream().map(id -> Category.builder().id(id).build()).toList();
    }
}
