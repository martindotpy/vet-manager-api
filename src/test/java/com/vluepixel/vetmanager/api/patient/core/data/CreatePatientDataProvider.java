package com.vluepixel.vetmanager.api.patient.core.data;

import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.INVALID_BIRTH_DATE_FUTURE_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.VALID_BIRTH_DATE_MINUS_YEAR_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.INVALID_BIRTH_DATE_NULL_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.VALID_BIRTH_DATE_TODAY_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.INVALID_DECEASED_NULL_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.INVALID_GENDER_NULL_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.INVALID_NAME_BLANK_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.INVALID_NAME_EMPTY_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.INVALID_NAME_NULL_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.INVALID_NAME_TOO_LONG_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.INVALID_OWNER_ID_NEGATIVE_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.INVALID_OWNER_ID_NOT_FOUND_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.INVALID_OWNER_ID_NULL_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.INVALID_RACE_ID_NEGATIVE_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.INVALID_RACE_ID_NOT_FOUND_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.INVALID_RACE_ID_NULL_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.VALID_CHARACTERISTICS_BLANK_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.VALID_CHARACTERISTICS_EMPTY_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.VALID_CHARACTERISTICS_NULL_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.VALID_NAME_MAX_LENGTH_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.VALID_UPDATE_PATIENT_REQUEST;

import com.vluepixel.vetmanager.api.patient.core.domain.request.CreatePatientRequest;

/**
 * Create patient data provider.
 */
public class CreatePatientDataProvider {

    public static final CreatePatientRequest VALID_CREATE_PATIENT_REQUEST = CreatePatientRequest
            .builder()
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // Name
    public static final CreatePatientRequest INVALID_NAME_TOO_LONG_CREATE_PATIENT_REQUEST = CreatePatientRequest
            .builder()
            .name(INVALID_NAME_TOO_LONG_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final CreatePatientRequest VALID_NAME_MAX_LENGTH_CREATE_PATIENT_REQUEST = CreatePatientRequest
            .builder()
            .name(VALID_NAME_MAX_LENGTH_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final CreatePatientRequest INVALID_NAME_BLANK_CREATE_PATIENT_REQUEST = CreatePatientRequest
            .builder()
            .name(INVALID_NAME_BLANK_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final CreatePatientRequest INVALID_NAME_EMPTY_CREATE_PATIENT_REQUEST = CreatePatientRequest
            .builder()
            .name(INVALID_NAME_EMPTY_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final CreatePatientRequest INVALID_NAME_NULL_CREATE_PATIENT_REQUEST = CreatePatientRequest
            .builder()
            .name(INVALID_NAME_NULL_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    // BirthDate
    public static final CreatePatientRequest INVALID_BIRTH_DATE_MINUS_YEAR_CREATE_PATIENT_REQUEST = CreatePatientRequest
            .builder()
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_BIRTH_DATE_MINUS_YEAR_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final CreatePatientRequest INVALID_BIRTH_DATE_TODAY_CREATE_PATIENT_REQUEST = CreatePatientRequest
            .builder()
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_BIRTH_DATE_TODAY_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final CreatePatientRequest INVALID_BIRTH_DATE_FUTURE_CREATE_PATIENT_REQUEST = CreatePatientRequest
            .builder()
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(INVALID_BIRTH_DATE_FUTURE_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final CreatePatientRequest INVALID_BIRTH_DATE_NULL_CREATE_PATIENT_REQUEST = CreatePatientRequest
            .builder()
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(INVALID_BIRTH_DATE_NULL_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    // Gender
    public static final CreatePatientRequest INVALID_GENDER_NULL_CREATE_PATIENT_REQUEST = CreatePatientRequest
            .builder()
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(INVALID_GENDER_NULL_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    // Characteristics
    public static final CreatePatientRequest VALID_CHARACTERISTICS_BLANK_CREATE_PATIENT_REQUEST = CreatePatientRequest
            .builder()
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_CHARACTERISTICS_BLANK_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final CreatePatientRequest VALID_CHARACTERISTICS_EMPTY_CREATE_PATIENT_REQUEST = CreatePatientRequest
            .builder()
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_CHARACTERISTICS_EMPTY_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final CreatePatientRequest VALID_CHARACTERISTICS_NULL_CREATE_PATIENT_REQUEST = CreatePatientRequest
            .builder()
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_CHARACTERISTICS_NULL_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    // Deceased
    public static final CreatePatientRequest INVALID_DECEASED_NULL_CREATE_PATIENT_REQUEST = CreatePatientRequest
            .builder()
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(INVALID_DECEASED_NULL_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    // Race ID
    public static final CreatePatientRequest INVALID_RACE_ID_NOT_FOUND_CREATE_PATIENT_REQUEST = CreatePatientRequest
            .builder()
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(INVALID_RACE_ID_NOT_FOUND_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final CreatePatientRequest INVALID_RACE_ID_NEGATIVE_CREATE_PATIENT_REQUEST = CreatePatientRequest
            .builder()
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(INVALID_RACE_ID_NEGATIVE_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final CreatePatientRequest INVALID_RACE_ID_NULL_CREATE_PATIENT_REQUEST = CreatePatientRequest
            .builder()
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(INVALID_RACE_ID_NULL_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(VALID_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    // Owner ID
    public static final CreatePatientRequest INVALID_OWNER_ID_NOT_FOUND_CREATE_PATIENT_REQUEST = CreatePatientRequest
            .builder()
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(INVALID_OWNER_ID_NOT_FOUND_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final CreatePatientRequest INVALID_OWNER_ID_NEGATIVE_CREATE_PATIENT_REQUEST = CreatePatientRequest
            .builder()
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(INVALID_OWNER_ID_NEGATIVE_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();

    public static final CreatePatientRequest INVALID_OWNER_ID_NULL_CREATE_PATIENT_REQUEST = CreatePatientRequest
            .builder()
            .name(VALID_UPDATE_PATIENT_REQUEST.getName())
            .birthDate(VALID_UPDATE_PATIENT_REQUEST.getBirthDate())
            .gender(VALID_UPDATE_PATIENT_REQUEST.getGender())
            .characteristics(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics())
            .deceased(VALID_UPDATE_PATIENT_REQUEST.isDeceased())
            .raceId(VALID_UPDATE_PATIENT_REQUEST.getRaceId())
            .ownerId(INVALID_OWNER_ID_NULL_UPDATE_PATIENT_REQUEST.getOwnerId())
            .build();
}
