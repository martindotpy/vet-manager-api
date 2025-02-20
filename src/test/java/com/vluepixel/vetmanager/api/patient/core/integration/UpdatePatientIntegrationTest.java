package com.vluepixel.vetmanager.api.patient.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.INVALID_BIRTH_DATE_FUTURE_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.INVALID_BIRTH_DATE_MINUS_YEAR_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.INVALID_BIRTH_DATE_NULL_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.INVALID_BIRTH_DATE_TODAY_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.INVALID_DECEASED_NULL_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.INVALID_GENDER_NULL_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.INVALID_ID_NEGATIVE_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.INVALID_ID_NULL_UPDATE_PATIENT_REQUEST;
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
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.INVALID_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.VALID_CHARACTERISTICS_BLANK_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.VALID_CHARACTERISTICS_EMPTY_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.VALID_CHARACTERISTICS_NULL_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.VALID_NAME_MAX_LENGTH_UPDATE_PATIENT_REQUEST;
import static com.vluepixel.vetmanager.api.patient.core.data.UpdatePatientDataProvider.VALID_UPDATE_PATIENT_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.format.DateTimeFormatterBuilder;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the update patient use case.
 */
public class UpdatePatientIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Paciente actualizado exitosamente";
    private static final String MESSAGE_NOT_FOUND = "Paciente no encontrado(a)";
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER

    // Role: ADMIN
    @Test
    @DirtiesContext
    void admin_UpdatePatientWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_UPDATE_PATIENT_REQUEST.getId()),
                        jsonPath("$.content.name").value(VALID_UPDATE_PATIENT_REQUEST.getName()),
                        jsonPath("$.content.birth_date").value(VALID_UPDATE_PATIENT_REQUEST.getBirthDate()
                                .format(new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd").toFormatter())),
                        jsonPath("$.content.age").value(10),
                        jsonPath("$.content.gender").value(VALID_UPDATE_PATIENT_REQUEST.getGender().name()),
                        jsonPath("$.content.characteristics").value(VALID_UPDATE_PATIENT_REQUEST.getCharacteristics()),
                        jsonPath("$.content.deceased").value(VALID_UPDATE_PATIENT_REQUEST.isDeceased()),
                        jsonPath("$.content.medical_histories").isArray(),
                        jsonPath("$.content.medical_records").isArray(),
                        jsonPath("$.content.vaccines").isArray(),
                        jsonPath("$.content.race.id").value(VALID_UPDATE_PATIENT_REQUEST.getRaceId()),
                        jsonPath("$.content.owner.id").value(VALID_UPDATE_PATIENT_REQUEST.getOwnerId()));
    }

    // - Validations

    // ID
    @Test
    void admin_UpdateRaceWithInvalidArguments_ID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(MESSAGE_NOT_FOUND));
    }

    @Test
    void admin_UpdateRaceWithInvalidArguments_ID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NEGATIVE_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateRaceWithInvalidArguments_ID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_ID_NULL_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id es requerido"));
    }

    // Name
    @Test
    void admin_UpdateRaceWithInvalidArguments_Name_TooLong_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_TOO_LONG_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre no puede tener más de 50 caracteres"));
    }

    @Test
    @DirtiesContext
    void admin_UpdateRaceWithValidArguments_Name_MaxLength_Ok() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_NAME_MAX_LENGTH_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_NAME_MAX_LENGTH_UPDATE_PATIENT_REQUEST.getId()),
                        jsonPath("$.content.name").value(VALID_NAME_MAX_LENGTH_UPDATE_PATIENT_REQUEST.getName()),
                        jsonPath("$.content.birth_date")
                                .value(VALID_NAME_MAX_LENGTH_UPDATE_PATIENT_REQUEST.getBirthDate()
                                        .format(new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd")
                                                .toFormatter())),
                        jsonPath("$.content.age").value(10),
                        jsonPath("$.content.gender")
                                .value(VALID_NAME_MAX_LENGTH_UPDATE_PATIENT_REQUEST.getGender().name()),
                        jsonPath("$.content.characteristics")
                                .value(VALID_NAME_MAX_LENGTH_UPDATE_PATIENT_REQUEST.getCharacteristics()),
                        jsonPath("$.content.deceased").value(VALID_NAME_MAX_LENGTH_UPDATE_PATIENT_REQUEST.isDeceased()),
                        jsonPath("$.content.medical_histories").isArray(),
                        jsonPath("$.content.medical_records").isArray(),
                        jsonPath("$.content.vaccines").isArray(),
                        jsonPath("$.content.race.id").value(VALID_UPDATE_PATIENT_REQUEST.getRaceId()),
                        jsonPath("$.content.owner.id").value(VALID_UPDATE_PATIENT_REQUEST.getOwnerId()));
    }

    @Test
    void admin_UpdateRaceWithInvalidArguments_Name_Blank_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_BLANK_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es requerido"));
    }

    @Test
    void admin_UpdateRaceWithInvalidArguments_Name_Empty_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_EMPTY_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es requerido"));
    }

    @Test
    void admin_UpdateRaceWithInvalidArguments_Name_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_NAME_NULL_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es requerido"));
    }

    // Birth Date
    @Test // TODO: Take in consideration
    void admin_UpdateRaceWithInvalidArguments_BirthDate_MinusYear_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BIRTH_DATE_MINUS_YEAR_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es requerido"));
    }

    @Test // TODO: Take in consideration
    void admin_UpdateRaceWithInvalidArguments_BirthDate_Today_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BIRTH_DATE_TODAY_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es requerido"));
    }

    @Test // TODO: Take in consideration
    void admin_UpdateRaceWithInvalidArguments_BirthDate_Future_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BIRTH_DATE_FUTURE_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("name"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El nombre es requerido"));
    }

    @Test
    void admin_UpdateRaceWithInvalidArguments_BirthDate_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_BIRTH_DATE_NULL_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("birth_date"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("La fecha de nacimiento es requerida"));
    }

    // Gender
    @Test
    void admin_UpdateRaceWithInvalidArguments_Gender_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_GENDER_NULL_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("gender"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("must not be null")); // TODO
    }

    // Characteristics
    @Test
    @DirtiesContext
    void admin_UpdateRaceWithValidArguments_Characteristic_Blank_Ok() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CHARACTERISTICS_BLANK_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_CHARACTERISTICS_BLANK_UPDATE_PATIENT_REQUEST.getId()),
                        jsonPath("$.content.name").value(VALID_CHARACTERISTICS_BLANK_UPDATE_PATIENT_REQUEST.getName()),
                        jsonPath("$.content.birth_date")
                                .value(VALID_CHARACTERISTICS_BLANK_UPDATE_PATIENT_REQUEST.getBirthDate()
                                        .format(new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd")
                                                .toFormatter())),
                        jsonPath("$.content.age").value(10),
                        jsonPath("$.content.gender")
                                .value(VALID_CHARACTERISTICS_BLANK_UPDATE_PATIENT_REQUEST.getGender().name()),
                        jsonPath("$.content.characteristics")
                                .value(""),
                        jsonPath("$.content.deceased")
                                .value(VALID_CHARACTERISTICS_BLANK_UPDATE_PATIENT_REQUEST.isDeceased()),
                        jsonPath("$.content.medical_histories").isArray(),
                        jsonPath("$.content.medical_records").isArray(),
                        jsonPath("$.content.vaccines").isArray(),
                        jsonPath("$.content.race.id")
                                .value(VALID_CHARACTERISTICS_BLANK_UPDATE_PATIENT_REQUEST.getRaceId()),
                        jsonPath("$.content.owner.id")
                                .value(VALID_CHARACTERISTICS_BLANK_UPDATE_PATIENT_REQUEST.getOwnerId()));
    }

    @Test
    @DirtiesContext
    void admin_UpdateRaceWithValidArguments_Characteristic_Empty_Ok() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CHARACTERISTICS_EMPTY_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_CHARACTERISTICS_EMPTY_UPDATE_PATIENT_REQUEST.getId()),
                        jsonPath("$.content.name").value(VALID_CHARACTERISTICS_EMPTY_UPDATE_PATIENT_REQUEST.getName()),
                        jsonPath("$.content.birth_date")
                                .value(VALID_CHARACTERISTICS_EMPTY_UPDATE_PATIENT_REQUEST.getBirthDate()
                                        .format(new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd")
                                                .toFormatter())),
                        jsonPath("$.content.age").value(10),
                        jsonPath("$.content.gender")
                                .value(VALID_CHARACTERISTICS_EMPTY_UPDATE_PATIENT_REQUEST.getGender().name()),
                        jsonPath("$.content.characteristics")
                                .value(VALID_CHARACTERISTICS_EMPTY_UPDATE_PATIENT_REQUEST.getCharacteristics()),
                        jsonPath("$.content.deceased")
                                .value(VALID_CHARACTERISTICS_EMPTY_UPDATE_PATIENT_REQUEST.isDeceased()),
                        jsonPath("$.content.medical_histories").isArray(),
                        jsonPath("$.content.medical_records").isArray(),
                        jsonPath("$.content.vaccines").isArray(),
                        jsonPath("$.content.race.id")
                                .value(VALID_CHARACTERISTICS_EMPTY_UPDATE_PATIENT_REQUEST.getRaceId()),
                        jsonPath("$.content.owner.id")
                                .value(VALID_CHARACTERISTICS_EMPTY_UPDATE_PATIENT_REQUEST.getOwnerId()));
    }

    @Test
    @DirtiesContext
    void admin_UpdateRaceWithValidArguments_Characteristic_Null_Ok() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CHARACTERISTICS_NULL_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.id").value(VALID_CHARACTERISTICS_NULL_UPDATE_PATIENT_REQUEST.getId()),
                        jsonPath("$.content.name").value(VALID_CHARACTERISTICS_NULL_UPDATE_PATIENT_REQUEST.getName()),
                        jsonPath("$.content.birth_date")
                                .value(VALID_CHARACTERISTICS_NULL_UPDATE_PATIENT_REQUEST.getBirthDate()
                                        .format(new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd")
                                                .toFormatter())),
                        jsonPath("$.content.age").value(10),
                        jsonPath("$.content.gender")
                                .value(VALID_CHARACTERISTICS_NULL_UPDATE_PATIENT_REQUEST.getGender().name()),
                        jsonPath("$.content.characteristics")
                                .value(VALID_CHARACTERISTICS_NULL_UPDATE_PATIENT_REQUEST.getCharacteristics()),
                        jsonPath("$.content.deceased")
                                .value(VALID_CHARACTERISTICS_NULL_UPDATE_PATIENT_REQUEST.isDeceased()),
                        jsonPath("$.content.medical_histories").isArray(),
                        jsonPath("$.content.medical_records").isArray(),
                        jsonPath("$.content.vaccines").isArray(),
                        jsonPath("$.content.race.id")
                                .value(VALID_CHARACTERISTICS_NULL_UPDATE_PATIENT_REQUEST.getRaceId()),
                        jsonPath("$.content.owner.id")
                                .value(VALID_CHARACTERISTICS_NULL_UPDATE_PATIENT_REQUEST.getOwnerId()));
    }

    // Deceased
    @Test
    void admin_UpdateRaceWithInvalidArguments_Deceased_Null_Ok() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_DECEASED_NULL_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content.name").value(INVALID_DECEASED_NULL_UPDATE_PATIENT_REQUEST.getName()),
                        jsonPath("$.content.birth_date")
                                .value(INVALID_DECEASED_NULL_UPDATE_PATIENT_REQUEST.getBirthDate().toString()),
                        jsonPath("$.content.age").value(10),
                        jsonPath("$.content.gender")
                                .value(INVALID_DECEASED_NULL_UPDATE_PATIENT_REQUEST.getGender().toString()),
                        jsonPath("$.content.characteristics")
                                .value(INVALID_DECEASED_NULL_UPDATE_PATIENT_REQUEST.getCharacteristics()),
                        jsonPath("$.content.deceased")
                                .value(INVALID_DECEASED_NULL_UPDATE_PATIENT_REQUEST.isDeceased()),
                        jsonPath("$.content.medical_histories").isArray(),
                        jsonPath("$.content.medical_records").isArray(),
                        jsonPath("$.content.vaccines").isArray(),
                        jsonPath("$.content.race.id")
                                .value(INVALID_DECEASED_NULL_UPDATE_PATIENT_REQUEST.getRaceId()),
                        jsonPath("$.content.owner.id")
                                .value(INVALID_DECEASED_NULL_UPDATE_PATIENT_REQUEST.getOwnerId()));
    }

    // Race ID
    @Test
    void admin_UpdateRaceWithInvalidArguments_RaceID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_RACE_ID_NOT_FOUND_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Raza no encontrado(a)"));
    }

    @Test
    void admin_UpdateRaceWithInvalidArguments_RaceID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_RACE_ID_NEGATIVE_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("race_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id de la raza debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateRaceWithInvalidArguments_RaceID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_RACE_ID_NULL_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("race_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id de la raza es requerido"));
    }

    // Owner ID
    @Test
    void admin_UpdateRaceWithInvalidArguments_OwnerID_NotFound_NotFound() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_OWNER_ID_NOT_FOUND_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Cliente no encontrado(a)"));
    }

    @Test
    void admin_UpdateRaceWithInvalidArguments_OwnerID_Negative_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_OWNER_ID_NEGATIVE_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("owner_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id del dueño debe ser mayor a 0"));
    }

    @Test
    void admin_UpdateRaceWithInvalidArguments_OwnerID_Null_UnprocessableEntity() throws Exception {
        mockMvc.perform(put("/patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_OWNER_ID_NULL_UPDATE_PATIENT_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details").isArray(),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("owner_id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages").value("El id del dueño es requerido"));
    }
}
