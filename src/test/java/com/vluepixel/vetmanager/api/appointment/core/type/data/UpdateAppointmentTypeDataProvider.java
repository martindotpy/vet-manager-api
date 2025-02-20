package com.vluepixel.vetmanager.api.appointment.core.type.data;

import java.math.BigDecimal;

import com.vluepixel.vetmanager.api.appointment.type.domain.request.UpdateAppointmentTypeRequest;

/**
 * Update appointment type data provider.
 */
public class UpdateAppointmentTypeDataProvider {
    public static final UpdateAppointmentTypeRequest INVALID_UPDATE_APPOINTMENT_TYPE_REQUEST = UpdateAppointmentTypeRequest
            .builder()
            .id(10)
            .name("Limpieza Premium")
            .durationInMinutes(30)
            .price(BigDecimal.valueOf(199.99))
            .build();

    public static final UpdateAppointmentTypeRequest VALID_UPDATE_APPOINTMENT_TYPE_REQUEST = UpdateAppointmentTypeRequest
            .builder()
            .id(1)
            .name("Limpieza Premium")
            .durationInMinutes(30)
            .price(BigDecimal.valueOf(199.99))
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // ID
    public static final UpdateAppointmentTypeRequest INVALID_ID_NEGATIVE_UPDATE_APPOINTMENT_TYPE_REQUEST = UpdateAppointmentTypeRequest
            .builder()
            .id(-10)
            .name(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getName())
            .durationInMinutes(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes())
            .price(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getPrice())
            .build();

    // Name
    private static final int MAX_NAME_LENGTH = 20;
    public static final UpdateAppointmentTypeRequest INVALID_NAME_TOO_LONG_UPDATE_APPOINTMENT_TYPE_REQUEST = UpdateAppointmentTypeRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getId())
            .name("a".repeat(MAX_NAME_LENGTH + 1))
            .durationInMinutes(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes())
            .price(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getPrice())
            .build();

    public static final UpdateAppointmentTypeRequest VALID_NAME_MAX_LENGTH_UPDATE_APPOINTMENT_TYPE_REQUEST = UpdateAppointmentTypeRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getId())
            .name("a".repeat(MAX_NAME_LENGTH))
            .durationInMinutes(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes())
            .price(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getPrice())
            .build();

    public static final UpdateAppointmentTypeRequest INVALID_NAME_BLANK_UPDATE_APPOINTMENT_TYPE_REQUEST = UpdateAppointmentTypeRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getId())
            .name(" ")
            .durationInMinutes(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes())
            .price(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getPrice())
            .build();

    public static final UpdateAppointmentTypeRequest INVALID_NAME_EMPTY_UPDATE_APPOINTMENT_TYPE_REQUEST = UpdateAppointmentTypeRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getId())
            .name("")
            .durationInMinutes(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes())
            .price(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getPrice())
            .build();

    public static final UpdateAppointmentTypeRequest INVALID_NAME_NULL_UPDATE_APPOINTMENT_TYPE_REQUEST = UpdateAppointmentTypeRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getId())
            .name(null)
            .durationInMinutes(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes())
            .price(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getPrice())
            .build();

    // Duration In Minutes
    private static final int MAX_DURACTION_IN_MINUTES_VALUE = 720;
    public static final UpdateAppointmentTypeRequest INVALID_DURATION_IN_MINUTES_NEGATIVE_UPDATE_APPOINTMENT_TYPE_REQUEST = UpdateAppointmentTypeRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getId())
            .name(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getName())
            .durationInMinutes(-10)
            .price(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getPrice())
            .build();

    public static final UpdateAppointmentTypeRequest INVALID_DURATION_IN_MINUTES_ZERO_UPDATE_APPOINTMENT_TYPE_REQUEST = UpdateAppointmentTypeRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getId())
            .name(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getName())
            .durationInMinutes(0)
            .price(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getPrice())
            .build();

    public static final UpdateAppointmentTypeRequest INVALID_DURATION_IN_MINUTES_TOO_BIG_UPDATE_APPOINTMENT_TYPE_REQUEST = UpdateAppointmentTypeRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getId())
            .name(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getName())
            .durationInMinutes(MAX_DURACTION_IN_MINUTES_VALUE + 1)
            .price(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getPrice())
            .build();

    public static final UpdateAppointmentTypeRequest VALID_DURATION_IN_MINUTES_MAX_VALUE_UPDATE_APPOINTMENT_TYPE_REQUEST = UpdateAppointmentTypeRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getId())
            .name(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getName())
            .durationInMinutes(MAX_DURACTION_IN_MINUTES_VALUE)
            .price(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getPrice())
            .build();

    // Price
    private static final int MAX_PRICE_VALUE = 999;
    public static final UpdateAppointmentTypeRequest INVALID_PRICE_NEGATIVE_UPDATE_APPOINTMENT_TYPE_REQUEST = UpdateAppointmentTypeRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getId())
            .name(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getName())
            .durationInMinutes(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes())
            .price(BigDecimal.valueOf(-1))
            .build();

    public static final UpdateAppointmentTypeRequest INVALID_PRICE_ZERO_UPDATE_APPOINTMENT_TYPE_REQUEST = UpdateAppointmentTypeRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getId())
            .name(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getName())
            .durationInMinutes(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes())
            .price(BigDecimal.valueOf(0))
            .build();

    public static final UpdateAppointmentTypeRequest INVALID_PRICE_TOO_BIG_UPDATE_APPOINTMENT_TYPE_REQUEST = UpdateAppointmentTypeRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getId())
            .name(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getName())
            .durationInMinutes(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes())
            .price(BigDecimal.valueOf(MAX_PRICE_VALUE + 1))
            .build();

    public static final UpdateAppointmentTypeRequest VALID_PRICE_MAX_VALUE_UPDATE_APPOINTMENT_TYPE_REQUEST = UpdateAppointmentTypeRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getId())
            .name(VALID_UPDATE_APPOINTMENT_TYPE_REQUEST.getName())
            .durationInMinutes(MAX_DURACTION_IN_MINUTES_VALUE)
            .price((BigDecimal.valueOf(999.99)))
            .build();
}
