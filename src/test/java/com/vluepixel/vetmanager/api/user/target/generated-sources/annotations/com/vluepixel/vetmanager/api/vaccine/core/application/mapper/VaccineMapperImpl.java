package com.vluepixel.vetmanager.api.vaccine.core.application.mapper;

import com.vluepixel.vetmanager.api.bill.sale.application.dto.ProductSaleDto;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.ProductSale;
import com.vluepixel.vetmanager.api.product.category.application.dto.CategoryDto;
import com.vluepixel.vetmanager.api.product.category.domain.model.Category;
import com.vluepixel.vetmanager.api.product.core.application.dto.ProductDto;
import com.vluepixel.vetmanager.api.product.core.domain.model.Product;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;
import com.vluepixel.vetmanager.api.user.core.application.dto.UserDto;
import com.vluepixel.vetmanager.api.user.core.domain.model.User;
import com.vluepixel.vetmanager.api.user.core.domain.model.enums.UserRole;
import com.vluepixel.vetmanager.api.vaccine.core.application.dto.VaccineDto;
import com.vluepixel.vetmanager.api.vaccine.core.domain.model.Vaccine;
import com.vluepixel.vetmanager.api.vaccine.core.domain.request.CreateVaccineRequest;
import com.vluepixel.vetmanager.api.vaccine.core.domain.request.UpdateVaccineRequest;
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
public class VaccineMapperImpl implements VaccineMapper {

    @Override
    public Vaccine.VaccineBuilder toBuilder(Vaccine t) {
        if ( t == null ) {
            return null;
        }

        Vaccine.VaccineBuilder vaccineBuilder = createVaccineBuilder();

        vaccineBuilder.deleted( t.isDeleted() );
        vaccineBuilder.doseInMilliliters( t.getDoseInMilliliters() );
        vaccineBuilder.id( t.getId() );
        vaccineBuilder.name( StringUtilsMapper.trimString( t.getName() ) );
        vaccineBuilder.patient( t.getPatient() );
        vaccineBuilder.productSale( t.getProductSale() );
        vaccineBuilder.providedAt( t.getProvidedAt() );
        vaccineBuilder.vaccinator( t.getVaccinator() );

        return vaccineBuilder;
    }

    @Override
    public VaccineDto toDto(Vaccine domain) {
        if ( domain == null ) {
            return null;
        }

        VaccineDto.VaccineDtoBuilder vaccineDto = VaccineDto.builder();

        vaccineDto.doseInMilliliters( domain.getDoseInMilliliters() );
        vaccineDto.id( domain.getId() );
        vaccineDto.name( StringUtilsMapper.trimString( domain.getName() ) );
        vaccineDto.productSale( productSaleToProductSaleDto( domain.getProductSale() ) );
        vaccineDto.providedAt( domain.getProvidedAt() );
        vaccineDto.vaccinator( userToUserDto( domain.getVaccinator() ) );

        return vaccineDto.build();
    }

    @Override
    public Vaccine.VaccineBuilder fromRequest(CreateVaccineRequest request) {
        if ( request == null ) {
            return null;
        }

        Vaccine.VaccineBuilder vaccineBuilder = createVaccineBuilder();

        vaccineBuilder.patient( mapPatientIdToPatient( request.getPatientId() ) );
        vaccineBuilder.vaccinator( mapVaccinatorIdToUser( request.getVaccinatorId() ) );
        vaccineBuilder.productSale( mapProductSaleIdToProductSale( request.getProductSaleId() ) );
        vaccineBuilder.doseInMilliliters( request.getDoseInMilliliters() );
        vaccineBuilder.name( StringUtilsMapper.trimString( request.getName() ) );
        vaccineBuilder.providedAt( request.getProvidedAt() );

        return vaccineBuilder;
    }

    @Override
    public Vaccine.VaccineBuilder fromRequest(UpdateVaccineRequest request) {
        if ( request == null ) {
            return null;
        }

        Vaccine.VaccineBuilder vaccineBuilder = createVaccineBuilder();

        vaccineBuilder.vaccinator( mapVaccinatorIdToUser( request.getVaccinatorId() ) );
        vaccineBuilder.productSale( mapProductSaleIdToProductSale( request.getProductSaleId() ) );
        vaccineBuilder.doseInMilliliters( request.getDoseInMilliliters() );
        vaccineBuilder.id( request.getId() );
        vaccineBuilder.name( StringUtilsMapper.trimString( request.getName() ) );
        vaccineBuilder.providedAt( request.getProvidedAt() );

        return vaccineBuilder;
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

    protected ProductSaleDto productSaleToProductSaleDto(ProductSale productSale) {
        if ( productSale == null ) {
            return null;
        }

        ProductSaleDto.ProductSaleDtoBuilder<?, ?> productSaleDto = ProductSaleDto.builder();

        productSaleDto.discount( productSale.getDiscount() );
        productSaleDto.id( productSale.getId() );
        productSaleDto.price( productSale.getPrice() );
        productSaleDto.seller( userToUserDto( productSale.getSeller() ) );
        productSaleDto.product( productToProductDto( productSale.getProduct() ) );
        productSaleDto.quantity( productSale.getQuantity() );

        return productSaleDto.build();
    }
}
