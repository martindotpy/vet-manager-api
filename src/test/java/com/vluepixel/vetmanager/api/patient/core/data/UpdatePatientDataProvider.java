package com.vluepixel.vetmanager.api.patient.core.data;

import java.time.LocalDate;

import com.vluepixel.vetmanager.api.patient.core.domain.enums.PatientGender;
import com.vluepixel.vetmanager.api.patient.core.domain.request.UpdatePatientRequest;

/**
 * Update patient data provider.
 */
public class UpdatePatientDataProvider {
    public static final UpdatePatientRequest INVALID_UPDATE_PATIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(10L)
            .name("New name")
            .birthDate(LocalDate.now().minusYears(10))
            .gender(PatientGender.FEMALE)
            .characteristics("Le creció más el pelo")
            .deceased(true)
            .raceId(1)
            .ownerId(2L)
            .build();

    public static final UpdatePatientRequest VALID_UPDATE_PATIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(1L)
            .name("New name")
            .birthDate(LocalDate.now().minusYears(10))
            .gender(PatientGender.FEMALE)
            .characteristics("Le creció más el pelo")
            .deceased(true)
            .raceId(1)
            .ownerId(1L)
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // ID
    public static final UpdatePatientRequest INVALID_ID_NEGATIVE_UPDATE_PATIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(-1L)
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    // Name
    private static final int MAX_NAME_LENGTH = 50;
    public static final UpdatePatientRequest INVALID_NAME_TOO_LONG_UPDATE_PATIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_REQUEST.getId())
            .name("a".repeat(MAX_NAME_LENGTH + 1))
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final UpdatePatientRequest VALID_NAME_MAX_LENGTH_UPDATE_PATIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_REQUEST.getId())
            .name("a".repeat(MAX_NAME_LENGTH))
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final UpdatePatientRequest INVALID_NAME_BLANK_UPDATE_PATIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_REQUEST.getId())
            .name(" ")
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final UpdatePatientRequest INVALID_NAME_EMPTY_UPDATE_PATIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_REQUEST.getId())
            .name("")
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final UpdatePatientRequest INVALID_NAME_NULL_UPDATE_PATIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_REQUEST.getId())
            .name(null)
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    // BirthDate
    public static final UpdatePatientRequest INVALID_BIRTH_DATE_MINUS_YEAR_UPDATE_PATIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(LocalDate.now().minusYears(1))
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final UpdatePatientRequest INVALID_BIRTH_DATE_TODAY_UPDATE_PATIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(LocalDate.now())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final UpdatePatientRequest INVALID_BIRTH_DATE_FUTURE_UPDATE_PATIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(LocalDate.now().plusDays(1))
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final UpdatePatientRequest INVALID_BIRTH_DATE_NULL_UPDATE_PATIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(null)
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    // Gender
    public static final UpdatePatientRequest INVALID_GENDER_NULL_UPDATE_PATIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(null)
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    // Characteristics
    public static final UpdatePatientRequest VALID_CHARACTERISTICS_BLANK_UPDATE_PATIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(" ")
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final UpdatePatientRequest VALID_CHARACTERISTICS_EMPTY_UPDATE_PATIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics("")
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final UpdatePatientRequest INVALID_CHARACTERISTICS_NULL_UPDATE_PATIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(null)
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    // Deceased

    public static final UpdatePatientRequest INVALID_DESEASED_NULL_UPDATE_PATIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(Boolean.parseBoolean(null))
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    // Race ID
    public static final UpdatePatientRequest INVALID_RACE_ID_NOT_FOUND_UPDATE_PATIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(10)
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final UpdatePatientRequest INVALID_RACE_ID_NEGATIVE_UPDATE_PATIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(-1)
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final UpdatePatientRequest INVALID_RACE_ID_NULL_UPDATE_PATIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(null)
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    // Owner ID
    public static final UpdatePatientRequest INVALID_OWNER_ID_NOT_FOUND_UPDATE_PATIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(10L)
            .build();

    public static final UpdatePatientRequest INVALID_OWNER_ID_NEGATIVE_UPDATE_PATIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(-1L)
            .build();

    public static final UpdatePatientRequest INVALID_OWNER_ID_NULL_UPDATE_PATIENT_REQUEST = UpdatePatientRequest
            .builder()
            .id(VALID_UPDATE_PATIENT_REQUEST.getId())
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(null)
            .build();
}
