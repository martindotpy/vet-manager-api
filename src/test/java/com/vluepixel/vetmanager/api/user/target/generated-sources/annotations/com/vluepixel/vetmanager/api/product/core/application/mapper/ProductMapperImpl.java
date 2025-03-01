package com.vluepixel.vetmanager.api.product.core.application.mapper;

import com.vluepixel.vetmanager.api.product.category.application.dto.CategoryDto;
import com.vluepixel.vetmanager.api.product.category.domain.model.Category;
import com.vluepixel.vetmanager.api.product.core.application.dto.ProductDto;
import com.vluepixel.vetmanager.api.product.core.domain.model.Product;
import com.vluepixel.vetmanager.api.product.core.domain.request.CreateProductRequest;
import com.vluepixel.vetmanager.api.product.core.domain.request.UpdateProductRequest;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-28T20:38:37-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.41.0.z20250213-2037, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product.ProductBuilder toBuilder(Product t) {
        if ( t == null ) {
            return null;
        }

        Product.ProductBuilder productBuilder = createProductBuilder();

        List<Category> list = t.getCategories();
        if ( list != null ) {
            productBuilder.categories( new ArrayList<Category>( list ) );
        }
        productBuilder.description( StringUtilsMapper.trimString( t.getDescription() ) );
        productBuilder.id( t.getId() );
        productBuilder.name( StringUtilsMapper.trimString( t.getName() ) );
        productBuilder.price( t.getPrice() );
        productBuilder.quantity( t.getQuantity() );
        productBuilder.updatedAt( t.getUpdatedAt() );

        return productBuilder;
    }

    @Override
    public ProductDto toDto(Product domain) {
        if ( domain == null ) {
            return null;
        }

        ProductDto.ProductDtoBuilder productDto = ProductDto.builder();

        productDto.categories( categoryListToCategoryDtoList( domain.getCategories() ) );
        productDto.description( StringUtilsMapper.trimString( domain.getDescription() ) );
        productDto.id( domain.getId() );
        productDto.name( StringUtilsMapper.trimString( domain.getName() ) );
        productDto.price( domain.getPrice() );
        productDto.quantity( domain.getQuantity() );

        return productDto.build();
    }

    @Override
    public Product.ProductBuilder fromRequest(CreateProductRequest request) {
        if ( request == null ) {
            return null;
        }

        Product.ProductBuilder productBuilder = createProductBuilder();

        productBuilder.categories( mapCategoryIdsToCategories( request.getCategoryIds() ) );
        productBuilder.description( StringUtilsMapper.trimString( request.getDescription() ) );
        productBuilder.name( StringUtilsMapper.trimString( request.getName() ) );
        productBuilder.price( request.getPrice() );
        productBuilder.quantity( request.getQuantity() );

        return productBuilder;
    }

    @Override
    public Product.ProductBuilder fromRequest(UpdateProductRequest request) {
        if ( request == null ) {
            return null;
        }

        Product.ProductBuilder productBuilder = createProductBuilder();

        productBuilder.categories( mapCategoryIdsToCategories( request.getCategoryIds() ) );
        productBuilder.description( StringUtilsMapper.trimString( request.getDescription() ) );
        productBuilder.id( request.getId() );
        productBuilder.name( StringUtilsMapper.trimString( request.getName() ) );
        productBuilder.price( request.getPrice() );
        productBuilder.quantity( request.getQuantity() );

        return productBuilder;
    }

    protected CategoryDto categoryToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto.CategoryDtoBuilder categoryDto = CategoryDto.builder();

        if ( category.getId() != null ) {
            categoryDto.id( category.getId().longValue() );
        }
        categoryDto.name( StringUtilsMapper.trimString( category.getName() ) );

        return categoryDto.build();
    }

    protected List<CategoryDto> categoryListToCategoryDtoList(List<Category> list) {
        if ( list == null ) {
            return null;
        }

        List<CategoryDto> list1 = new ArrayList<CategoryDto>( list.size() );
        for ( Category category : list ) {
            list1.add( categoryToCategoryDto( category ) );
        }

        return list1;
    }
}
