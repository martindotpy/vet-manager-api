package com.vluepixel.vetmanager.api.appointment.core.type.integration;

import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_ADMIN_JWT;
import static com.vluepixel.vetmanager.api.auth.core.data.AuthDataProvider.BEARER_USER_JWT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.vluepixel.vetmanager.api.base.BaseIntegrationTest;

/**
 * Integration tests for the get appointment type use case.
 */
public class GetAppointmentTypeTypeIntegrationTest extends BaseIntegrationTest {
    private static final String MESSAGE_OK = "Tipos de cita encontradas";

    // -----------------------------------------------------------------------------------------------------------------
    // Without authentication:
    // -----------------------------------------------------------------------------------------------------------------

    @Test
    void noUser_GetAppointmentTypeWithValidParams_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetAppointmentTypeWithoutParams_Forbidden() throws Exception {
        mockMvc.perform(get("/appointment/type"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // ID
    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_ID_Invalid_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("id", "invalid");
        queryParams.add("page", "1");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_ID_NotFound_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("id", "20");
        queryParams.add("page", "1");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_ID_Blank_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("id", " ");
        queryParams.add("page", "1");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_ID_Empty_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("id", "");
        queryParams.add("page", "1");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_ID_Null_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("id", null);
        queryParams.add("page", "1");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Page
    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_Page_NotNumber_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "abcd");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_Page_Negative_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "-1");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_Page_Blank_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", " ");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_Page_Null_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", null);
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Size
    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_Size_NotNumber_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "abcd");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_Size_Negative_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "-1");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_Size_Largest_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "111");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_Size_Blank_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", " ");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_Size_Null_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", null);

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Order
    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_Order_Without_OrderBy_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_Order_Invalid_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "abcd");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_Order_Blank_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", " ");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_Order_Empty_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", " ");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_Order_Null_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", null);

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // OrderBy
    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_OrderBy_Name_Invalid_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");
        queryParams.add("order_by", "abcd");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_OrderBy_Name_Valid_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");
        queryParams.add("order_by", "name");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_OrderBy_Name_Null_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");
        queryParams.add("order_by", null);

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // Name
    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_AppointmentTypeName_Valid_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");
        queryParams.add("order_by", "name");
        queryParams.add("name", "Limpieza");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_AppointmentTypeName_Blank_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");
        queryParams.add("order_by", "name");
        queryParams.add("name", " ");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_AppointmentTypeName_Empty_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");
        queryParams.add("order_by", "name");
        queryParams.add("name", "");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    @Test
    void noUser_GetAppointmentTypeWithInvalidParams_AppointmentTypeName_Null_Forbidden() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");
        queryParams.add("order_by", "name");
        queryParams.add("name", null);

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(MESSAGE_FORBIDDEN));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // With authentication:
    // -----------------------------------------------------------------------------------------------------------------

    // Role: USER
    @Test
    void user_GetAppointmentTypeWithValidParams_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void user_GetAppointmentTypeWithoutParams_Ok() throws Exception {
        mockMvc.perform(get("/appointment/type")
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    // ID
    @Test
    void user_GetAppointmentTypeWithInvalidParams_ID_Invalid_UnprocessableEntity() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("id", "invalid");
        queryParams.add("page", "1");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("Valor numérico inválido"));
    }

    @Test
    void user_GetAppointmentTypeWithInvalidParams_ID_NotFound_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("id", "20");
        queryParams.add("page", "1");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(0),
                        jsonPath("$.total_pages").value(0),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(0));
    }

    @Test
    void user_GetAppointmentTypeWithInvalidParams_ID_Blank_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("id", " ");
        queryParams.add("page", "1");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void user_GetAppointmentTypeWithInvalidParams_ID_Empty_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("id", "");
        queryParams.add("page", "1");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void user_GetAppointmentTypeWithInvalidParams_ID_Null_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("id", null);
        queryParams.add("page", "1");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    // Page
    @Test
    void user_GetAppointmentTypeWithInvalidParams_Page_NotNumber_UnprocessableEntity() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "abcd");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.page"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("Valor numérico inválido"));
    }

    @Test
    void user_GetAppointmentTypeWithInvalidParams_Page_Negative_UnprocessableEntity() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "-1");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.page"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La página debe ser mayor a 0"));
    }

    @Test
    void user_GetAppointmentTypeWithInvalidParams_Page_NotNumber_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", " ");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void user_GetAppointmentTypeWithInvalidParams_Page_Null_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", null);
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    // Size
    @Test
    void user_GetAppointmentTypeWithInvalidParams_Size_NotNumber_UnprocessableEntity() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "abcd");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.size"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("Valor numérico inválido"));
    }

    @Test
    void user_GetAppointmentTypeWithInvalidParams_Size_Negative_UnprocessableEntity() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "-1");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.size"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El tamaño debe ser mayor a 0"));
    }

    @Test
    void user_GetAppointmentTypeWithInvalidParams_Size_Largest_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "111");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(111),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void user_GetAppointmentTypeWithInvalidParams_Size_Blank_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", " ");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void user_GetAppointmentTypeWithInvalidParams_Size_Null_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", null);

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    // Order
    @Test
    void user_GetAppointmentTypeWithInvalidParams_Order_Without_OrderBy_UnprocessableEntity() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.order"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El campo para ordenar es requerido cuando se ha definido un orden"));
    }

    @Test
    void user_GetAppointmentTypeWithInvalidParams_Order_Invalid_UnprocessableEntity() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "abcd");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.order"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("Solo se permite: asc, desc, none"));
    }

    @Test
    void user_GetAppointmentTypeWithInvalidParams_Order_Blank_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", " ");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void user_GetAppointmentTypeWithInvalidParams_Order_Empty_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", " ");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void user_GetAppointmentTypeWithInvalidParams_Order_Null_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", null);

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    // OrderBy
    @Test
    void user_GetAppointmentTypeWithInvalidParams_OrderBy_Name_Invalid_UnprocessableEntity() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");
        queryParams.add("order_by", "abcd");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.order_by"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("Solo los siguientes campos son válidos: id, name, duration_in_minutes"));
    }

    @Test
    void user_GetAppointmentTypeWithInvalidParams_OrderBy_Name_Valid_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");
        queryParams.add("order_by", "name");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void user_GetAppointmentTypeWithInvalidParams_OrderBy_Name_Null_UnprocessableEntity() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");
        queryParams.add("order_by", null);

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.order"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El campo para ordenar es requerido cuando se ha definido un orden"));
    }

    // Name
    @Test
    void user_GetAppointmentTypeWithInvalidParams_AppointmentTypeName_Valid_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");
        queryParams.add("order_by", "name");
        queryParams.add("name", "Limpieza");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(1),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(1),
                        jsonPath("$.content[0].name").value("Limpieza"));
    }

    @Test
    void user_GetAppointmentTypeWithInvalidParams_AppointmentTypeName_Blank_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");
        queryParams.add("order_by", "name");
        queryParams.add("name", " ");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void user_GetAppointmentTypeWithInvalidParams_AppointmentTypeName_Empty_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");
        queryParams.add("order_by", "name");
        queryParams.add("name", "");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void user_GetAppointmentTypeWithInvalidParams_AppointmentTypeName_Null_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");
        queryParams.add("order_by", "name");
        queryParams.add("name", null);

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_USER_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    // Role: ADMIN
    @Test
    void admin_GetAppointmentTypeWithValidParams_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void admin_GetAppointmentTypeWithoutParams_Ok() throws Exception {
        mockMvc.perform(get("/appointment/type")
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    // ID
    @Test
    void admin_GetAppointmentTypeWithInvalidParams_ID_Invalid_UnprocessableEntity() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("id", "invalid");
        queryParams.add("page", "1");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.id"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("Valor numérico inválido"));
    }

    @Test
    void admin_GetAppointmentTypeWithInvalidParams_ID_NotFound_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("id", "20");
        queryParams.add("page", "1");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(0),
                        jsonPath("$.total_pages").value(0),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(0));
    }

    @Test
    void admin_GetAppointmentTypeWithInvalidParams_ID_Blank_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("id", " ");
        queryParams.add("page", "1");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void admin_GetAppointmentTypeWithInvalidParams_ID_Empty_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("id", "");
        queryParams.add("page", "1");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void admin_GetAppointmentTypeWithInvalidParams_ID_Null_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("id", null);
        queryParams.add("page", "1");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    // Page
    @Test
    void admin_GetAppointmentTypeWithInvalidParams_Page_NotNumber_UnprocessableEntity() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "abcd");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.page"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("Valor numérico inválido"));
    }

    @Test
    void admin_GetAppointmentTypeWithInvalidParams_Page_Negative_UnprocessableEntity() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "-1");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.page"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("La página debe ser mayor a 0"));
    }

    @Test
    void admin_GetAppointmentTypeWithInvalidParams_Page_NotNumber_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", " ");
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void admin_GetAppointmentTypeWithInvalidParams_Page_Null_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", null);
        queryParams.add("size", "10");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    // Size
    @Test
    void admin_GetAppointmentTypeWithInvalidParams_Size_NotNumber_UnprocessableEntity() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "abcd");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.size"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("Valor numérico inválido"));
    }

    @Test
    void admin_GetAppointmentTypeWithInvalidParams_Size_Negative_UnprocessableEntity() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "-1");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.size"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]").value("El tamaño debe ser mayor a 0"));
    }

    @Test
    void admin_GetAppointmentTypeWithInvalidParams_Size_Largest_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "111");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(111),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void admin_GetAppointmentTypeWithInvalidParams_Size_Blank_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", " ");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void admin_GetAppointmentTypeWithInvalidParams_Size_Null_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", null);

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    // Order
    @Test
    void admin_GetAppointmentTypeWithInvalidParams_Order_Without_OrderBy_UnprocessableEntity() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.order"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El campo para ordenar es requerido cuando se ha definido un orden"));
    }

    @Test
    void admin_GetAppointmentTypeWithInvalidParams_Order_Invalid_UnprocessableEntity() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "abcd");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.order"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("Solo se permite: asc, desc, none"));
    }

    @Test
    void admin_GetAppointmentTypeWithInvalidParams_Order_Blank_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", " ");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void admin_GetAppointmentTypeWithInvalidParams_Order_Empty_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", " ");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void admin_GetAppointmentTypeWithInvalidParams_Order_Null_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", null);

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    // OrderBy
    @Test
    void admin_GetAppointmentTypeWithInvalidParams_OrderBy_Name_Invalid_UnprocessableEntity() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");
        queryParams.add("order_by", "abcd");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.order_by"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("Solo los siguientes campos son válidos: id, name, duration_in_minutes"));
    }

    @Test
    void admin_GetAppointmentTypeWithInvalidParams_OrderBy_Name_Valid_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");
        queryParams.add("order_by", "name");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void admin_GetAppointmentTypeWithInvalidParams_OrderBy_Name_Null_UnprocessableEntity() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");
        queryParams.add("order_by", null);

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isUnprocessableEntity())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_UNPROCESSABLE_ENTITY),
                        jsonPath("$.details.length()").value(1),
                        jsonPath("$.details[0].field").value("query.order"),
                        jsonPath("$.details[0].messages.length()").value(1),
                        jsonPath("$.details[0].messages[0]")
                                .value("El campo para ordenar es requerido cuando se ha definido un orden"));
    }

    // Name
    @Test
    void admin_GetAppointmentTypeWithInvalidParams_AppointmentTypeName_Valid_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");
        queryParams.add("order_by", "name");
        queryParams.add("name", "Limpieza");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(1),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(1),
                        jsonPath("$.content[0].name").value("Limpieza"));
    }

    @Test
    void admin_GetAppointmentTypeWithInvalidParams_AppointmentTypeName_Blank_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");
        queryParams.add("order_by", "name");
        queryParams.add("name", " ");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void admin_GetAppointmentTypeWithInvalidParams_AppointmentTypeName_Empty_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");
        queryParams.add("order_by", "name");
        queryParams.add("name", "");

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }

    @Test
    void admin_GetAppointmentTypeWithInvalidParams_AppointmentTypeName_Null_Ok() throws Exception {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", "1");
        queryParams.add("size", "10");
        queryParams.add("order", "asc");
        queryParams.add("order_by", "name");
        queryParams.add("name", null);

        mockMvc.perform(get("/appointment/type")
                .queryParams(queryParams)
                .header("Authorization", BEARER_ADMIN_JWT))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.message").value(MESSAGE_OK),
                        jsonPath("$.page").value(1),
                        jsonPath("$.size").value(10),
                        jsonPath("$.total_elements").value(2),
                        jsonPath("$.total_pages").value(1),
                        jsonPath("$.content").isArray(),
                        jsonPath("$.content.length()").value(2));
    }
}
