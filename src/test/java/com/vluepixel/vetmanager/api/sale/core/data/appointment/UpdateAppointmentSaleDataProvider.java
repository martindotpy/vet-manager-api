package com.vluepixel.vetmanager.api.sale.core.data.appointment;

import com.vluepixel.vetmanager.api.bill.sale.domain.request.UpdateAppointmentSaleRequest;

/**
 * Update appointment sale data provider.
 */
public class UpdateAppointmentSaleDataProvider {
    public static final UpdateAppointmentSaleRequest INVALID_ID_NOT_FOUND_UPDATE_APPOINTMET_SALE_REQUEST = UpdateAppointmentSaleRequest
            .builder()
            .id(10)
            .appointmentId(1L)
            .discount(20)
            .build();

    public static final UpdateAppointmentSaleRequest INVALID_ID_NEGATIVE_UPDATE_APPOINTMET_SALE_REQUEST = UpdateAppointmentSaleRequest
            .builder()
            .id(-1)
            .appointmentId(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMET_SALE_REQUEST.getAppointmentId())
            .discount(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMET_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateAppointmentSaleRequest INVALID_ID_NULL_UPDATE_APPOINTMET_SALE_REQUEST = UpdateAppointmentSaleRequest
            .builder()
            .id(null)
            .appointmentId(INVALID_ID_NEGATIVE_UPDATE_APPOINTMET_SALE_REQUEST.getAppointmentId())
            .discount(INVALID_ID_NEGATIVE_UPDATE_APPOINTMET_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateAppointmentSaleRequest VALID_UPDATE_APPOINTMENT_SALE_REQUEST = UpdateAppointmentSaleRequest
            .builder()
            .id(1)
            .appointmentId(INVALID_ID_NULL_UPDATE_APPOINTMET_SALE_REQUEST.getAppointmentId())
            .discount(INVALID_ID_NULL_UPDATE_APPOINTMET_SALE_REQUEST.getDiscount())
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // Appointment ID
    public static final UpdateAppointmentSaleRequest INVALID_APPOINTMENT_ID_NOT_FOUND_UPDATE_APPOINTMENT_SALE_REQUEST = UpdateAppointmentSaleRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getId())
            .appointmentId(10L)
            .discount(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateAppointmentSaleRequest INVALID_APPOINTMENT_ID_NEGATIVE_UPDATE_APPOINTMENT_SALE_REQUEST = UpdateAppointmentSaleRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getId())
            .appointmentId(-1L)
            .discount(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateAppointmentSaleRequest INVALID_APPOINTMENT_ID_NULL_UPDATE_APPOINTMENT_SALE_REQUEST = UpdateAppointmentSaleRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getId())
            .appointmentId(null)
            .discount(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getDiscount())
            .build();

    // Discount
    private static final int MAX_DISCOUNT_VALUE = 100;
    public static final UpdateAppointmentSaleRequest INVALID_DISCOUNT_TOO_BIG_UPDATE_APPOINTMENT_SALE_REQUEST = UpdateAppointmentSaleRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getId())
            .appointmentId(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getAppointmentId())
            .discount(MAX_DISCOUNT_VALUE + 1)
            .build();

    public static final UpdateAppointmentSaleRequest VALID_DISCOUNT_MAX_VALUE_UPDATE_APPOINTMENT_SALE_REQUEST = UpdateAppointmentSaleRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getId())
            .appointmentId(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getAppointmentId())
            .discount(MAX_DISCOUNT_VALUE)
            .build();

    public static final UpdateAppointmentSaleRequest INVALID_DISCOUNT_ZERO_UPDATE_APPOINTMENT_SALE_REQUEST = UpdateAppointmentSaleRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getId())
            .appointmentId(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getAppointmentId())
            .discount(0)
            .build();

    public static final UpdateAppointmentSaleRequest INVALID_DISCOUNT_NEGATIVE_UPDATE_APPOINTMENT_SALE_REQUEST = UpdateAppointmentSaleRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getId())
            .appointmentId(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getAppointmentId())
            .discount(-1)
            .build();

    public static final UpdateAppointmentSaleRequest INVALID_DISCOUNT_NULL_UPDATE_APPOINTMENT_SALE_REQUEST = UpdateAppointmentSaleRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getId())
            .appointmentId(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getAppointmentId())
            .discount(null)
            .build();
}
