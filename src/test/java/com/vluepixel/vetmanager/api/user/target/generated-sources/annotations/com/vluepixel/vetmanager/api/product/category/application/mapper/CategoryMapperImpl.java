package com.vluepixel.vetmanager.api.product.category.application.mapper;

import com.vluepixel.vetmanager.api.product.category.application.dto.CategoryDto;
import com.vluepixel.vetmanager.api.product.category.domain.model.Category;
import com.vluepixel.vetmanager.api.product.category.domain.request.CreateCategoryRequest;
import com.vluepixel.vetmanager.api.product.category.domain.request.UpdateCategoryRequest;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-28T20:38:37-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.41.0.z20250213-2037, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category.CategoryBuilder toBuilder(Category t) {
        if ( t == null ) {
            return null;
        }

        Category.CategoryBuilder categoryBuilder = createCategoryBuilder();

        categoryBuilder.id( t.getId() );
        categoryBuilder.name( StringUtilsMapper.trimString( t.getName() ) );

        return categoryBuilder;
    }

    @Override
    public CategoryDto toDto(Category domain) {
        if ( domain == null ) {
            return null;
        }

        CategoryDto.CategoryDtoBuilder categoryDto = CategoryDto.builder();

        if ( domain.getId() != null ) {
            categoryDto.id( domain.getId().longValue() );
        }
        categoryDto.name( StringUtilsMapper.trimString( domain.getName() ) );

        return categoryDto.build();
    }

    @Override
    public Category.CategoryBuilder fromRequest(CreateCategoryRequest request) {
        if ( request == null ) {
            return null;
        }

        Category.CategoryBuilder categoryBuilder = createCategoryBuilder();

        categoryBuilder.name( StringUtilsMapper.trimString( request.getName() ) );

        return categoryBuilder;
    }

    @Override
    public Category.CategoryBuilder fromRequest(UpdateCategoryRequest request) {
        if ( request == null ) {
            return null;
        }

        Category.CategoryBuilder categoryBuilder = createCategoryBuilder();

        categoryBuilder.id( request.getId() );
        categoryBuilder.name( StringUtilsMapper.trimString( request.getName() ) );

        return categoryBuilder;
    }
}
