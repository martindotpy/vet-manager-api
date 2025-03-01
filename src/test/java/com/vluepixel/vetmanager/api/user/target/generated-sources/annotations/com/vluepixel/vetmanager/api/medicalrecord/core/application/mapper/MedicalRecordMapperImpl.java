package com.vluepixel.vetmanager.api.medicalrecord.core.application.mapper;

import com.vluepixel.vetmanager.api.medicalrecord.core.application.dto.MedicalRecordDto;
import com.vluepixel.vetmanager.api.medicalrecord.core.domain.model.MedicalRecord;
import com.vluepixel.vetmanager.api.medicalrecord.core.domain.request.CreateMedicalRecordRequest;
import com.vluepixel.vetmanager.api.medicalrecord.core.domain.request.UpdateMedicalRecordRequest;
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
public class MedicalRecordMapperImpl implements MedicalRecordMapper {

    @Override
    public MedicalRecord.MedicalRecordBuilder toBuilder(MedicalRecord t) {
        if ( t == null ) {
            return null;
        }

        MedicalRecord.MedicalRecordBuilder medicalRecordBuilder = createMedicalRecordBuilder();

        medicalRecordBuilder.deleted( t.isDeleted() );
        medicalRecordBuilder.diagnosis( StringUtilsMapper.trimString( t.getDiagnosis() ) );
        medicalRecordBuilder.entryAt( t.getEntryAt() );
        medicalRecordBuilder.heartRate( t.getHeartRate() );
        medicalRecordBuilder.id( t.getId() );
        medicalRecordBuilder.patient( t.getPatient() );
        medicalRecordBuilder.physicalExam( StringUtilsMapper.trimString( t.getPhysicalExam() ) );
        medicalRecordBuilder.reason( StringUtilsMapper.trimString( t.getReason() ) );
        medicalRecordBuilder.recipe( StringUtilsMapper.trimString( t.getRecipe() ) );
        medicalRecordBuilder.respirationRate( t.getRespirationRate() );
        medicalRecordBuilder.sterilized( t.isSterilized() );
        medicalRecordBuilder.temperatureInCelsius( t.getTemperatureInCelsius() );
        List<Treatment> list = t.getTreatments();
        if ( list != null ) {
            medicalRecordBuilder.treatments( new ArrayList<Treatment>( list ) );
        }
        medicalRecordBuilder.vet( t.getVet() );
        medicalRecordBuilder.weight( t.getWeight() );

        return medicalRecordBuilder;
    }

    @Override
    public MedicalRecordDto toDto(MedicalRecord domain) {
        if ( domain == null ) {
            return null;
        }

        MedicalRecordDto.MedicalRecordDtoBuilder medicalRecordDto = MedicalRecordDto.builder();

        medicalRecordDto.diagnosis( StringUtilsMapper.trimString( domain.getDiagnosis() ) );
        medicalRecordDto.entryAt( domain.getEntryAt() );
        medicalRecordDto.heartRate( domain.getHeartRate() );
        medicalRecordDto.id( domain.getId() );
        medicalRecordDto.physicalExam( StringUtilsMapper.trimString( domain.getPhysicalExam() ) );
        medicalRecordDto.reason( StringUtilsMapper.trimString( domain.getReason() ) );
        medicalRecordDto.recipe( StringUtilsMapper.trimString( domain.getRecipe() ) );
        medicalRecordDto.respirationRate( domain.getRespirationRate() );
        medicalRecordDto.sterilized( domain.isSterilized() );
        medicalRecordDto.temperatureInCelsius( domain.getTemperatureInCelsius() );
        medicalRecordDto.treatments( treatmentListToTreatmentDtoList( domain.getTreatments() ) );
        medicalRecordDto.vet( userToUserDto( domain.getVet() ) );
        medicalRecordDto.weight( domain.getWeight() );

        return medicalRecordDto.build();
    }

    @Override
    public MedicalRecord.MedicalRecordBuilder fromRequest(CreateMedicalRecordRequest request) {
        if ( request == null ) {
            return null;
        }

        MedicalRecord.MedicalRecordBuilder medicalRecordBuilder = createMedicalRecordBuilder();

        medicalRecordBuilder.patient( mapPatientIdToPatient( request.getPatientId() ) );
        medicalRecordBuilder.vet( mapVetIdToUser( request.getVetId() ) );
        medicalRecordBuilder.diagnosis( StringUtilsMapper.trimString( request.getDiagnosis() ) );
        medicalRecordBuilder.entryAt( request.getEntryAt() );
        medicalRecordBuilder.heartRate( request.getHeartRate() );
        medicalRecordBuilder.physicalExam( StringUtilsMapper.trimString( request.getPhysicalExam() ) );
        medicalRecordBuilder.reason( StringUtilsMapper.trimString( request.getReason() ) );
        medicalRecordBuilder.recipe( StringUtilsMapper.trimString( request.getRecipe() ) );
        medicalRecordBuilder.respirationRate( request.getRespirationRate() );
        medicalRecordBuilder.sterilized( request.isSterilized() );
        medicalRecordBuilder.temperatureInCelsius( request.getTemperatureInCelsius() );
        medicalRecordBuilder.weight( request.getWeight() );

        return medicalRecordBuilder;
    }

    @Override
    public MedicalRecord.MedicalRecordBuilder fromRequest(UpdateMedicalRecordRequest request) {
        if ( request == null ) {
            return null;
        }

        MedicalRecord.MedicalRecordBuilder medicalRecordBuilder = createMedicalRecordBuilder();

        medicalRecordBuilder.vet( mapVetIdToUser( request.getVetId() ) );
        medicalRecordBuilder.diagnosis( StringUtilsMapper.trimString( request.getDiagnosis() ) );
        medicalRecordBuilder.entryAt( request.getEntryAt() );
        medicalRecordBuilder.heartRate( request.getHeartRate() );
        medicalRecordBuilder.id( request.getId() );
        medicalRecordBuilder.physicalExam( StringUtilsMapper.trimString( request.getPhysicalExam() ) );
        medicalRecordBuilder.reason( StringUtilsMapper.trimString( request.getReason() ) );
        medicalRecordBuilder.recipe( StringUtilsMapper.trimString( request.getRecipe() ) );
        medicalRecordBuilder.respirationRate( request.getRespirationRate() );
        medicalRecordBuilder.sterilized( request.isSterilized() );
        medicalRecordBuilder.temperatureInCelsius( request.getTemperatureInCelsius() );
        medicalRecordBuilder.weight( request.getWeight() );

        return medicalRecordBuilder;
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

    protected List<TreatmentDto> treatmentListToTreatmentDtoList(List<Treatment> list) {
        if ( list == null ) {
            return null;
        }

        List<TreatmentDto> list1 = new ArrayList<TreatmentDto>( list.size() );
        for ( Treatment treatment : list ) {
            list1.add( treatmentToTreatmentDto( treatment ) );
        }

        return list1;
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
}
