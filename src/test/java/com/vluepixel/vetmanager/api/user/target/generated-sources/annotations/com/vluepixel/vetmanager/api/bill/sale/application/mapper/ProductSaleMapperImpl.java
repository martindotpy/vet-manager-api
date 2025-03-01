package com.vluepixel.vetmanager.api.bill.sale.application.mapper;

import com.vluepixel.vetmanager.api.bill.sale.application.dto.ProductSaleDto;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.ProductSale;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.CreateProductSaleRequest;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.UpdateProductSaleRequest;
import com.vluepixel.vetmanager.api.product.category.application.dto.CategoryDto;
import com.vluepixel.vetmanager.api.product.category.domain.model.Category;
import com.vluepixel.vetmanager.api.product.core.application.dto.ProductDto;
import com.vluepixel.vetmanager.api.product.core.domain.model.Product;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;
import com.vluepixel.vetmanager.api.user.core.application.dto.UserDto;
import com.vluepixel.vetmanager.api.user.core.domain.model.User;
import com.vluepixel.vetmanager.api.user.core.domain.model.enums.UserRole;
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
public class ProductSaleMapperImpl implements ProductSaleMapper {

    @Override
    public ProductSale toBuilder(ProductSale t) {
        if ( t == null ) {
            return null;
        }

        ProductSale.ProductSaleBuilder<?, ?> productSale = ProductSale.builder();

        productSale.bill( t.getBill() );
        productSale.discount( t.getDiscount() );
        productSale.id( t.getId() );
        productSale.price( t.getPrice() );
        productSale.seller( t.getSeller() );
        productSale.product( t.getProduct() );
        productSale.quantity( t.getQuantity() );

        return productSale.build();
    }

    @Override
    public ProductSaleDto toDto(ProductSale domain) {
        if ( domain == null ) {
            return null;
        }

        ProductSaleDto.ProductSaleDtoBuilder<?, ?> productSaleDto = ProductSaleDto.builder();

        productSaleDto.discount( domain.getDiscount() );
        productSaleDto.id( domain.getId() );
        productSaleDto.price( domain.getPrice() );
        productSaleDto.seller( userToUserDto( domain.getSeller() ) );
        productSaleDto.product( productToProductDto( domain.getProduct() ) );
        productSaleDto.quantity( domain.getQuantity() );

        return productSaleDto.build();
    }

    @Override
    public ProductSale fromRequest(CreateProductSaleRequest request) {
        if ( request == null ) {
            return null;
        }

        ProductSale.ProductSaleBuilder<?, ?> productSale = ProductSale.builder();

        productSale.bill( mapBillIdToBill( request.getBillId() ) );
        productSale.product( mapProductIdToProduct( request.getProductId() ) );
        productSale.discount( request.getDiscount() );
        productSale.quantity( request.getQuantity() );

        return productSale.build();
    }

    @Override
    public ProductSale fromRequest(UpdateProductSaleRequest request) {
        if ( request == null ) {
            return null;
        }

        ProductSale.ProductSaleBuilder<?, ?> productSale = ProductSale.builder();

        productSale.bill( mapBillIdToBill( request.getBillId() ) );
        productSale.product( mapProductIdToProduct( request.getProductId() ) );
        productSale.discount( request.getDiscount() );
        if ( request.getId() != null ) {
            productSale.id( request.getId().longValue() );
        }
        productSale.quantity( request.getQuantity() );

        return productSale.build();
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.email( StringUtilsMapper.trimString( user.getEmail() ) );
        userDto.firstName( StringUtilsMapper.trimString( user.getFirstName() ) );
        userDto.id( user.getId() );
        userDto.lastName( StringUtilsMapper.trimString( user.getLastName() ) );
        userDto.profileImageUrl( StringUtilsMapper.trimString( user.getProfileImageUrl() ) );
        List<UserRole> list = user.getRoles();
        if ( list != null ) {
            userDto.roles( new ArrayList<UserRole>( list ) );
        }

        return userDto.build();
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

    protected ProductDto productToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto.ProductDtoBuilder productDto = ProductDto.builder();

        productDto.categories( categoryListToCategoryDtoList( product.getCategories() ) );
        productDto.description( StringUtilsMapper.trimString( product.getDescription() ) );
        productDto.id( product.getId() );
        productDto.name( StringUtilsMapper.trimString( product.getName() ) );
        productDto.price( product.getPrice() );
        productDto.quantity( product.getQuantity() );

        return productDto.build();
    }
}
