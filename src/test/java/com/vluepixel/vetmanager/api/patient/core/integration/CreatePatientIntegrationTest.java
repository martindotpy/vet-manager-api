package com.vluepixel.vetmanager.api.patient.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static com.vluepixel.vetmanager.api.patient.core.data.CreatePatientDataProvider.INVALID_BIRTH_DATE_FUTURE_CREATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.CreatePatientDataProvider.INVALID_BIRTH_DATE_MINUS_YEAR_CREATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.CreatePatientDataProvider.INVALID_BIRTH_DATE_NULL_CREATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.CreatePatientDataProvider.INVALID_BIRTH_DATE_TODAY_CREATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.CreatePatientDataProvider.INVALID_DECEASED_NULL_CREATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.CreatePatientDataProvider.INVALID_GENDER_NULL_CREATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.CreatePatientDataProvider.INVALID_NAME_BLANK_CREATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.CreatePatientDataProvider.INVALID_NAME_EMPTY_CREATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.CreatePatientDataProvider.INVALID_NAME_NULL_CREATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.CreatePatientDataProvider.INVALID_NAME_TOO_LONG_CREATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.CreatePatientDataProvider.INVALID_OWNER_ID_NEGATIVE_CREATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.CreatePatientDataProvider.INVALID_OWNER_ID_NOT_FOUND_CREATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.CreatePatientDataProvider.INVALID_OWNER_ID_NULL_CREATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.CreatePatientDataProvider.INVALID_RACE_ID_NEGATIVE_CREATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.CreatePatientDataProvider.INVALID_RACE_ID_NOT_FOUND_CREATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.CreatePatientDataProvider.INVALID_RACE_ID_NULL_CREATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.CreatePatientDataProvider.VALID_CHARACTERISTICS_BLANK_CREATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.CreatePatientDataProvider.VALID_CHARACTERISTICS_EMPTY_CREATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.CreatePatientDataProvider.VALID_CHARACTERISTICS_NULL_CREATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.CreatePatientDataProvider.VALID_CREATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.CreatePatientDataProvider.VALID_NAME_MAX_LENGTH_CREATE_PATIENT_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration test for the create patient functionality.
 */
public class CreatePatientIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Paciente creado exitosamente";
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    void noUser_CreatePatientWithValidArguments_Forbidden() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PATIENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // - Invalidations

    // Name
    @Test
    void noUser_CreatePatientWithInvalidArguments_Name_TooLong_Forbidden() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_TOO_LONG_CREATE_PATIENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientWithValidArguments_Name_MaxLength_Forbidden() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_NAME_MAX_LENGTH_CREATE_PATIENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientWithInvalidArguments_Name_Blank_Forbidden() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_BLANK_CREATE_PATIENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientWithInvalidArguments_Name_Empty_Forbidden() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_EMPTY_CREATE_PATIENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientWithInvalidArguments_Name_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_NULL_CREATE_PATIENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // BirthDate
    @Test
    void noUser_CreatePatientWithInvalidArguments_BirthDate_MinusYear_Forbidden() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BIRTH_DATE_MINUS_YEAR_CREATE_PATIENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientWithInvalidArguments_BirthDate_Today_Forbidden() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BIRTH_DATE_TODAY_CREATE_PATIENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientWithInvalidArguments_BirthDate_Future_Forbidden() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BIRTH_DATE_FUTURE_CREATE_PATIENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientWithInvalidArguments_BirthDate_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BIRTH_DATE_NULL_CREATE_PATIENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Gender
    @Test
    void noUser_CreatePatientWithInvalidArguments_Gender_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_GENDER_NULL_CREATE_PATIENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Characteristics
    @Test
    void user_CreatePatientWithValidArguments_Characteristics_Blank_Forbidden() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CHARACTERISTICS_BLANK_CREATE_PATIENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientWithValidArguments_Characteristics_Empty_Forbidden() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CHARACTERISTICS_EMPTY_CREATE_PATIENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientWithValidArguments_Characteristics_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CHARACTERISTICS_NULL_CREATE_PATIENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Deceased
    @Test
    void noUser_CreatePatientWithInvalidArguments_Deceased_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DECEASED_NULL_CREATE_PATIENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Race ID
    @Test
    void noUser_CreatePatientWithInvalidArguments_RaceID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_RACE_ID_NOT_FOUND_CREATE_PATIENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientWithInvalidArguments_RaceID_Negative_Forbidden() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_RACE_ID_NEGATIVE_CREATE_PATIENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientWithInvalidArguments_RaceID_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_RACE_ID_NULL_CREATE_PATIENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Owner ID
    @Test
    void noUser_CreatePatientWithInvalidArguments_OwnerID_NotFound_Forbidden() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_OWNER_ID_NOT_FOUND_CREATE_PATIENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientWithInvalidArguments_OwnerID_Negative_Forbidden() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_OWNER_ID_NEGATIVE_CREATE_PATIENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_CreatePatientWithInvalidArguments_OwnerID_Null_Forbidden() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_OWNER_ID_NULL_CREATE_PATIENT_REQUEST)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER
    @Test
    @Order(1)
    @DirtiesContext
    void user_CreatePatientWithValidArguments_Ok() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.name").value(VALID_CREATE_PATIENT_REQUEST.getName()),
                        jsonPath("$.content.birth_date").value(VALID_CREATE_PATIENT_REQUEST.getBirthDate().toString()),
                        jsonPath("$.content.age").value(10),
                        jsonPath("$.content.gender").value(VALID_CREATE_PATIENT_REQUEST.getGender().toString()),
                        jsonPath("$.content.characteristics").value(VALID_CREATE_PATIENT_REQUEST.getCharacteristics()),
                        jsonPath("$.content.deceased").value(VALID_CREATE_PATIENT_REQUEST.isDeceased()),
                        jsonPath("$.content.medical_histories").isArray(),
                        jsonPath("$.content.medical_records").isArray(),
                        jsonPath("$.content.vaccines").isArray(),
                        jsonPath("$.content.race.id").value(VALID_CREATE_PATIENT_REQUEST.getRaceId()),
                        jsonPath("$.content.owner.id").value(VALID_CREATE_PATIENT_REQUEST.getOwnerId()));
    }

    // - Invalidations

    // Name
    @Test
    void user_CreatePatientWithInvalidArguments_Name_TooLong_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_TOO_LONG_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El nombre no puede tener más de 50 caracteres"));
    }

    @Test
    @Order(2)
    @DirtiesContext
    void user_CreatePatientWithValidArguments_Name_MaxLength_Ok() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_NAME_MAX_LENGTH_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.name").value(VALID_NAME_MAX_LENGTH_CREATE_PATIENT_REQUEST.getName()),
                        jsonPath("$.content.birth_date")
                                .value(VALID_NAME_MAX_LENGTH_CREATE_PATIENT_REQUEST.getBirthDate().toString()),
                        jsonPath("$.content.age").value(10),
                        jsonPath("$.content.gender")
                                .value(VALID_NAME_MAX_LENGTH_CREATE_PATIENT_REQUEST.getGender().toString()),
                        jsonPath("$.content.characteristics")
                                .value(VALID_NAME_MAX_LENGTH_CREATE_PATIENT_REQUEST.getCharacteristics()),
                        jsonPath("$.content.deceased").value(VALID_NAME_MAX_LENGTH_CREATE_PATIENT_REQUEST.isDeceased()),
                        jsonPath("$.content.medical_histories").isArray(),
                        jsonPath("$.content.medical_records").isArray(),
                        jsonPath("$.content.vaccines").isArray(),
                        jsonPath("$.content.race.id").value(VALID_NAME_MAX_LENGTH_CREATE_PATIENT_REQUEST.getRaceId()),
                        jsonPath("$.content.owner.id")
                                .value(VALID_NAME_MAX_LENGTH_CREATE_PATIENT_REQUEST.getOwnerId()));
    }

    @Test
    void user_CreatePatientWithInvalidArguments_Name_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_BLANK_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El nombre es requerido"));
    }

    @Test
    void user_CreatePatientWithInvalidArguments_Name_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_EMPTY_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El nombre es requerido"));
    }

    @Test
    void user_CreatePatientWithInvalidArguments_Name_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_NULL_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El nombre es requerido"));
    }

    // BirthDate
    @Test // TODO: Take in consideration
    void user_CreatePatientWithInvalidArguments_BirthDate_MinusYear_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BIRTH_DATE_MINUS_YEAR_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El nombre no puede tener más de 50 caracteres"));
    }

    @Test // TODO: Take in consideration
    void user_CreatePatientWithInvalidArguments_BirthDate_Today_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BIRTH_DATE_TODAY_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El nombre no puede tener más de 50 caracteres"));
    }

    @Test // TODO: Take in consideration
    void user_CreatePatientWithInvalidArguments_BirthDate_Future_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BIRTH_DATE_FUTURE_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El nombre no puede tener más de 50 caracteres"));
    }

    @Test // TODO: Take in consideration
    void user_CreatePatientWithInvalidArguments_BirthDate_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BIRTH_DATE_NULL_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("birth_date"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La fecha de nacimiento es requerida"));
    }

    // Gender
    @Test
    void user_CreatePatientWithInvalidArguments_Gender_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_GENDER_NULL_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("gender"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El género es requerido"));
    }

    // Characteristics
    @Test
    @Order(3)
    @DirtiesContext
    void user_CreatePatientWithValidArguments_Characteristics_Blank_Ok() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CHARACTERISTICS_BLANK_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.name").value(VALID_CHARACTERISTICS_BLANK_CREATE_PATIENT_REQUEST.getName()),
                        jsonPath("$.content.birth_date")
                                .value(VALID_CHARACTERISTICS_BLANK_CREATE_PATIENT_REQUEST.getBirthDate().toString()),
                        jsonPath("$.content.age").value(10),
                        jsonPath("$.content.gender")
                                .value(VALID_CHARACTERISTICS_BLANK_CREATE_PATIENT_REQUEST.getGender().toString()),
                        jsonPath("$.content.characteristics")
                                .value(""),
                        jsonPath("$.content.deceased")
                                .value(VALID_CHARACTERISTICS_BLANK_CREATE_PATIENT_REQUEST.isDeceased()),
                        jsonPath("$.content.medical_histories").isArray(),
                        jsonPath("$.content.medical_records").isArray(),
                        jsonPath("$.content.vaccines").isArray(),
                        jsonPath("$.content.race.id")
                                .value(VALID_CHARACTERISTICS_BLANK_CREATE_PATIENT_REQUEST.getRaceId()),
                        jsonPath("$.content.owner.id")
                                .value(VALID_CHARACTERISTICS_BLANK_CREATE_PATIENT_REQUEST.getOwnerId()));
    }

    @Test
    @Order(4)
    @DirtiesContext
    void user_CreatePatientWithValidArguments_Characteristics_Empty_Ok() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CHARACTERISTICS_EMPTY_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.name").value(VALID_CHARACTERISTICS_EMPTY_CREATE_PATIENT_REQUEST.getName()),
                        jsonPath("$.content.birth_date")
                                .value(VALID_CHARACTERISTICS_EMPTY_CREATE_PATIENT_REQUEST.getBirthDate().toString()),
                        jsonPath("$.content.age").value(10),
                        jsonPath("$.content.gender")
                                .value(VALID_CHARACTERISTICS_EMPTY_CREATE_PATIENT_REQUEST.getGender().toString()),
                        jsonPath("$.content.characteristics")
                                .value(VALID_CHARACTERISTICS_EMPTY_CREATE_PATIENT_REQUEST.getCharacteristics()),
                        jsonPath("$.content.deceased")
                                .value(VALID_CHARACTERISTICS_EMPTY_CREATE_PATIENT_REQUEST.isDeceased()),
                        jsonPath("$.content.medical_histories").isArray(),
                        jsonPath("$.content.medical_records").isArray(),
                        jsonPath("$.content.vaccines").isArray(),
                        jsonPath("$.content.race.id")
                                .value(VALID_CHARACTERISTICS_EMPTY_CREATE_PATIENT_REQUEST.getRaceId()),
                        jsonPath("$.content.owner.id")
                                .value(VALID_CHARACTERISTICS_EMPTY_CREATE_PATIENT_REQUEST.getOwnerId()));
    }

    @Test
    @Order(5)
    @DirtiesContext
    void user_CreatePatientWithValidArguments_Characteristics_Null_Ok() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CHARACTERISTICS_NULL_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.name").value(VALID_CHARACTERISTICS_NULL_CREATE_PATIENT_REQUEST.getName()),
                        jsonPath("$.content.birth_date")
                                .value(VALID_CHARACTERISTICS_NULL_CREATE_PATIENT_REQUEST.getBirthDate().toString()),
                        jsonPath("$.content.age").value(10),
                        jsonPath("$.content.gender")
                                .value(VALID_CHARACTERISTICS_NULL_CREATE_PATIENT_REQUEST.getGender().toString()),
                        jsonPath("$.content.characteristics")
                                .value(VALID_CHARACTERISTICS_NULL_CREATE_PATIENT_REQUEST.getCharacteristics()),
                        jsonPath("$.content.deceased")
                                .value(VALID_CHARACTERISTICS_NULL_CREATE_PATIENT_REQUEST.isDeceased()),
                        jsonPath("$.content.medical_histories").isArray(),
                        jsonPath("$.content.medical_records").isArray(),
                        jsonPath("$.content.vaccines").isArray(),
                        jsonPath("$.content.race.id")
                                .value(VALID_CHARACTERISTICS_NULL_CREATE_PATIENT_REQUEST.getRaceId()),
                        jsonPath("$.content.owner.id")
                                .value(VALID_CHARACTERISTICS_NULL_CREATE_PATIENT_REQUEST.getOwnerId()));
    }

    // Deceased
    @Test
    @Order(6)
    @DirtiesContext
    void user_CreatePatientWithInvalidArguments_Deceased_Null_Ok() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DECEASED_NULL_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.name").value(INVALID_DECEASED_NULL_CREATE_PATIENT_REQUEST.getName()),
                        jsonPath("$.content.birth_date")
                                .value(INVALID_DECEASED_NULL_CREATE_PATIENT_REQUEST.getBirthDate().toString()),
                        jsonPath("$.content.age").value(10),
                        jsonPath("$.content.gender")
                                .value(INVALID_DECEASED_NULL_CREATE_PATIENT_REQUEST.getGender().toString()),
                        jsonPath("$.content.characteristics")
                                .value(INVALID_DECEASED_NULL_CREATE_PATIENT_REQUEST.getCharacteristics()),
                        jsonPath("$.content.deceased")
                                .value(INVALID_DECEASED_NULL_CREATE_PATIENT_REQUEST.isDeceased()),
                        jsonPath("$.content.medical_histories").isArray(),
                        jsonPath("$.content.medical_records").isArray(),
                        jsonPath("$.content.vaccines").isArray(),
                        jsonPath("$.content.race.id")
                                .value(INVALID_DECEASED_NULL_CREATE_PATIENT_REQUEST.getRaceId()),
                        jsonPath("$.content.owner.id")
                                .value(INVALID_DECEASED_NULL_CREATE_PATIENT_REQUEST.getOwnerId()));
    }

    // Race ID
    @Test
    void user_CreatePatientWithInvalidArguments_RaceID_NotFound_NotFound() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_RACE_ID_NOT_FOUND_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Raza no encontrado(a)"));
    }

    @Test
    void user_CreatePatientWithInvalidArguments_RaceID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_RACE_ID_NEGATIVE_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("race_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id de la raza debe ser mayor a 0"));
    }

    @Test
    void user_CreatePatientWithInvalidArguments_RaceID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_RACE_ID_NULL_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("race_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id de la raza es requerido"));
    }

    // Owner ID
    @Test
    void user_CreatePatientWithInvalidArguments_OwnerID_NotFound_NotFound() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_OWNER_ID_NOT_FOUND_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Dueño no encontrado(a)"));
    }

    @Test
    void user_CreatePatientWithInvalidArguments_OwnerID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_OWNER_ID_NEGATIVE_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("owner_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del dueño debe ser mayor a 0"));
    }

    @Test
    void user_CreatePatientWithInvalidArguments_OwnerID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_OWNER_ID_NULL_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("owner_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del dueño es requerido"));
    }

    // Role: ADMIN
    @Test
    @Order(7)
    @DirtiesContext
    void admin_CreatePatientWithValidArguments_Ok() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.name").value(VALID_CREATE_PATIENT_REQUEST.getName()),
                        jsonPath("$.content.birth_date").value(VALID_CREATE_PATIENT_REQUEST.getBirthDate().toString()),
                        jsonPath("$.content.age").value(10),
                        jsonPath("$.content.gender").value(VALID_CREATE_PATIENT_REQUEST.getGender().toString()),
                        jsonPath("$.content.characteristics").value(VALID_CREATE_PATIENT_REQUEST.getCharacteristics()),
                        jsonPath("$.content.deceased").value(VALID_CREATE_PATIENT_REQUEST.isDeceased()),
                        jsonPath("$.content.medical_histories").isArray(),
                        jsonPath("$.content.medical_records").isArray(),
                        jsonPath("$.content.vaccines").isArray(),
                        jsonPath("$.content.race.id").value(VALID_CREATE_PATIENT_REQUEST.getRaceId()),
                        jsonPath("$.content.owner.id").value(VALID_CREATE_PATIENT_REQUEST.getOwnerId()));
    }

    // - Invalidations

    // Name
    @Test
    void admin_CreatePatientWithInvalidArguments_Name_TooLong_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_TOO_LONG_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El nombre no puede tener más de 50 caracteres"));
    }

    @Test
    @Order(8)
    @DirtiesContext
    void admin_CreatePatientWithValidArguments_Name_MaxLength_Ok() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_NAME_MAX_LENGTH_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.name").value(VALID_NAME_MAX_LENGTH_CREATE_PATIENT_REQUEST.getName()),
                        jsonPath("$.content.birth_date")
                                .value(VALID_NAME_MAX_LENGTH_CREATE_PATIENT_REQUEST.getBirthDate().toString()),
                        jsonPath("$.content.age").value(10),
                        jsonPath("$.content.gender")
                                .value(VALID_NAME_MAX_LENGTH_CREATE_PATIENT_REQUEST.getGender().toString()),
                        jsonPath("$.content.characteristics")
                                .value(VALID_NAME_MAX_LENGTH_CREATE_PATIENT_REQUEST.getCharacteristics()),
                        jsonPath("$.content.deceased").value(VALID_NAME_MAX_LENGTH_CREATE_PATIENT_REQUEST.isDeceased()),
                        jsonPath("$.content.medical_histories").isArray(),
                        jsonPath("$.content.medical_records").isArray(),
                        jsonPath("$.content.vaccines").isArray(),
                        jsonPath("$.content.race.id").value(VALID_NAME_MAX_LENGTH_CREATE_PATIENT_REQUEST.getRaceId()),
                        jsonPath("$.content.owner.id")
                                .value(VALID_NAME_MAX_LENGTH_CREATE_PATIENT_REQUEST.getOwnerId()));
    }

    @Test
    void admin_CreatePatientWithInvalidArguments_Name_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_BLANK_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El nombre es requerido"));
    }

    @Test
    void admin_CreatePatientWithInvalidArguments_Name_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_EMPTY_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El nombre es requerido"));
    }

    @Test
    void admin_CreatePatientWithInvalidArguments_Name_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_NULL_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El nombre es requerido"));
    }

    // BirthDate
    @Test // TODO: Take in consideration
    void admin_CreatePatientWithInvalidArguments_BirthDate_MinusYear_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BIRTH_DATE_MINUS_YEAR_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El nombre no puede tener más de 50 caracteres"));
    }

    @Test // TODO: Take in consideration
    void admin_CreatePatientWithInvalidArguments_BirthDate_Today_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BIRTH_DATE_TODAY_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El nombre no puede tener más de 50 caracteres"));
    }

    @Test // TODO: Take in consideration
    void admin_CreatePatientWithInvalidArguments_BirthDate_Future_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BIRTH_DATE_FUTURE_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El nombre no puede tener más de 50 caracteres"));
    }

    @Test // TODO: Take in consideration
    void admin_CreatePatientWithInvalidArguments_BirthDate_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BIRTH_DATE_NULL_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("birth_date"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La fecha de nacimiento es requerida"));
    }

    // Gender
    @Test
    void admin_CreatePatientWithInvalidArguments_Gender_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_GENDER_NULL_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("gender"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("must not be null")); // TODO
    }

    // Characteristics
    @Test
    @Order(9)
    @DirtiesContext
    void admin_CreatePatientWithValidArguments_Characteristics_Blank_Ok() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CHARACTERISTICS_BLANK_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.name").value(VALID_CHARACTERISTICS_BLANK_CREATE_PATIENT_REQUEST.getName()),
                        jsonPath("$.content.birth_date")
                                .value(VALID_CHARACTERISTICS_BLANK_CREATE_PATIENT_REQUEST.getBirthDate().toString()),
                        jsonPath("$.content.age").value(10),
                        jsonPath("$.content.gender")
                                .value(VALID_CHARACTERISTICS_BLANK_CREATE_PATIENT_REQUEST.getGender().toString()),
                        jsonPath("$.content.characteristics")
                                .value(""),
                        jsonPath("$.content.deceased")
                                .value(VALID_CHARACTERISTICS_BLANK_CREATE_PATIENT_REQUEST.isDeceased()),
                        jsonPath("$.content.medical_histories").isArray(),
                        jsonPath("$.content.medical_records").isArray(),
                        jsonPath("$.content.vaccines").isArray(),
                        jsonPath("$.content.race.id")
                                .value(VALID_CHARACTERISTICS_BLANK_CREATE_PATIENT_REQUEST.getRaceId()),
                        jsonPath("$.content.owner.id")
                                .value(VALID_CHARACTERISTICS_BLANK_CREATE_PATIENT_REQUEST.getOwnerId()));
    }

    @Test
    @Order(10)
    @DirtiesContext
    void admin_CreatePatientWithValidArguments_Characteristics_Empty_Ok() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CHARACTERISTICS_EMPTY_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.name").value(VALID_CHARACTERISTICS_EMPTY_CREATE_PATIENT_REQUEST.getName()),
                        jsonPath("$.content.birth_date")
                                .value(VALID_CHARACTERISTICS_EMPTY_CREATE_PATIENT_REQUEST.getBirthDate().toString()),
                        jsonPath("$.content.age").value(10),
                        jsonPath("$.content.gender")
                                .value(VALID_CHARACTERISTICS_EMPTY_CREATE_PATIENT_REQUEST.getGender().toString()),
                        jsonPath("$.content.characteristics")
                                .value(VALID_CHARACTERISTICS_EMPTY_CREATE_PATIENT_REQUEST.getCharacteristics()),
                        jsonPath("$.content.deceased")
                                .value(VALID_CHARACTERISTICS_EMPTY_CREATE_PATIENT_REQUEST.isDeceased()),
                        jsonPath("$.content.medical_histories").isArray(),
                        jsonPath("$.content.medical_records").isArray(),
                        jsonPath("$.content.vaccines").isArray(),
                        jsonPath("$.content.race.id")
                                .value(VALID_CHARACTERISTICS_EMPTY_CREATE_PATIENT_REQUEST.getRaceId()),
                        jsonPath("$.content.owner.id")
                                .value(VALID_CHARACTERISTICS_EMPTY_CREATE_PATIENT_REQUEST.getOwnerId()));
    }

    @Test
    @Order(11)
    @DirtiesContext
    void admin_CreatePatientWithValidArguments_Characteristics_Null_Ok() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CHARACTERISTICS_NULL_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.name").value(VALID_CHARACTERISTICS_NULL_CREATE_PATIENT_REQUEST.getName()),
                        jsonPath("$.content.birth_date")
                                .value(VALID_CHARACTERISTICS_NULL_CREATE_PATIENT_REQUEST.getBirthDate().toString()),
                        jsonPath("$.content.age").value(10),
                        jsonPath("$.content.gender")
                                .value(VALID_CHARACTERISTICS_NULL_CREATE_PATIENT_REQUEST.getGender().toString()),
                        jsonPath("$.content.characteristics")
                                .value(VALID_CHARACTERISTICS_NULL_CREATE_PATIENT_REQUEST.getCharacteristics()),
                        jsonPath("$.content.deceased")
                                .value(VALID_CHARACTERISTICS_NULL_CREATE_PATIENT_REQUEST.isDeceased()),
                        jsonPath("$.content.medical_histories").isArray(),
                        jsonPath("$.content.medical_records").isArray(),
                        jsonPath("$.content.vaccines").isArray(),
                        jsonPath("$.content.race.id")
                                .value(VALID_CHARACTERISTICS_NULL_CREATE_PATIENT_REQUEST.getRaceId()),
                        jsonPath("$.content.owner.id")
                                .value(VALID_CHARACTERISTICS_NULL_CREATE_PATIENT_REQUEST.getOwnerId()));
    }

    // Deceased
    @Test
    @Order(12)
    @DirtiesContext
    void admin_CreatePatientWithInvalidArguments_Deceased_Null_Ok() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DECEASED_NULL_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.name").value(INVALID_DECEASED_NULL_CREATE_PATIENT_REQUEST.getName()),
                        jsonPath("$.content.birth_date")
                                .value(INVALID_DECEASED_NULL_CREATE_PATIENT_REQUEST.getBirthDate().toString()),
                        jsonPath("$.content.age").value(10),
                        jsonPath("$.content.gender")
                                .value(INVALID_DECEASED_NULL_CREATE_PATIENT_REQUEST.getGender().toString()),
                        jsonPath("$.content.characteristics")
                                .value(INVALID_DECEASED_NULL_CREATE_PATIENT_REQUEST.getCharacteristics()),
                        jsonPath("$.content.deceased")
                                .value(INVALID_DECEASED_NULL_CREATE_PATIENT_REQUEST.isDeceased()),
                        jsonPath("$.content.medical_histories").isArray(),
                        jsonPath("$.content.medical_records").isArray(),
                        jsonPath("$.content.vaccines").isArray(),
                        jsonPath("$.content.race.id")
                                .value(INVALID_DECEASED_NULL_CREATE_PATIENT_REQUEST.getRaceId()),
                        jsonPath("$.content.owner.id")
                                .value(INVALID_DECEASED_NULL_CREATE_PATIENT_REQUEST.getOwnerId()));
    }

    // Race ID
    @Test
    void admin_CreatePatientWithInvalidArguments_RaceID_NotFound_NotFound() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_RACE_ID_NOT_FOUND_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Raza no encontrado(a)"));
    }

    @Test
    void admin_CreatePatientWithInvalidArguments_RaceID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_RACE_ID_NEGATIVE_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("race_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id de la raza debe ser mayor a 0"));
    }

    @Test
    void admin_CreatePatientWithInvalidArguments_RaceID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_RACE_ID_NULL_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("race_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id de la raza es requerido"));
    }

    // Owner ID
    @Test
    void admin_CreatePatientWithInvalidArguments_OwnerID_NotFound_NotFound() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_OWNER_ID_NOT_FOUND_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Dueño no encontrado(a)"));
    }

    @Test
    void admin_CreatePatientWithInvalidArguments_OwnerID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_OWNER_ID_NEGATIVE_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("owner_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del dueño debe ser mayor a 0"));
    }

    @Test
    void admin_CreatePatientWithInvalidArguments_OwnerID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(post("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_OWNER_ID_NULL_CREATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("owner_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El id del dueño es requerido"));
    }

}
