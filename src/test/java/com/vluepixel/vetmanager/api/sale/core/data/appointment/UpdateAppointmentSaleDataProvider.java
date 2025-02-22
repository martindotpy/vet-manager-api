package com.vluepixel.vetmanager.api.sale.core.data.appointment;

import com.vluepixel.vetmanager.api.bill.sale.domain.request.UpdateAppointmentSaleRequest;

/**
 * Update appointment sale data provider.
 */
public class UpdateAppointmentSaleDataProvider {
    public static final UpdateAppointmentSaleRequest INVALID_ID_NOT_FOUND_UPDATE_APPOINTMET_SALE_REQUEST = UpdateAppointmentSaleRequest
            .builder()
            .id(10)
            .billId(1L)
            .appointmentId(1L)
            .discount(20)
            .build();

    public static final UpdateAppointmentSaleRequest INVALID_ID_NEGATIVE_UPDATE_APPOINTMET_SALE_REQUEST = UpdateAppointmentSaleRequest
            .builder()
            .id(-1)
            .billId(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMET_SALE_REQUEST.getBillId())
            .appointmentId(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMET_SALE_REQUEST.getAppointmentId())
            .discount(INVALID_ID_NOT_FOUND_UPDATE_APPOINTMET_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateAppointmentSaleRequest INVALID_ID_NULL_UPDATE_APPOINTMET_SALE_REQUEST = UpdateAppointmentSaleRequest
            .builder()
            .id(null)
            .billId(INVALID_ID_NEGATIVE_UPDATE_APPOINTMET_SALE_REQUEST.getBillId())
            .appointmentId(INVALID_ID_NEGATIVE_UPDATE_APPOINTMET_SALE_REQUEST.getAppointmentId())
            .discount(INVALID_ID_NEGATIVE_UPDATE_APPOINTMET_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateAppointmentSaleRequest VALID_UPDATE_APPOINTMENT_SALE_REQUEST = UpdateAppointmentSaleRequest
            .builder()
            .id(1)
            .billId(INVALID_ID_NULL_UPDATE_APPOINTMET_SALE_REQUEST.getBillId())
            .appointmentId(INVALID_ID_NULL_UPDATE_APPOINTMET_SALE_REQUEST.getAppointmentId())
            .discount(INVALID_ID_NULL_UPDATE_APPOINTMET_SALE_REQUEST.getDiscount())
            .build();

    // -----------------------------------------------------------------------------------------------------------------
    // Validations
    // -----------------------------------------------------------------------------------------------------

    // Bill ID
    public static final UpdateAppointmentSaleRequest INVALID_BILL_ID_NOT_FOUND_UPDATE_APPOINTMENT_SALE_REQUEST = UpdateAppointmentSaleRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getId())
            .billId(10L)
            .appointmentId(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getAppointmentId())
            .discount(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateAppointmentSaleRequest INVALID_BILL_ID_NEGATIVE_UPDATE_APPOINTMENT_SALE_REQUEST = UpdateAppointmentSaleRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getId())
            .billId(-1L)
            .appointmentId(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getAppointmentId())
            .discount(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateAppointmentSaleRequest INVALID_BILL_ID_NULL_UPDATE_APPOINTMENT_SALE_REQUEST = UpdateAppointmentSaleRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getId())
            .billId(null)
            .appointmentId(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getAppointmentId())
            .discount(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getDiscount())
            .build();

    // Appointment ID
    public static final UpdateAppointmentSaleRequest INVALID_APPOINTMENT_ID_NOT_FOUND_UPDATE_APPOINTMENT_SALE_REQUEST = UpdateAppointmentSaleRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getId())
            .billId(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getBillId())
            .appointmentId(10L)
            .discount(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateAppointmentSaleRequest INVALID_APPOINTMENT_ID_NEGATIVE_UPDATE_APPOINTMENT_SALE_REQUEST = UpdateAppointmentSaleRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getId())
            .billId(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getBillId())
            .appointmentId(-1L)
            .discount(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final UpdateAppointmentSaleRequest INVALID_APPOINTMENT_ID_NULL_UPDATE_APPOINTMENT_SALE_REQUEST = UpdateAppointmentSaleRequest
            .builder()
            .id(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getId())
            .billId(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getBillId())
            .appointmentId(null)
            .discount(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getDiscount())
            .build();
}
