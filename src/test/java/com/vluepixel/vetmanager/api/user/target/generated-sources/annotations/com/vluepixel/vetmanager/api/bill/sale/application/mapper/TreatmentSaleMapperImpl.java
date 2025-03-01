package com.vluepixel.vetmanager.api.bill.sale.application.mapper;

import com.vluepixel.vetmanager.api.bill.sale.application.dto.TreatmentSaleDto;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.TreatmentSale;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.CreateTreatmentSaleRequest;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.UpdateTreatmentSaleRequest;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.application.dto.TreatmentDto;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.domain.model.Treatment;
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
public class TreatmentSaleMapperImpl implements TreatmentSaleMapper {

    @Override
    public TreatmentSale toBuilder(TreatmentSale t) {
        if ( t == null ) {
            return null;
        }

        TreatmentSale.TreatmentSaleBuilder<?, ?> treatmentSale = TreatmentSale.builder();

        treatmentSale.bill( t.getBill() );
        treatmentSale.discount( t.getDiscount() );
        treatmentSale.id( t.getId() );
        treatmentSale.price( t.getPrice() );
        treatmentSale.seller( t.getSeller() );
        treatmentSale.treatment( t.getTreatment() );

        return treatmentSale.build();
    }

    @Override
    public TreatmentSaleDto toDto(TreatmentSale domain) {
        if ( domain == null ) {
            return null;
        }

        TreatmentSaleDto.TreatmentSaleDtoBuilder<?, ?> treatmentSaleDto = TreatmentSaleDto.builder();

        treatmentSaleDto.discount( domain.getDiscount() );
        treatmentSaleDto.id( domain.getId() );
        treatmentSaleDto.price( domain.getPrice() );
        treatmentSaleDto.seller( userToUserDto( domain.getSeller() ) );
        treatmentSaleDto.treatment( treatmentToTreatmentDto( domain.getTreatment() ) );

        return treatmentSaleDto.build();
    }

    @Override
    public TreatmentSale fromRequest(CreateTreatmentSaleRequest request) {
        if ( request == null ) {
            return null;
        }

        TreatmentSale.TreatmentSaleBuilder<?, ?> treatmentSale = TreatmentSale.builder();

        treatmentSale.bill( mapBillIdToBill( request.getBillId() ) );
        treatmentSale.treatment( mapTreatmentIdToTreatment( request.getTreatmentId() ) );
        treatmentSale.discount( request.getDiscount() );
        treatmentSale.price( request.getPrice() );

        return treatmentSale.build();
    }

    @Override
    public TreatmentSale fromRequest(UpdateTreatmentSaleRequest request) {
        if ( request == null ) {
            return null;
        }

        TreatmentSale.TreatmentSaleBuilder<?, ?> treatmentSale = TreatmentSale.builder();

        treatmentSale.bill( mapBillIdToBill( request.getBillId() ) );
        treatmentSale.treatment( mapTreatmentIdToTreatment( request.getTreatmentId() ) );
        treatmentSale.discount( request.getDiscount() );
        if ( request.getId() != null ) {
            treatmentSale.id( request.getId().longValue() );
        }
        treatmentSale.price( request.getPrice() );

        return treatmentSale.build();
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

    protected TreatmentDto treatmentToTreatmentDto(Treatment treatment) {
        if ( treatment == null ) {
            return null;
        }

        TreatmentDto.TreatmentDtoBuilder treatmentDto = TreatmentDto.builder();

        treatmentDto.description( StringUtilsMapper.trimString( treatment.getDescription() ) );
        treatmentDto.id( treatment.getId() );
        treatmentDto.order( treatment.getOrder() );

        return treatmentDto.build();
    }
}
