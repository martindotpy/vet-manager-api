package com.vluepixel.vetmanager.api.patient.core.data;

import java.time.LocalDate;

import com.vluepixel.vetmanager.api.patient.core.domain.enums.PatientGender;
import com.vluepixel.vetmanager.api.patient.core.domain.request.UpdatePatientRequest;

/**
 * Update patient data provider.
 */
public class UpdatePatientDataProvider {
    public static final UpdatePatientRequest INVALID_UPDATE_CLIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(10L)
            .name("New name")
            .birthDate(LocalDate.now())
            .gender(PatientGender.FEMALE)
            .characteristics("Le creció más el pelo")
            .deceased(true)
            .raceId(2)
            .ownerId(2L)
            .build();
}
