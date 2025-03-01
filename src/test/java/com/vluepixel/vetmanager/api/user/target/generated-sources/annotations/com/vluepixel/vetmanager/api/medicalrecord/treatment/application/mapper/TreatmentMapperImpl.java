package com.vluepixel.vetmanager.api.medicalrecord.treatment.application.mapper;

import com.vluepixel.vetmanager.api.medicalrecord.treatment.application.dto.TreatmentDto;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.domain.model.Treatment;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.domain.request.CreateTreatmentRequest;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.domain.request.UpdateTreatmentRequest;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-28T20:38:37-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.41.0.z20250213-2037, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class TreatmentMapperImpl implements TreatmentMapper {

    @Override
    public Treatment.TreatmentBuilder toBuilder(Treatment t) {
        if ( t == null ) {
            return null;
        }

        Treatment.TreatmentBuilder treatmentBuilder = createTreatmentBuilder();

        treatmentBuilder.deleted( t.isDeleted() );
        treatmentBuilder.description( StringUtilsMapper.trimString( t.getDescription() ) );
        treatmentBuilder.id( t.getId() );
        treatmentBuilder.medicalRecord( t.getMedicalRecord() );
        treatmentBuilder.order( t.getOrder() );

        return treatmentBuilder;
    }

    @Override
    public TreatmentDto toDto(Treatment domain) {
        if ( domain == null ) {
            return null;
        }

        TreatmentDto.TreatmentDtoBuilder treatmentDto = TreatmentDto.builder();

        treatmentDto.description( StringUtilsMapper.trimString( domain.getDescription() ) );
        treatmentDto.id( domain.getId() );
        treatmentDto.order( domain.getOrder() );

        return treatmentDto.build();
    }

    @Override
    public Treatment.TreatmentBuilder fromRequest(CreateTreatmentRequest request) {
        if ( request == null ) {
            return null;
        }

        Treatment.TreatmentBuilder treatmentBuilder = createTreatmentBuilder();

        treatmentBuilder.medicalRecord( mapMedicalRecordIdToMedicalRecord( request.getMedicalRecordId() ) );
        treatmentBuilder.description( StringUtilsMapper.trimString( request.getDescription() ) );
        treatmentBuilder.order( request.getOrder() );

        return treatmentBuilder;
    }

    @Override
    public Treatment.TreatmentBuilder fromRequest(UpdateTreatmentRequest request) {
        if ( request == null ) {
            return null;
        }

        Treatment.TreatmentBuilder treatmentBuilder = createTreatmentBuilder();

        treatmentBuilder.description( StringUtilsMapper.trimString( request.getDescription() ) );
        treatmentBuilder.id( request.getId() );
        treatmentBuilder.order( request.getOrder() );

        return treatmentBuilder;
    }
}
