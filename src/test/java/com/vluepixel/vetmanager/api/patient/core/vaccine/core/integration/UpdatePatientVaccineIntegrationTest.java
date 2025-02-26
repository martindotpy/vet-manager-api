package com.vluepixel.vetmanager.api.patient.core.vaccine.core.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.patient.core.vaccine.core.data.UpdatePatientVaccineDataProvider.VALID_UPDATE_PATIENT_VACCINE_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the update patient use case.
 */
public class UpdatePatientVaccineIntegrationTest extends BaseIntegrationTest {
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER

    // Role: ADMIN
    @Test
    void admin_UpdatePatientVaccineWithValidArguments_Ok() throws Exception {
        mockMvc.perform(put("/patient/{patient_id}/vaccine", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_UPDATE_PATIENT_VACCINE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value("a"),
                        jsonPath("$.content.id").value(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getId()),
                        jsonPath("$.content.name").value(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getName()),
                        jsonPath("$.content.dose_in_milliliters")
                                .value(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getDoseInMilliliters()),
                        jsonPath("$.content.provided_at").isString(),
                        jsonPath("$.content.vaccinator.id")
                                .value(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getVaccinatorId()),
                        jsonPath("$.content.product_sale.id")
                                .value(VALID_UPDATE_PATIENT_VACCINE_REQUEST.getProductSaleId()));
    }
}
