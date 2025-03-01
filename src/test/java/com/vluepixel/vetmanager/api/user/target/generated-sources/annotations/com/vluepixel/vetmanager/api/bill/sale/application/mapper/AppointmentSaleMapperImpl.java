package com.vluepixel.vetmanager.api.bill.sale.application.mapper;

import com.vluepixel.vetmanager.api.appointment.core.application.dto.AppointmentDto;
import com.vluepixel.vetmanager.api.appointment.core.domain.model.Appointment;
import com.vluepixel.vetmanager.api.appointment.details.application.dto.AppointmentDetailsDto;
import com.vluepixel.vetmanager.api.appointment.details.domain.model.AppointmentDetails;
import com.vluepixel.vetmanager.api.appointment.type.application.dto.AppointmentTypeDto;
import com.vluepixel.vetmanager.api.appointment.type.domain.model.AppointmentType;
import com.vluepixel.vetmanager.api.bill.sale.application.dto.AppointmentSaleDto;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.AppointmentSale;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.CreateAppointmentSaleRequest;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.UpdateAppointmentSaleRequest;
import com.vluepixel.vetmanager.api.patient.core.application.mapper.PatientMapper;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;
import com.vluepixel.vetmanager.api.user.core.application.dto.UserDto;
import com.vluepixel.vetmanager.api.user.core.domain.model.User;
import com.vluepixel.vetmanager.api.user.core.domain.model.enums.UserRole;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-28T20:38:37-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.41.0.z20250213-2037, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class AppointmentSaleMapperImpl implements AppointmentSaleMapper {

    @Autowired
    private PatientMapper patientMapper;

    @Override
    public AppointmentSaleDto toDto(AppointmentSale domain) {
        if ( domain == null ) {
            return null;
        }

        AppointmentSaleDto.AppointmentSaleDtoBuilder<?, ?> appointmentSaleDto = AppointmentSaleDto.builder();

        appointmentSaleDto.discount( domain.getDiscount() );
        appointmentSaleDto.id( domain.getId() );
        appointmentSaleDto.price( domain.getPrice() );
        appointmentSaleDto.seller( userToUserDto( domain.getSeller() ) );
        appointmentSaleDto.appointment( appointmentToAppointmentDto( domain.getAppointment() ) );

        return appointmentSaleDto.build();
    }

    @Override
    public AppointmentSale fromRequest(CreateAppointmentSaleRequest request) {
        if ( request == null ) {
            return null;
        }

        AppointmentSale.AppointmentSaleBuilder<?, ?> appointmentSale = AppointmentSale.builder();

        appointmentSale.bill( mapBillIdToBill( request.getBillId() ) );
        appointmentSale.appointment( mapAppointmentIdToAppointment( request.getAppointmentId() ) );
        appointmentSale.discount( request.getDiscount() );

        return appointmentSale.build();
    }

    @Override
    public AppointmentSale fromRequest(UpdateAppointmentSaleRequest request) {
        if ( request == null ) {
            return null;
        }

        AppointmentSale.AppointmentSaleBuilder<?, ?> appointmentSale = AppointmentSale.builder();

        appointmentSale.bill( mapBillIdToBill( request.getBillId() ) );
        appointmentSale.appointment( mapAppointmentIdToAppointment( request.getAppointmentId() ) );
        appointmentSale.discount( request.getDiscount() );
        if ( request.getId() != null ) {
            appointmentSale.id( request.getId().longValue() );
        }

        return appointmentSale.build();
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

    protected AppointmentTypeDto appointmentTypeToAppointmentTypeDto(AppointmentType appointmentType) {
        if ( appointmentType == null ) {
            return null;
        }

        AppointmentTypeDto.AppointmentTypeDtoBuilder appointmentTypeDto = AppointmentTypeDto.builder();

        appointmentTypeDto.durationInMinutes( appointmentType.getDurationInMinutes() );
        if ( appointmentType.getId() != null ) {
            appointmentTypeDto.id( appointmentType.getId().longValue() );
        }
        appointmentTypeDto.name( StringUtilsMapper.trimString( appointmentType.getName() ) );
        appointmentTypeDto.price( appointmentType.getPrice() );

        return appointmentTypeDto.build();
    }

    protected AppointmentDetailsDto appointmentDetailsToAppointmentDetailsDto(AppointmentDetails appointmentDetails) {
        if ( appointmentDetails == null ) {
            return null;
        }

        AppointmentDetailsDto.AppointmentDetailsDtoBuilder appointmentDetailsDto = AppointmentDetailsDto.builder();

        appointmentDetailsDto.appointmentType( appointmentTypeToAppointmentTypeDto( appointmentDetails.getAppointmentType() ) );
        appointmentDetailsDto.durationInMinutes( appointmentDetails.getDurationInMinutes() );
        appointmentDetailsDto.id( appointmentDetails.getId() );
        appointmentDetailsDto.price( appointmentDetails.getPrice() );

        return appointmentDetailsDto.build();
    }

    protected List<AppointmentDetailsDto> appointmentDetailsListToAppointmentDetailsDtoList(List<AppointmentDetails> list) {
        if ( list == null ) {
            return null;
        }

        List<AppointmentDetailsDto> list1 = new ArrayList<AppointmentDetailsDto>( list.size() );
        for ( AppointmentDetails appointmentDetails : list ) {
            list1.add( appointmentDetailsToAppointmentDetailsDto( appointmentDetails ) );
        }

        return list1;
    }

    protected AppointmentDto appointmentToAppointmentDto(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }

        AppointmentDto.AppointmentDtoBuilder appointmentDto = AppointmentDto.builder();

        appointmentDto.createdAt( appointment.getCreatedAt() );
        appointmentDto.createdBy( userToUserDto( appointment.getCreatedBy() ) );
        appointmentDto.description( StringUtilsMapper.trimString( appointment.getDescription() ) );
        appointmentDto.details( appointmentDetailsListToAppointmentDetailsDtoList( appointment.getDetails() ) );
        appointmentDto.id( appointment.getId() );
        appointmentDto.patient( patientMapper.toDto( appointment.getPatient() ) );
        appointmentDto.startAt( appointment.getStartAt() );

        return appointmentDto.build();
    }
}
