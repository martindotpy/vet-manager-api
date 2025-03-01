package com.vluepixel.vetmanager.api.patient.medicalhistory.application.mapper;

import com.vluepixel.vetmanager.api.patient.medicalhistory.application.dto.MedicalHistoryDto;
import com.vluepixel.vetmanager.api.patient.medicalhistory.domain.model.MedicalHistory;
import com.vluepixel.vetmanager.api.patient.medicalhistory.domain.request.CreateMedicalHistoryRequest;
import com.vluepixel.vetmanager.api.patient.medicalhistory.domain.request.UpdateMedicalHistoryRequest;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-28T20:38:37-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.41.0.z20250213-2037, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class MedicalHistoryMapperImpl implements MedicalHistoryMapper {

    @Override
    public MedicalHistory.MedicalHistoryBuilder toBuilder(MedicalHistory t) {
        if ( t == null ) {
            return null;
        }

        MedicalHistory.MedicalHistoryBuilder medicalHistoryBuilder = createMedicalHistoryBuilder();

        medicalHistoryBuilder.content( StringUtilsMapper.trimString( t.getContent() ) );
        medicalHistoryBuilder.createdAt( t.getCreatedAt() );
        medicalHistoryBuilder.deleted( t.isDeleted() );
        medicalHistoryBuilder.id( t.getId() );
        medicalHistoryBuilder.patient( t.getPatient() );
        medicalHistoryBuilder.updatedAt( t.getUpdatedAt() );

        return medicalHistoryBuilder;
    }

    @Override
    public MedicalHistoryDto toDto(MedicalHistory domain) {
        if ( domain == null ) {
            return null;
        }

        MedicalHistoryDto.MedicalHistoryDtoBuilder medicalHistoryDto = MedicalHistoryDto.builder();

        medicalHistoryDto.content( StringUtilsMapper.trimString( domain.getContent() ) );
        medicalHistoryDto.createdAt( domain.getCreatedAt() );
        medicalHistoryDto.id( domain.getId() );
        medicalHistoryDto.updatedAt( domain.getUpdatedAt() );

        return medicalHistoryDto.build();
    }

    @Override
    public MedicalHistory.MedicalHistoryBuilder fromRequest(CreateMedicalHistoryRequest request) {
        if ( request == null ) {
            return null;
        }

        MedicalHistory.MedicalHistoryBuilder medicalHistoryBuilder = createMedicalHistoryBuilder();

        medicalHistoryBuilder.patient( mapPatientIdToPatient( request.getPatientId() ) );
        medicalHistoryBuilder.content( StringUtilsMapper.trimString( request.getContent() ) );

        return medicalHistoryBuilder;
    }

    @Override
    public MedicalHistory.MedicalHistoryBuilder fromRequest(UpdateMedicalHistoryRequest request) {
        if ( request == null ) {
            return null;
        }

        MedicalHistory.MedicalHistoryBuilder medicalHistoryBuilder = createMedicalHistoryBuilder();

        medicalHistoryBuilder.content( StringUtilsMapper.trimString( request.getContent() ) );
        medicalHistoryBuilder.id( request.getId() );

        return medicalHistoryBuilder;
    }
}
