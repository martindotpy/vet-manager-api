package com.vluepixel.vetmanager.api.sale.core.integration.treatment;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.sale.core.data.treatment.CreateTreatmentSaleDataProvider.VALID_CREATE_TREATMENT_SALE_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the create sale treatment use case.
 */
public class CreateSaleTreatmentIntegrationTest extends BaseIntegrationTest {
    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER

    // Role: ADMIN
    @Test
    @Order(1)
    @DirtiesContext
    void admin_CreateTreatmentWithValidArguments_Ok() throws Exception {
        mockMvc.perform(post("/sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VALID_CREATE_TREATMENT_SALE_REQUEST))
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value("a"),
                        jsonPath("$.content.id").value(4),
                        jsonPath("$.content.discount").value(VALID_CREATE_TREATMENT_SALE_REQUEST.getDiscount()),
                        jsonPath("$.content.price").value(VALID_CREATE_TREATMENT_SALE_REQUEST.getPrice()),
                        jsonPath("$.content.treatment.id").value(VALID_CREATE_TREATMENT_SALE_REQUEST.getTreatmentId()));
        ;
    }
}
