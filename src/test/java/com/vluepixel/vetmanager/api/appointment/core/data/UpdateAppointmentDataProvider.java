package com.vluepixel.vetmanager.api.appointment.core.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.vluepixel.vetmanager.api.appointment.core.domain.request.UpdateAppointmentRequest;
import com.vluepixel.vetmanager.api.appointment.details.domain.request.UpdateAppointmentDetailsRequest;

/**
 * Update appointment data provider.
 */
public class UpdateAppointmentDataProvider {
    public static final UpdateAppointmentRequest INVALID_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(10L)
            .startAt(LocalDateTime.now())
            .description("New Description")
            .details(new ArrayList<UpdateAppointmentDetailsRequest>(
                    List.of(
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(1L)
                                    .durationInMinutes(60)
                                    .price(BigDecimal.valueOf(100))
                                    .appointmentTypeId(1L)
                                    .build(),
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(2L)
                                    .durationInMinutes(120)
                                    .price(BigDecimal.valueOf(200))
                                    .appointmentTypeId(1L)
                                    .build())))
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
            .startAt(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    public static final UpdateAppointmentRequest VALID_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(1L)
            .startAt(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // Start At
    public static final UpdateAppointmentRequest VALID_START_AT_MINUS_YEAR_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
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

    public static final UpdateAppointmentRequest INVALID_START_AT_FUTURE_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
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
    public static final UpdateAppointmentRequest INVALID_DETAILS_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(new ArrayList<UpdateAppointmentDetailsRequest>(
                    List.of(
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(10L)
                                    .durationInMinutes(60)
                                    .price(BigDecimal.valueOf(100))
                                    .appointmentTypeId(1L)
                                    .build(),
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(2L)
                                    .durationInMinutes(120)
                                    .price(BigDecimal.valueOf(200))
                                    .appointmentTypeId(1L)
                                    .build())))
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    public static final UpdateAppointmentRequest INVALID_DETAILS_ID_NEGATIVE_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(new ArrayList<UpdateAppointmentDetailsRequest>(
                    List.of(
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(-1L)
                                    .durationInMinutes(60)
                                    .price(BigDecimal.valueOf(100))
                                    .appointmentTypeId(1L)
                                    .build(),
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(2L)
                                    .durationInMinutes(120)
                                    .price(BigDecimal.valueOf(200))
                                    .appointmentTypeId(1L)
                                    .build())))
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    public static final UpdateAppointmentRequest INVALID_DETAILS_ID_NULL_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(new ArrayList<UpdateAppointmentDetailsRequest>(
                    List.of(
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(null)
                                    .durationInMinutes(60)
                                    .price(BigDecimal.valueOf(100))
                                    .appointmentTypeId(1L)
                                    .build(),
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(2L)
                                    .durationInMinutes(120)
                                    .price(BigDecimal.valueOf(200))
                                    .appointmentTypeId(1L)
                                    .build())))
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    // Duration In Minutes
    private static final int MAX_DURATION_IN_MINUTES_VALUE = 1440;
    public static final UpdateAppointmentRequest INVALID_DETAILS_DURATION_IN_MINUTES_TOO_BIG_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(new ArrayList<UpdateAppointmentDetailsRequest>(
                    List.of(
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(1L)
                                    .durationInMinutes(MAX_DURATION_IN_MINUTES_VALUE + 1)
                                    .price(BigDecimal.valueOf(100))
                                    .appointmentTypeId(1L)
                                    .build(),
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(2L)
                                    .durationInMinutes(120)
                                    .price(BigDecimal.valueOf(200))
                                    .appointmentTypeId(1L)
                                    .build())))
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    public static final UpdateAppointmentRequest VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(new ArrayList<UpdateAppointmentDetailsRequest>(
                    List.of(
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(1L)
                                    .durationInMinutes(MAX_DURATION_IN_MINUTES_VALUE)
                                    .price(BigDecimal.valueOf(100))
                                    .appointmentTypeId(1L)
                                    .build(),
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(2L)
                                    .durationInMinutes(120)
                                    .price(BigDecimal.valueOf(200))
                                    .appointmentTypeId(1L)
                                    .build())))
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    public static final UpdateAppointmentRequest INVALID_DETAILS_DURATION_IN_MINUTES_ZERO_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(new ArrayList<UpdateAppointmentDetailsRequest>(
                    List.of(
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(1L)
                                    .durationInMinutes(0)
                                    .price(BigDecimal.valueOf(100))
                                    .appointmentTypeId(1L)
                                    .build(),
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(2L)
                                    .durationInMinutes(120)
                                    .price(BigDecimal.valueOf(200))
                                    .appointmentTypeId(1L)
                                    .build())))
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    public static final UpdateAppointmentRequest INVALID_DETAILS_DURATION_IN_MINUTES_NEGATIVE_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(new ArrayList<UpdateAppointmentDetailsRequest>(
                    List.of(
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(1L)
                                    .durationInMinutes(-1)
                                    .price(BigDecimal.valueOf(100))
                                    .appointmentTypeId(1L)
                                    .build(),
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(2L)
                                    .durationInMinutes(120)
                                    .price(BigDecimal.valueOf(200))
                                    .appointmentTypeId(1L)
                                    .build())))
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    // Price
    private static final int MAX_PRICE_VALUE = 9999;
    public static final UpdateAppointmentRequest INVALID_DETAILS_PRICE_TOO_BIG_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(new ArrayList<UpdateAppointmentDetailsRequest>(
                    List.of(
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(1L)
                                    .durationInMinutes(60)
                                    .price(BigDecimal.valueOf(MAX_PRICE_VALUE + 1))
                                    .appointmentTypeId(1L)
                                    .build(),
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(2L)
                                    .durationInMinutes(120)
                                    .price(BigDecimal.valueOf(200))
                                    .appointmentTypeId(1L)
                                    .build())))
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    public static final UpdateAppointmentRequest VALID_DETAILS_PRICE_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(new ArrayList<UpdateAppointmentDetailsRequest>(
                    List.of(
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(1L)
                                    .durationInMinutes(60)
                                    .price(BigDecimal.valueOf(MAX_PRICE_VALUE + 0.99))
                                    .appointmentTypeId(1L)
                                    .build(),
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(2L)
                                    .durationInMinutes(120)
                                    .price(BigDecimal.valueOf(200))
                                    .appointmentTypeId(1L)
                                    .build())))
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    public static final UpdateAppointmentRequest INVALID_DETAILS_PRICE_NEGATIVE_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(new ArrayList<UpdateAppointmentDetailsRequest>(
                    List.of(
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(1L)
                                    .durationInMinutes(60)
                                    .price(BigDecimal.valueOf(-1))
                                    .appointmentTypeId(1L)
                                    .build(),
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(2L)
                                    .durationInMinutes(120)
                                    .price(BigDecimal.valueOf(200))
                                    .appointmentTypeId(1L)
                                    .build())))
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    public static final UpdateAppointmentRequest INVALID_DETAILS_PRICE_NULL_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(new ArrayList<UpdateAppointmentDetailsRequest>(
                    List.of(
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(1L)
                                    .durationInMinutes(60)
                                    .price(null)
                                    .appointmentTypeId(1L)
                                    .build(),
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(2L)
                                    .durationInMinutes(120)
                                    .price(BigDecimal.valueOf(200))
                                    .appointmentTypeId(1L)
                                    .build())))
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    // Type ID
    public static final UpdateAppointmentRequest INVALID_DETAILS_TYPE_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(new ArrayList<UpdateAppointmentDetailsRequest>(
                    List.of(
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(1L)
                                    .durationInMinutes(60)
                                    .price(BigDecimal.valueOf(100))
                                    .appointmentTypeId(10L)
                                    .build(),
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(2L)
                                    .durationInMinutes(120)
                                    .price(BigDecimal.valueOf(200))
                                    .appointmentTypeId(1L)
                                    .build())))
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    public static final UpdateAppointmentRequest INVALID_DETAILS_TYPE_ID_NEGATIVE_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(new ArrayList<UpdateAppointmentDetailsRequest>(
                    List.of(
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(1L)
                                    .durationInMinutes(60)
                                    .price(BigDecimal.valueOf(100))
                                    .appointmentTypeId(-1L)
                                    .build(),
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(2L)
                                    .durationInMinutes(120)
                                    .price(BigDecimal.valueOf(200))
                                    .appointmentTypeId(1L)
                                    .build())))
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    public static final UpdateAppointmentRequest INVALID_DETAILS_TYPE_ID_NULL_UPDATE_APPOINTMENT_REQUEST = UpdateAppointmentRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_REQUEST.getId())
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(new ArrayList<UpdateAppointmentDetailsRequest>(
                    List.of(
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(1L)
                                    .durationInMinutes(60)
                                    .price(BigDecimal.valueOf(100))
                                    .appointmentTypeId(null)
                                    .build(),
                            UpdateAppointmentDetailsRequest
                                    .builder()
                                    .id(2L)
                                    .durationInMinutes(120)
                                    .price(BigDecimal.valueOf(200))
                                    .appointmentTypeId(1L)
                                    .build())))
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
