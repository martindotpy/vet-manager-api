package com.vluepixel.vetmanager.api.appointment.core.application.mapper;

import com.vluepixel.vetmanager.api.appointment.core.application.dto.AppointmentDto;
import com.vluepixel.vetmanager.api.appointment.core.domain.model.Appointment;
import com.vluepixel.vetmanager.api.appointment.core.domain.request.CreateAppointmentRequest;
import com.vluepixel.vetmanager.api.appointment.core.domain.request.UpdateAppointmentRequest;
import com.vluepixel.vetmanager.api.appointment.details.application.dto.AppointmentDetailsDto;
import com.vluepixel.vetmanager.api.appointment.details.application.mapper.AppointmentDetailsMapper;
import com.vluepixel.vetmanager.api.appointment.details.domain.model.AppointmentDetails;
import com.vluepixel.vetmanager.api.appointment.details.domain.request.CreateAppointmentDetailsRequest;
import com.vluepixel.vetmanager.api.appointment.details.domain.request.UpdateAppointmentDetailsRequest;
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
public class AppointmentMapperImpl implements AppointmentMapper {

    @Autowired
    private AppointmentDetailsMapper appointmentDetailsMapper;
    @Autowired
    private PatientMapper patientMapper;

    @Override
    public Appointment.AppointmentBuilder toBuilder(Appointment t) {
        if ( t == null ) {
            return null;
        }

        Appointment.AppointmentBuilder appointmentBuilder = createAppointmentBuilder();

        appointmentBuilder.createdAt( t.getCreatedAt() );
        appointmentBuilder.createdBy( t.getCreatedBy() );
        appointmentBuilder.deleted( t.isDeleted() );
        appointmentBuilder.description( StringUtilsMapper.trimString( t.getDescription() ) );
        List<AppointmentDetails> list = t.getDetails();
        if ( list != null ) {
            appointmentBuilder.details( new ArrayList<AppointmentDetails>( list ) );
        }
        appointmentBuilder.id( t.getId() );
        appointmentBuilder.patient( t.getPatient() );
        appointmentBuilder.startAt( t.getStartAt() );

        return appointmentBuilder;
    }

    @Override
    public AppointmentDto toDto(Appointment domain) {
        if ( domain == null ) {
            return null;
        }

        AppointmentDto.AppointmentDtoBuilder appointmentDto = AppointmentDto.builder();

        appointmentDto.createdAt( domain.getCreatedAt() );
        appointmentDto.createdBy( userToUserDto( domain.getCreatedBy() ) );
        appointmentDto.description( StringUtilsMapper.trimString( domain.getDescription() ) );
        appointmentDto.details( appointmentDetailsListToAppointmentDetailsDtoList( domain.getDetails() ) );
        appointmentDto.id( domain.getId() );
        appointmentDto.patient( patientMapper.toDto( domain.getPatient() ) );
        appointmentDto.startAt( domain.getStartAt() );

        return appointmentDto.build();
    }

    @Override
    public Appointment.AppointmentBuilder fromRequest(CreateAppointmentRequest request) {
        if ( request == null ) {
            return null;
        }

        Appointment.AppointmentBuilder appointmentBuilder = createAppointmentBuilder();

        appointmentBuilder.patient( mapPatientIdToPatient( request.getPatientId() ) );
        appointmentBuilder.description( StringUtilsMapper.trimString( request.getDescription() ) );
        appointmentBuilder.details( createAppointmentDetailsRequestListToAppointmentDetailsList( request.getDetails() ) );
        appointmentBuilder.startAt( request.getStartAt() );

        return appointmentBuilder;
    }

    @Override
    public Appointment.AppointmentBuilder fromRequest(UpdateAppointmentRequest request) {
        if ( request == null ) {
            return null;
        }

        Appointment.AppointmentBuilder appointmentBuilder = createAppointmentBuilder();

        appointmentBuilder.patient( mapPatientIdToPatient( request.getPatientId() ) );
        appointmentBuilder.description( StringUtilsMapper.trimString( request.getDescription() ) );
        appointmentBuilder.details( updateAppointmentDetailsRequestListToAppointmentDetailsList( request.getDetails() ) );
        appointmentBuilder.id( request.getId() );
        appointmentBuilder.startAt( request.getStartAt() );

        return appointmentBuilder;
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

    protected List<AppointmentDetailsDto> appointmentDetailsListToAppointmentDetailsDtoList(List<AppointmentDetails> list) {
        if ( list == null ) {
            return null;
        }

        List<AppointmentDetailsDto> list1 = new ArrayList<AppointmentDetailsDto>( list.size() );
        for ( AppointmentDetails appointmentDetails : list ) {
            list1.add( appointmentDetailsMapper.toDto( appointmentDetails ) );
        }

        return list1;
    }

    protected List<AppointmentDetails> createAppointmentDetailsRequestListToAppointmentDetailsList(List<CreateAppointmentDetailsRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<AppointmentDetails> list1 = new ArrayList<AppointmentDetails>( list.size() );
        for ( CreateAppointmentDetailsRequest createAppointmentDetailsRequest : list ) {
            list1.add( mapDetailsToDetails( createAppointmentDetailsRequest ) );
        }

        return list1;
    }

    protected List<AppointmentDetails> updateAppointmentDetailsRequestListToAppointmentDetailsList(List<UpdateAppointmentDetailsRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<AppointmentDetails> list1 = new ArrayList<AppointmentDetails>( list.size() );
        for ( UpdateAppointmentDetailsRequest updateAppointmentDetailsRequest : list ) {
            list1.add( mapDetailsToDetails( updateAppointmentDetailsRequest ) );
        }

        return list1;
    }
}
