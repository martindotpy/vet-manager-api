package com.vluepixel.vetmanager.api.appointment.type.application.mapper;

import com.vluepixel.vetmanager.api.appointment.type.application.dto.AppointmentTypeDto;
import com.vluepixel.vetmanager.api.appointment.type.domain.model.AppointmentType;
import com.vluepixel.vetmanager.api.appointment.type.domain.request.CreateAppointmentTypeRequest;
import com.vluepixel.vetmanager.api.appointment.type.domain.request.UpdateAppointmentTypeRequest;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-28T20:38:37-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.41.0.z20250213-2037, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class AppointmentTypeMapperImpl implements AppointmentTypeMapper {

    @Override
    public AppointmentType.AppointmentTypeBuilder toBuilder(AppointmentType t) {
        if ( t == null ) {
            return null;
        }

        AppointmentType.AppointmentTypeBuilder appointmentTypeBuilder = createAppointmentTypeBuilder();

        appointmentTypeBuilder.durationInMinutes( t.getDurationInMinutes() );
        appointmentTypeBuilder.id( t.getId() );
        appointmentTypeBuilder.name( StringUtilsMapper.trimString( t.getName() ) );
        appointmentTypeBuilder.price( t.getPrice() );

        return appointmentTypeBuilder;
    }

    @Override
    public AppointmentTypeDto toDto(AppointmentType domain) {
        if ( domain == null ) {
            return null;
        }

        AppointmentTypeDto.AppointmentTypeDtoBuilder appointmentTypeDto = AppointmentTypeDto.builder();

        appointmentTypeDto.durationInMinutes( domain.getDurationInMinutes() );
        if ( domain.getId() != null ) {
            appointmentTypeDto.id( domain.getId().longValue() );
        }
        appointmentTypeDto.name( StringUtilsMapper.trimString( domain.getName() ) );
        appointmentTypeDto.price( domain.getPrice() );

        return appointmentTypeDto.build();
    }

    @Override
    public AppointmentType.AppointmentTypeBuilder fromRequest(CreateAppointmentTypeRequest request) {
        if ( request == null ) {
            return null;
        }

        AppointmentType.AppointmentTypeBuilder appointmentTypeBuilder = createAppointmentTypeBuilder();

        appointmentTypeBuilder.durationInMinutes( request.getDurationInMinutes() );
        appointmentTypeBuilder.name( StringUtilsMapper.trimString( request.getName() ) );
        appointmentTypeBuilder.price( request.getPrice() );

        return appointmentTypeBuilder;
    }

    @Override
    public AppointmentType.AppointmentTypeBuilder fromRequest(UpdateAppointmentTypeRequest request) {
        if ( request == null ) {
            return null;
        }

        AppointmentType.AppointmentTypeBuilder appointmentTypeBuilder = createAppointmentTypeBuilder();

        appointmentTypeBuilder.durationInMinutes( request.getDurationInMinutes() );
        appointmentTypeBuilder.id( request.getId() );
        appointmentTypeBuilder.name( StringUtilsMapper.trimString( request.getName() ) );
        appointmentTypeBuilder.price( request.getPrice() );

        return appointmentTypeBuilder;
    }
}
