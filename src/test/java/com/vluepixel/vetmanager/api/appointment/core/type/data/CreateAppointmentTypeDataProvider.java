package com.vluepixel.vetmanager.api.appointment.core.type.data;

import static com.vluepixel.vetmanager.api.appointment.core.type.data.UpdateAppointmentTypeDataProvider.INVALID_DURATION_IN_MINUTES_NEGATIVE_UPDATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.UpdateAppointmentTypeDataProvider.INVALID_DURATION_IN_MINUTES_TOO_BIG_UPDATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.UpdateAppointmentTypeDataProvider.INVALID_DURATION_IN_MINUTES_ZERO_UPDATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.UpdateAppointmentTypeDataProvider.INVALID_NAME_BLANK_UPDATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.UpdateAppointmentTypeDataProvider.INVALID_NAME_EMPTY_UPDATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.UpdateAppointmentTypeDataProvider.INVALID_NAME_NULL_UPDATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.UpdateAppointmentTypeDataProvider.INVALID_NAME_TOO_LONG_UPDATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.UpdateAppointmentTypeDataProvider.INVALID_PRICE_NEGATIVE_UPDATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.UpdateAppointmentTypeDataProvider.INVALID_PRICE_TOO_BIG_UPDATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.UpdateAppointmentTypeDataProvider.INVALID_PRICE_ZERO_UPDATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.UpdateAppointmentTypeDataProvider.VALID_DURATION_IN_MINUTES_MAX_VALUE_UPDATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.UpdateAppointmentTypeDataProvider.VALID_NAME_MAX_LENGTH_UPDATE_APPOINTMENT_TYPE_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.type.data.UpdateAppointmentTypeDataProvider.VALID_PRICE_MAX_VALUE_UPDATE_APPOINTMENT_TYPE_REQUEST;

import java.math.BigDecimal;

import com.vluepixel.vetmanager.api.appointment.type.domain.request.CreateAppointmentTypeRequest;

/**
 * Create appointment type data provider.
 */
public class CreateAppointmentTypeDataProvider {
    public static final CreateAppointmentTypeRequest VALID_CREATE_APPOINTMENT_TYPE_REQUEST = CreateAppointmentTypeRequest
            .builder()
            .name("Limpieza Premim 2.0")
            .durationInMinutes(60)
            .price(BigDecimal.valueOf(299.99))
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // Name
    public static final CreateAppointmentTypeRequest INVALID_NAME_TOO_LONG_CREATE_APPOINTMENT_TYPE_REQUEST = CreateAppointmentTypeRequest
            .builder()
            .name(INVALID_NAME_TOO_LONG_UPDATE_APPOINTMENT_TYPE_REQUEST.getName())
            .durationInMinutes(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes())
            .price(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getPrice())
            .build();

    public static final CreateAppointmentTypeRequest VALID_NAME_MAX_LENGTH_CREATE_APPOINTMENT_TYPE_REQUEST = CreateAppointmentTypeRequest
            .builder()
            .name(VALID_NAME_MAX_LENGTH_UPDATE_APPOINTMENT_TYPE_REQUEST.getName())
            .durationInMinutes(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes())
            .price(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getPrice())
            .build();

    public static final CreateAppointmentTypeRequest INVALID_NAME_BLANK_CREATE_APPOINTMENT_TYPE_REQUEST = CreateAppointmentTypeRequest
            .builder()
            .name(INVALID_NAME_BLANK_UPDATE_APPOINTMENT_TYPE_REQUEST.getName())
            .durationInMinutes(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes())
            .price(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getPrice())
            .build();

    public static final CreateAppointmentTypeRequest INVALID_NAME_EMPTY_CREATE_APPOINTMENT_TYPE_REQUEST = CreateAppointmentTypeRequest
            .builder()
            .name(INVALID_NAME_EMPTY_UPDATE_APPOINTMENT_TYPE_REQUEST.getName())
            .durationInMinutes(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes())
            .price(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getPrice())
            .build();

    public static final CreateAppointmentTypeRequest INVALID_NAME_NULL_CREATE_APPOINTMENT_TYPE_REQUEST = CreateAppointmentTypeRequest
            .builder()
            .name(INVALID_NAME_NULL_UPDATE_APPOINTMENT_TYPE_REQUEST.getName())
            .durationInMinutes(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes())
            .price(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getPrice())
            .build();

    // Duration In Minutes
    public static final CreateAppointmentTypeRequest INVALID_DURATION_IN_MINUTES_NEGATIVE_CREATE_APPOINTMENT_TYPE_REQUEST = CreateAppointmentTypeRequest
            .builder()
            .name(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getName())
            .durationInMinutes(
                    INVALID_DURATION_IN_MINUTES_NEGATIVE_UPDATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes())
            .price(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getPrice())
            .build();

    public static final CreateAppointmentTypeRequest INVALID_DURATION_IN_MINUTES_ZERO_CREATE_APPOINTMENT_TYPE_REQUEST = CreateAppointmentTypeRequest
            .builder()
            .name(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getName())
            .durationInMinutes(
                    INVALID_DURATION_IN_MINUTES_ZERO_UPDATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes())
            .price(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getPrice())
            .build();

    public static final CreateAppointmentTypeRequest INVALID_DURATION_IN_MINUTES_TOO_BIG_CREATE_APPOINTMENT_TYPE_REQUEST = CreateAppointmentTypeRequest
            .builder()
            .name(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getName())
            .durationInMinutes(
                    INVALID_DURATION_IN_MINUTES_TOO_BIG_UPDATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes())
            .price(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getPrice())
            .build();

    public static final CreateAppointmentTypeRequest VALID_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_TYPE_REQUEST = CreateAppointmentTypeRequest
            .builder()
            .name(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getName())
            .durationInMinutes(
                    VALID_DURATION_IN_MINUTES_MAX_VALUE_UPDATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes())
            .price(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getPrice())
            .build();

    // Price
    public static final CreateAppointmentTypeRequest INVALID_PRICE_NEGATIVE_CREATE_APPOINTMENT_TYPE_REQUEST = CreateAppointmentTypeRequest
            .builder()
            .name(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getName())
            .durationInMinutes(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes())
            .price(INVALID_PRICE_NEGATIVE_UPDATE_APPOINTMENT_TYPE_REQUEST.getPrice())
            .build();

    public static final CreateAppointmentTypeRequest INVALID_PRICE_ZERO_CREATE_APPOINTMENT_TYPE_REQUEST = CreateAppointmentTypeRequest
            .builder()
            .name(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getName())
            .durationInMinutes(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes())
            .price(INVALID_PRICE_ZERO_UPDATE_APPOINTMENT_TYPE_REQUEST.getPrice())
            .build();

    public static final CreateAppointmentTypeRequest INVALID_PRICE_TOO_BIG_CREATE_APPOINTMENT_TYPE_REQUEST = CreateAppointmentTypeRequest
            .builder()
            .name(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getName())
            .durationInMinutes(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes())
            .price(INVALID_PRICE_TOO_BIG_UPDATE_APPOINTMENT_TYPE_REQUEST.getPrice())
            .build();

    public static final CreateAppointmentTypeRequest VALID_PRICE_MAX_VALUE_CREATE_APPOINTMENT_TYPE_REQUEST = CreateAppointmentTypeRequest
            .builder()
            .name(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getName())
            .durationInMinutes(VALID_CREATE_APPOINTMENT_TYPE_REQUEST.getDurationInMinutes())
            .price(VALID_PRICE_MAX_VALUE_UPDATE_APPOINTMENT_TYPE_REQUEST.getPrice())
            .build();
}
