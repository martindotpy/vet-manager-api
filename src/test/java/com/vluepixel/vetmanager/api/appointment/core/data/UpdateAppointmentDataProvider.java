package com.vluepixel.vetmanager.api.appointment.core.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.vluepixel.vetmanager.api.appointment.core.domain.request.UpdateAppointmentRequest;
import com.vluepixel.vetmanager.api.appointment.details.domain.request.UpdateAppointmentDetailsRequest;

/**
 * Update appointment data provider.
 */
public class UpdateAppointmentDataProvider {
    private static final List<UpdateAppointmentDetailsRequest> details = Arrays.asList(
            UpdateAppointmentDetailsRequest
                    .builder()
                    .id(1L)
                    .durationInMinutes(1440)
                    .price(BigDecimal.valueOf(9999.99))
                    .appointmentTypeId(1L)
                    .build());

    public static final UpdateAppointmentRequest INVALID_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(10L)
            .startAt(LocalDateTime.now())
            .description("New Description")
            .details(details)
            .patientId(1L)
            .build();

    public static final UpdateAppointmentRequest INVALID_ID_NEGATIVE_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(-10L)
            .startAt(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    public static final UpdateAppointmentRequest INVALID_ID_NULL_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(null)
            .startAt(INVALID_ID_NEGATIVE_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(INVALID_ID_NEGATIVE_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(INVALID_ID_NEGATIVE_UPDATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(INVALID_ID_NEGATIVE_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    public static final UpdateAppointmentRequest VALID_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(1L)
            .startAt(INVALID_ID_NULL_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(INVALID_ID_NULL_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(INVALID_ID_NULL_UPDATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(INVALID_ID_NULL_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // Start At
    public static final UpdateAppointmentRequest INVALID_START_AT_MINUS_YEAR_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(LocalDateTime.now().minusYears(1))
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(VALID_UPDATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    public static final UpdateAppointmentRequest VALID_START_AT_TODAY_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(LocalDateTime.now())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(VALID_UPDATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    public static final UpdateAppointmentRequest VALID_START_AT_FUTURE_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(LocalDateTime.now().plusDays(1))
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(VALID_UPDATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    public static final UpdateAppointmentRequest INVALID_START_AT_NULL_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(null)
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(VALID_UPDATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    // Description
    public static final UpdateAppointmentRequest INVALID_DESCRIPTION_BLANK_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(" ")
            .details(VALID_UPDATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    public static final UpdateAppointmentRequest INVALID_DESCRIPTION_EMPTY_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description("")
            .details(VALID_UPDATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    public static final UpdateAppointmentRequest INVALID_DESCRIPTION_NULL_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(null)
            .details(VALID_UPDATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    // Details

    // Details ID
    private static final List<UpdateAppointmentDetailsRequest> DETAILS_ID_NOT_FOUND = Arrays.asList(
            UpdateAppointmentDetailsRequest
                    .builder()
                    .id(10L)
                    .durationInMinutes(1440)
                    .price(BigDecimal.valueOf(9999.99))
                    .appointmentTypeId(1L)
                    .build());

    public static final UpdateAppointmentRequest INVALID_DETAILS_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_ID_NOT_FOUND)
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    private static final List<UpdateAppointmentDetailsRequest> DETAILS_ID_NEGATIVE = Arrays.asList(
            UpdateAppointmentDetailsRequest
                    .builder()
                    .id(-1L)
                    .durationInMinutes(1440)
                    .price(BigDecimal.valueOf(9999.99))
                    .appointmentTypeId(1L)
                    .build());

    public static final UpdateAppointmentRequest INVALID_DETAILS_ID_NEGATIVE_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_ID_NEGATIVE)
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    private static final List<UpdateAppointmentDetailsRequest> DETAILS_ID_NULL = Arrays.asList(
            UpdateAppointmentDetailsRequest
                    .builder()
                    .id(null)
                    .durationInMinutes(1440)
                    .price(BigDecimal.valueOf(9999.99))
                    .appointmentTypeId(1L)
                    .build());

    public static final UpdateAppointmentRequest INVALID_DETAILS_ID_NULL_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_ID_NULL)
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    // Duration In Minutes
    private static final int MAX_DURATION_IN_MINUTES_VALUE = 1440;
    private static final List<UpdateAppointmentDetailsRequest> DETAILS_DURATION_IN_MINUTES_TOO_BIG = Arrays.asList(
            UpdateAppointmentDetailsRequest
                    .builder()
                    .id(1L)
                    .durationInMinutes(MAX_DURATION_IN_MINUTES_VALUE + 1)
                    .price(BigDecimal.valueOf(100))
                    .appointmentTypeId(1L)
                    .build());

    public static final UpdateAppointmentRequest INVALID_DETAILS_DURATION_IN_MINUTES_TOO_BIG_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_DURATION_IN_MINUTES_TOO_BIG)
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    private static final List<UpdateAppointmentDetailsRequest> DETAILS_DURATION_IN_MINUTES_MAX_VALUE = Arrays.asList(
            UpdateAppointmentDetailsRequest
                    .builder()
                    .id(1L)
                    .durationInMinutes(MAX_DURATION_IN_MINUTES_VALUE)
                    .price(BigDecimal.valueOf(100))
                    .appointmentTypeId(1L)
                    .build());

    public static final UpdateAppointmentRequest VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_DURATION_IN_MINUTES_MAX_VALUE)
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    private static final List<UpdateAppointmentDetailsRequest> DETAILS_DURATION_IN_MINUTES_ZERO = Arrays.asList(
            UpdateAppointmentDetailsRequest
                    .builder()
                    .id(1L)
                    .durationInMinutes(0)
                    .price(BigDecimal.valueOf(100))
                    .appointmentTypeId(1L)
                    .build());

    public static final UpdateAppointmentRequest INVALID_DETAILS_DURATION_IN_MINUTES_ZERO_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_DURATION_IN_MINUTES_ZERO)
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    private static final List<UpdateAppointmentDetailsRequest> DETAILS_DURATION_IN_MINUTES_NEGATIVE = Arrays.asList(
            UpdateAppointmentDetailsRequest
                    .builder()
                    .id(1L)
                    .durationInMinutes(-1)
                    .price(BigDecimal.valueOf(100))
                    .appointmentTypeId(1L)
                    .build());

    public static final UpdateAppointmentRequest INVALID_DETAILS_DURATION_IN_MINUTES_NEGATIVE_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_DURATION_IN_MINUTES_NEGATIVE)
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    // Price
    private static final int MAX_PRICE_VALUE = 9999;
    private static final List<UpdateAppointmentDetailsRequest> DETAILS_PRICE_TOO_BIG = Arrays.asList(
            UpdateAppointmentDetailsRequest
                    .builder()
                    .id(1L)
                    .durationInMinutes(60)
                    .price(BigDecimal.valueOf(MAX_PRICE_VALUE + 1))
                    .appointmentTypeId(1L)
                    .build());

    public static final UpdateAppointmentRequest INVALID_DETAILS_PRICE_TOO_BIG_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_PRICE_TOO_BIG)
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    private static final List<UpdateAppointmentDetailsRequest> DETAILS_PRICE_MAX_VALUE = Arrays.asList(
            UpdateAppointmentDetailsRequest
                    .builder()
                    .id(1L)
                    .durationInMinutes(60)
                    .price(BigDecimal.valueOf(MAX_PRICE_VALUE + 0.99))
                    .appointmentTypeId(1L)
                    .build());

    public static final UpdateAppointmentRequest VALID_DETAILS_PRICE_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_PRICE_MAX_VALUE)
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    private static final List<UpdateAppointmentDetailsRequest> DETAILS_PRICE_NEGATIVE = Arrays.asList(
            UpdateAppointmentDetailsRequest
                    .builder()
                    .id(1L)
                    .durationInMinutes(60)
                    .price(BigDecimal.valueOf(-1))
                    .appointmentTypeId(1L)
                    .build());

    public static final UpdateAppointmentRequest INVALID_DETAILS_PRICE_NEGATIVE_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_PRICE_NEGATIVE)
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    private static final List<UpdateAppointmentDetailsRequest> DETAILS_PRICE_NULL = Arrays.asList(
            UpdateAppointmentDetailsRequest
                    .builder()
                    .id(1L)
                    .durationInMinutes(60)
                    .price(null)
                    .appointmentTypeId(1L)
                    .build());

    public static final UpdateAppointmentRequest INVALID_DETAILS_PRICE_NULL_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_PRICE_NULL)
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    // Type ID
    private static final List<UpdateAppointmentDetailsRequest> DETAILS_TYPE_ID_NOT_FOUND = Arrays.asList(
            UpdateAppointmentDetailsRequest
                    .builder()
                    .id(1L)
                    .durationInMinutes(60)
                    .price(BigDecimal.valueOf(100))
                    .appointmentTypeId(10L)
                    .build());

    public static final UpdateAppointmentRequest INVALID_DETAILS_TYPE_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_TYPE_ID_NOT_FOUND)
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    private static final List<UpdateAppointmentDetailsRequest> DETAILS_TYPE_ID_NEGATIVE = Arrays.asList(
            UpdateAppointmentDetailsRequest
                    .builder()
                    .id(1L)
                    .durationInMinutes(60)
                    .price(BigDecimal.valueOf(100))
                    .appointmentTypeId(-1L)
                    .build());

    public static final UpdateAppointmentRequest INVALID_DETAILS_TYPE_ID_NEGATIVE_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_TYPE_ID_NEGATIVE)
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    private static final List<UpdateAppointmentDetailsRequest> DETAILS_TYPE_ID_NULL = Arrays.asList(
            UpdateAppointmentDetailsRequest
                    .builder()
                    .id(1L)
                    .durationInMinutes(60)
                    .price(BigDecimal.valueOf(100))
                    .appointmentTypeId(null)
                    .build());

    public static final UpdateAppointmentRequest INVALID_DETAILS_TYPE_ID_NULL_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_TYPE_ID_NULL)
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    // Patient ID
    public static final UpdateAppointmentRequest INVALID_PATIENT_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(VALID_UPDATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(10L)
            .build();

    public static final UpdateAppointmentRequest INVALID_PATIENT_ID_NEGATIVE_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(VALID_UPDATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(-1L)
            .build();

    public static final UpdateAppointmentRequest INVALID_PATIENT_ID_NULL_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(VALID_UPDATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(null)
            .build();
}
