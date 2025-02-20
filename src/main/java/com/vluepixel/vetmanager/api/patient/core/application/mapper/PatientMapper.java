package com.vluepixel.vetmanager.api.patient.core.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;

import com.vluepixel.vetmanager.api.client.core.domain.model.Client;
import com.vluepixel.vetmanager.api.patient.core.application.dto.PatientDto;
import com.vluepixel.vetmanager.api.patient.core.domain.model.Patient;
import com.vluepixel.vetmanager.api.patient.core.domain.request.CreatePatientRequest;
import com.vluepixel.vetmanager.api.patient.core.domain.request.UpdatePatientRequest;
import com.vluepixel.vetmanager.api.patient.race.domain.model.Race;
import com.vluepixel.vetmanager.api.shared.application.mapper.CrudMapper;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;

/**
 * Patient mapper.
 */
@Mapper(componentModel = "spring", uses = { StringUtilsMapper.class })
public interface PatientMapper
        extends CrudMapper<Patient, PatientDto, Patient.PatientBuilder> {

    @Override
    @Mapping(target = "age", expression = "java(domain.calculateAge())")
    PatientDto toDto(Patient domain);

    /**
     * Creates a new {@link Patient} builder.
     *
     * @return the builder
     */
    @ObjectFactory
    default Patient.PatientBuilder createPatientBuilder() {
        return Patient.builder();
    }

    /**
     * Create patient from request.
     *
     * <ul>
     * <li><strong>Ignores:</strong>
     * <ul>
     * <li><code>id</code></li>
     * <li><code>age</code></li>
     * <li><code>deleted</code></li>
     * <li><code>medicalHistories</code></li>
     * <li><code>medicalRecords</code></li>
     * <li><code>vaccines</code></li>
     * </ul>
     * </li>
     * </ul>
     *
     * @param request the create patient request.
     * @return the patient builder
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "medicalHistories", ignore = true)
    @Mapping(target = "medicalRecords", ignore = true)
    @Mapping(target = "vaccines", ignore = true)
    @Mapping(target = "race", source = "raceId")
    @Mapping(target = "owner", source = "ownerId")
    Patient.PatientBuilder fromRequest(CreatePatientRequest request);

    /**
     * Update patient from request.
     *
     * <ul>
     * <li><strong>Ignores:</strong>
     * <ul>
     * <li><code>deleted</code></li>
     * <li><code>medicalHistories</code></li>
     * <li><code>medicalRecords</code></li>
     * <li><code>vaccines</code></li>
     * </ul>
     * </li>
     * </ul>
     *
     * @param request the update patient request.
     * @return the patient builder
     */
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "medicalHistories", ignore = true)
    @Mapping(target = "medicalRecords", ignore = true)
    @Mapping(target = "vaccines", ignore = true)
    @Mapping(target = "race", source = "raceId")
    @Mapping(target = "owner", source = "ownerId")
    Patient.PatientBuilder fromRequest(UpdatePatientRequest request);

    /**
     * Map race id to race.
     *
     * @param raceId the race id.
     * @return the race
     */
    default Race mapRaceIdToRace(Integer raceId) {
        return Race.builder().id(raceId).build();
    }

    /**
     * Map owner id to owner.
     *
     * @param ownerId the owner id.
     * @return the owner
     */
    default Client mapOwnerIdToOwner(Long ownerId) {
        return Client.builder().id(ownerId).build();
    }
}
