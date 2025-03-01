package com.vluepixel.vetmanager.api.appointment.details.application.mapper;

import com.vluepixel.vetmanager.api.appointment.details.application.dto.AppointmentDetailsDto;
import com.vluepixel.vetmanager.api.appointment.details.domain.model.AppointmentDetails;
import com.vluepixel.vetmanager.api.appointment.details.domain.request.CreateAppointmentDetailsRequest;
import com.vluepixel.vetmanager.api.appointment.details.domain.request.UpdateAppointmentDetailsRequest;
import com.vluepixel.vetmanager.api.appointment.type.application.dto.AppointmentTypeDto;
import com.vluepixel.vetmanager.api.appointment.type.domain.model.AppointmentType;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-28T20:38:37-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.41.0.z20250213-2037, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class AppointmentDetailsMapperImpl implements AppointmentDetailsMapper {

    @Override
    public AppointmentDetails.AppointmentDetailsBuilder toBuilder(AppointmentDetails t) {
        if ( t == null ) {
            return null;
        }

        AppointmentDetails.AppointmentDetailsBuilder appointmentDetailsBuilder = createAppointmentDetailsBuilder();

        appointmentDetailsBuilder.appointmentType( t.getAppointmentType() );
        appointmentDetailsBuilder.durationInMinutes( t.getDurationInMinutes() );
        appointmentDetailsBuilder.id( t.getId() );
        appointmentDetailsBuilder.price( t.getPrice() );

        return appointmentDetailsBuilder;
    }

    @Override
    public AppointmentDetailsDto toDto(AppointmentDetails domain) {
        if ( domain == null ) {
            return null;
        }

        AppointmentDetailsDto.AppointmentDetailsDtoBuilder appointmentDetailsDto = AppointmentDetailsDto.builder();

        appointmentDetailsDto.appointmentType( appointmentTypeToAppointmentTypeDto( domain.getAppointmentType() ) );
        appointmentDetailsDto.durationInMinutes( domain.getDurationInMinutes() );
        appointmentDetailsDto.id( domain.getId() );
        appointmentDetailsDto.price( domain.getPrice() );

        return appointmentDetailsDto.build();
    }

    @Override
    public AppointmentDetails.AppointmentDetailsBuilder fromRequest(CreateAppointmentDetailsRequest request) {
        if ( request == null ) {
            return null;
        }

        AppointmentDetails.AppointmentDetailsBuilder appointmentDetailsBuilder = createAppointmentDetailsBuilder();

        if ( request.getAppointmentTypeId() != null ) {
            appointmentDetailsBuilder.appointmentType( mapAppointmentTypeIdToAppoitmentType( request.getAppointmentTypeId().intValue() ) );
        }
        appointmentDetailsBuilder.durationInMinutes( request.getDurationInMinutes() );
        appointmentDetailsBuilder.price( request.getPrice() );

        return appointmentDetailsBuilder;
    }

    @Override
    public AppointmentDetails.AppointmentDetailsBuilder fromRequest(UpdateAppointmentDetailsRequest request) {
        if ( request == null ) {
            return null;
        }

        AppointmentDetails.AppointmentDetailsBuilder appointmentDetailsBuilder = createAppointmentDetailsBuilder();

        if ( request.getAppointmentTypeId() != null ) {
            appointmentDetailsBuilder.appointmentType( mapAppointmentTypeIdToAppoitmentType( request.getAppointmentTypeId().intValue() ) );
        }
        appointmentDetailsBuilder.durationInMinutes( request.getDurationInMinutes() );
        appointmentDetailsBuilder.id( request.getId() );
        appointmentDetailsBuilder.price( request.getPrice() );

        return appointmentDetailsBuilder;
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
}
