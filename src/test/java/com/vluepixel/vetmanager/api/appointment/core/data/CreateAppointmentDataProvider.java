package com.vluepixel.vetmanager.api.appointment.core.data;

import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DESCRIPTION_BLANK_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DESCRIPTION_EMPTY_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DESCRIPTION_NULL_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DETAILS_DURATION_IN_MINUTES_NEGATIVE_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DETAILS_DURATION_IN_MINUTES_TOO_BIG_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DETAILS_DURATION_IN_MINUTES_ZERO_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DETAILS_PRICE_NEGATIVE_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DETAILS_PRICE_NULL_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DETAILS_PRICE_TOO_BIG_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DETAILS_TYPE_ID_NEGATIVE_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DETAILS_TYPE_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_DETAILS_TYPE_ID_NULL_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_START_AT_MINUS_YEAR_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.INVALID_START_AT_NULL_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.VALID_DETAILS_PRICE_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.VALID_START_AT_FUTURE_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.VALID_START_AT_TODAY_UPDATE_APPOINTMENT_REQUEST;
import static com.vluepixel.vetmanager.api.appointment.core.data.UpdateAppointmentDataProvider.VALID_UPDATE_APPOINTMENT_REQUEST;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.vluepixel.vetmanager.api.appointment.core.domain.request.CreateAppointmentRequest;
import com.vluepixel.vetmanager.api.appointment.details.domain.request.CreateAppointmentDetailsRequest;

/**
 * Create appointment data provider.
 */
public class CreateAppointmentDataProvider {
    private static final List<CreateAppointmentDetailsRequest> DETAILS_VALID = Arrays.asList(
            CreateAppointmentDetailsRequest
                    .builder()
                    .durationInMinutes(1440)
                    .price(BigDecimal.valueOf(9999.99))
                    .appointmentTypeId(1L)
                    .build());

    public static final CreateAppointmentRequest VALID_CREATE_APPOINTMENT_REQUEST = CreateAppointmentRequest
            .builder()
            .startAt(VALID_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_VALID)
            .patientId(VALID_UPDATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // Start At
    public static final CreateAppointmentRequest INVALID_START_AT_MINUS_YEAR_CREATE_APPOINTMENT_REQUEST = CreateAppointmentRequest
            .builder()
            .startAt(INVALID_START_AT_MINUS_YEAR_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_CREATE_APPOINTMENT_REQUEST.getDescription())
            .details(VALID_CREATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(VALID_CREATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    public static final CreateAppointmentRequest VALID_START_AT_TODAY_CREATE_APPOINTMENT_REQUEST = CreateAppointmentRequest
            .builder()
            .startAt(VALID_START_AT_TODAY_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_CREATE_APPOINTMENT_REQUEST.getDescription())
            .details(VALID_CREATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(VALID_CREATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    public static final CreateAppointmentRequest VALID_START_AT_FUTURE_CREATE_APPOINTMENT_REQUEST = CreateAppointmentRequest
            .builder()
            .startAt(VALID_START_AT_FUTURE_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_CREATE_APPOINTMENT_REQUEST.getDescription())
            .details(VALID_CREATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(VALID_CREATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    public static final CreateAppointmentRequest INVALID_START_AT_NULL_CREATE_APPOINTMENT_REQUEST = CreateAppointmentRequest
            .builder()
            .startAt(INVALID_START_AT_NULL_UPDATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_CREATE_APPOINTMENT_REQUEST.getDescription())
            .details(VALID_CREATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(VALID_CREATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    // Description
    public static final CreateAppointmentRequest INVALID_DESCRIPTION_BLANK_CREATE_APPOINTMENT_REQUEST = CreateAppointmentRequest
            .builder()
            .startAt(VALID_CREATE_APPOINTMENT_REQUEST.getStartAt())
            .description(INVALID_DESCRIPTION_BLANK_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(VALID_CREATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(VALID_CREATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    public static final CreateAppointmentRequest INVALID_DESCRIPTION_EMPTY_CREATE_APPOINTMENT_REQUEST = CreateAppointmentRequest
            .builder()
            .startAt(VALID_CREATE_APPOINTMENT_REQUEST.getStartAt())
            .description(INVALID_DESCRIPTION_EMPTY_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(VALID_CREATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(VALID_CREATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    public static final CreateAppointmentRequest INVALID_DESCRIPTION_NULL_CREATE_APPOINTMENT_REQUEST = CreateAppointmentRequest
            .builder()
            .startAt(VALID_CREATE_APPOINTMENT_REQUEST.getStartAt())
            .description(INVALID_DESCRIPTION_NULL_UPDATE_APPOINTMENT_REQUEST.getDescription())
            .details(VALID_CREATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(VALID_CREATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    // Details

    // Duration In Minutes
    private static final List<CreateAppointmentDetailsRequest> DETAILS_DURATION_IN_MINUTES_TOO_BIG = Arrays.asList(
            CreateAppointmentDetailsRequest
                    .builder()
                    .durationInMinutes(INVALID_DETAILS_DURATION_IN_MINUTES_TOO_BIG_UPDATE_APPOINTMENT_REQUEST
                            .getDetails().get(0).getDurationInMinutes())
                    .price(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getPrice())
                    .appointmentTypeId(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getAppointmentTypeId())
                    .build());

    public static final CreateAppointmentRequest INVALID_DETAILS_DURATION_IN_MINUTES_TOO_BIG_CREATE_APPOINTMENT_REQUEST = CreateAppointmentRequest
            .builder()
            .startAt(VALID_CREATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_CREATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_DURATION_IN_MINUTES_TOO_BIG)
            .patientId(VALID_CREATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    private static final List<CreateAppointmentDetailsRequest> DETAILS_DURATION_IN_MINUTES_MAX_VALUE = Arrays.asList(
            CreateAppointmentDetailsRequest
                    .builder()
                    .durationInMinutes(VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST
                            .getDetails().get(0).getDurationInMinutes())
                    .price(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getPrice())
                    .appointmentTypeId(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getAppointmentTypeId())
                    .build());

    public static final CreateAppointmentRequest VALID_DETAILS_DURATION_IN_MINUTES_MAX_VALUE_CREATE_APPOINTMENT_REQUEST = CreateAppointmentRequest
            .builder()
            .startAt(VALID_CREATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_CREATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_DURATION_IN_MINUTES_MAX_VALUE)
            .patientId(VALID_CREATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    private static final List<CreateAppointmentDetailsRequest> DETAILS_DURATION_IN_MINUTES_ZERO = Arrays.asList(
            CreateAppointmentDetailsRequest
                    .builder()
                    .durationInMinutes(INVALID_DETAILS_DURATION_IN_MINUTES_ZERO_UPDATE_APPOINTMENT_REQUEST
                            .getDetails().get(0).getDurationInMinutes())
                    .price(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getPrice())
                    .appointmentTypeId(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getAppointmentTypeId())
                    .build());

    public static final CreateAppointmentRequest INVALID_DETAILS_DURATION_IN_MINUTES_ZERO_CREATE_APPOINTMENT_REQUEST = CreateAppointmentRequest
            .builder()
            .startAt(VALID_CREATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_CREATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_DURATION_IN_MINUTES_ZERO)
            .patientId(VALID_CREATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    private static final List<CreateAppointmentDetailsRequest> DETAILS_DURATION_IN_MINUTES_NEGATIVE = Arrays.asList(
            CreateAppointmentDetailsRequest
                    .builder()
                    .durationInMinutes(INVALID_DETAILS_DURATION_IN_MINUTES_NEGATIVE_UPDATE_APPOINTMENT_REQUEST
                            .getDetails().get(0).getDurationInMinutes())
                    .price(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getPrice())
                    .appointmentTypeId(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getAppointmentTypeId())
                    .build());

    public static final CreateAppointmentRequest INVALID_DETAILS_DURATION_IN_MINUTES_NEGATIVE_CREATE_APPOINTMENT_REQUEST = CreateAppointmentRequest
            .builder()
            .startAt(VALID_CREATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_CREATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_DURATION_IN_MINUTES_NEGATIVE)
            .patientId(VALID_CREATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    // Price
    private static final List<CreateAppointmentDetailsRequest> DETAILS_PRICE_TOO_BIG = Arrays.asList(
            CreateAppointmentDetailsRequest
                    .builder()
                    .durationInMinutes(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getDurationInMinutes())
                    .price(INVALID_DETAILS_PRICE_TOO_BIG_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0).getPrice())
                    .appointmentTypeId(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getAppointmentTypeId())
                    .build());

    public static final CreateAppointmentRequest INVALID_DETAILS_PRICE_TOO_BIG_CREATE_APPOINTMENT_REQUEST = CreateAppointmentRequest
            .builder()
            .startAt(VALID_CREATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_CREATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_PRICE_TOO_BIG)
            .patientId(VALID_CREATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    private static final List<CreateAppointmentDetailsRequest> DETAILS_PRICE_MAX_VALUE = Arrays.asList(
            CreateAppointmentDetailsRequest
                    .builder()
                    .durationInMinutes(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getDurationInMinutes())
                    .price(VALID_DETAILS_PRICE_MAX_VALUE_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0).getPrice())
                    .appointmentTypeId(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getAppointmentTypeId())
                    .build());

    public static final CreateAppointmentRequest VALID_DETAILS_PRICE_MAX_VALUE_CREATE_APPOINTMENT_REQUEST = CreateAppointmentRequest
            .builder()
            .startAt(VALID_CREATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_CREATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_PRICE_MAX_VALUE)
            .patientId(VALID_CREATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    private static final List<CreateAppointmentDetailsRequest> DETAILS_PRICE_NEGATIVE = Arrays.asList(
            CreateAppointmentDetailsRequest
                    .builder()
                    .durationInMinutes(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getDurationInMinutes())
                    .price(INVALID_DETAILS_PRICE_NEGATIVE_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0).getPrice())
                    .appointmentTypeId(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getAppointmentTypeId())
                    .build());

    public static final CreateAppointmentRequest INVALID_DETAILS_PRICE_NEGATIVE_CREATE_APPOINTMENT_REQUEST = CreateAppointmentRequest
            .builder()
            .startAt(VALID_CREATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_CREATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_PRICE_NEGATIVE)
            .patientId(VALID_CREATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    private static final List<CreateAppointmentDetailsRequest> DETAILS_PRICE_NULL = Arrays.asList(
            CreateAppointmentDetailsRequest
                    .builder()
                    .durationInMinutes(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getDurationInMinutes())
                    .price(INVALID_DETAILS_PRICE_NULL_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0).getPrice())
                    .appointmentTypeId(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getAppointmentTypeId())
                    .build());

    public static final CreateAppointmentRequest INVALID_DETAILS_PRICE_NULL_CREATE_APPOINTMENT_REQUEST = CreateAppointmentRequest
            .builder()
            .startAt(VALID_CREATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_CREATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_PRICE_NULL)
            .patientId(VALID_CREATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    // Type ID
    private static final List<CreateAppointmentDetailsRequest> DETAILS_TYPE_ID_NOT_FOUND = Arrays.asList(
            CreateAppointmentDetailsRequest
                    .builder()
                    .durationInMinutes(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getDurationInMinutes())
                    .price(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getPrice())
                    .appointmentTypeId(INVALID_DETAILS_TYPE_ID_NOT_FOUND_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0)
                            .getAppointmentTypeId())
                    .build());

    public static final CreateAppointmentRequest INVALID_DETAILS_TYPE_ID_NOT_FOUND_CREATE_APPOINTMENT_REQUEST = CreateAppointmentRequest
            .builder()
            .startAt(VALID_CREATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_CREATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_TYPE_ID_NOT_FOUND)
            .patientId(VALID_CREATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    private static final List<CreateAppointmentDetailsRequest> DETAILS_TYPE_ID_NEGATIVE = Arrays.asList(
            CreateAppointmentDetailsRequest
                    .builder()
                    .durationInMinutes(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getDurationInMinutes())
                    .price(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getPrice())
                    .appointmentTypeId(INVALID_DETAILS_TYPE_ID_NEGATIVE_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0)
                            .getAppointmentTypeId())
                    .build());

    public static final CreateAppointmentRequest INVALID_DETAILS_TYPE_ID_NEGATIVE_CREATE_APPOINTMENT_REQUEST = CreateAppointmentRequest
            .builder()
            .startAt(VALID_CREATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_CREATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_TYPE_ID_NEGATIVE)
            .patientId(VALID_CREATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    private static final List<CreateAppointmentDetailsRequest> DETAILS_TYPE_ID_NULL = Arrays.asList(
            CreateAppointmentDetailsRequest
                    .builder()
                    .durationInMinutes(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getDurationInMinutes())
                    .price(VALID_CREATE_APPOINTMENT_REQUEST.getDetails().get(0).getPrice())
                    .appointmentTypeId(INVALID_DETAILS_TYPE_ID_NULL_UPDATE_APPOINTMENT_REQUEST.getDetails().get(0)
                            .getAppointmentTypeId())
                    .build());

    public static final CreateAppointmentRequest INVALID_DETAILS_TYPE_ID_NULL_CREATE_APPOINTMENT_REQUEST = CreateAppointmentRequest
            .builder()
            .startAt(VALID_CREATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_CREATE_APPOINTMENT_REQUEST.getDescription())
            .details(DETAILS_TYPE_ID_NULL)
            .patientId(VALID_CREATE_APPOINTMENT_REQUEST.getPatientId())
            .build();

    // Patient ID
    public static final CreateAppointmentRequest INVALID_PATIENT_ID_NOT_FOUND_CREATE_APPOINTMENT_REQUEST = CreateAppointmentRequest
            .builder()
            .startAt(VALID_CREATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_CREATE_APPOINTMENT_REQUEST.getDescription())
            .details(VALID_CREATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(10L)
            .build();

    public static final CreateAppointmentRequest INVALID_PATIENT_ID_NEGATIVE_CREATE_APPOINTMENT_REQUEST = CreateAppointmentRequest
            .builder()
            .startAt(VALID_CREATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_CREATE_APPOINTMENT_REQUEST.getDescription())
            .details(VALID_CREATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(-1L)
            .build();

    public static final CreateAppointmentRequest INVALID_PATIENT_ID_NULL_CREATE_APPOINTMENT_REQUEST = CreateAppointmentRequest
            .builder()
            .startAt(VALID_CREATE_APPOINTMENT_REQUEST.getStartAt())
            .description(VALID_CREATE_APPOINTMENT_REQUEST.getDescription())
            .details(VALID_CREATE_APPOINTMENT_REQUEST.getDetails())
            .patientId(null)
            .build();
}
