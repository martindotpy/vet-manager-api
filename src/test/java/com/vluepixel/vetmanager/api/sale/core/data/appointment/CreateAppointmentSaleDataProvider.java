package com.vluepixel.vetmanager.api.sale.core.data.appointment;

import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.INVALID_APPOINTMENT_ID_NEGATIVE_UPDATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.INVALID_APPOINTMENT_ID_NOT_FOUND_UPDATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.INVALID_BILL_ID_NEGATIVE_UPDATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.INVALID_BILL_ID_NOT_FOUND_UPDATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.INVALID_BILL_ID_NULL_UPDATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.INVALID_DISCOUNT_NEGATIVE_UPDATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.INVALID_DISCOUNT_NULL_UPDATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.INVALID_DISCOUNT_TOO_BIG_UPDATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.VALID_DISCOUNT_MAX_VALUE_UPDATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.VALID_DISCOUNT_ZERO_UPDATE_APPOINTMENT_SALE_REQUEST;
import static com.vluepixel.vetmanager.api.sale.core.data.appointment.UpdateAppointmentSaleDataProvider.VALID_UPDATE_APPOINTMENT_SALE_REQUEST;

import com.vluepixel.vetmanager.api.bill.sale.domain.request.CreateAppointmentSaleRequest;

/**
 * Create appointment sale data provider.
 */
public class CreateAppointmentSaleDataProvider {
    public static final CreateAppointmentSaleRequest VALID_CREATE_APPOINTMENT_SALE_REQUEST = CreateAppointmentSaleRequest
            .builder()
            .billId(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getBillId())
            .appointmentId(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getAppointmentId())
            .discount(VALID_UPDATE_APPOINTMENT_SALE_REQUEST.getDiscount())
            .build();

    // Bill ID
    public static final CreateAppointmentSaleRequest INVALID_BILL_ID_NOT_FOUND_CREATE_APPOINTMENT_SALE_REQUEST = CreateAppointmentSaleRequest
            .builder()
            .billId(INVALID_BILL_ID_NOT_FOUND_UPDATE_APPOINTMENT_SALE_REQUEST.getBillId())
            .appointmentId(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getAppointmentId())
            .discount(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateAppointmentSaleRequest INVALID_BILL_ID_NEGATIVE_CREATE_APPOINTMENT_SALE_REQUEST = CreateAppointmentSaleRequest
            .builder()
            .billId(INVALID_BILL_ID_NEGATIVE_UPDATE_APPOINTMENT_SALE_REQUEST.getBillId())
            .appointmentId(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getAppointmentId())
            .discount(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateAppointmentSaleRequest INVALID_BILL_ID_NULL_CREATE_APPOINTMENT_SALE_REQUEST = CreateAppointmentSaleRequest
            .builder()
            .billId(INVALID_BILL_ID_NULL_UPDATE_APPOINTMENT_SALE_REQUEST.getBillId())
            .appointmentId(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getAppointmentId())
            .discount(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getDiscount())
            .build();

    // Appointment ID
    public static final CreateAppointmentSaleRequest INVALID_APPOINTMENT_ID_NOT_FOUND_CREATE_APPOINTMENT_SALE_REQUEST = CreateAppointmentSaleRequest
            .builder()
            .billId(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getBillId())
            .appointmentId(INVALID_APPOINTMENT_ID_NOT_FOUND_UPDATE_APPOINTMENT_SALE_REQUEST.getAppointmentId())
            .discount(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateAppointmentSaleRequest INVALID_APPOINTMENT_ID_NEGATIVE_CREATE_APPOINTMENT_SALE_REQUEST = CreateAppointmentSaleRequest
            .builder()
            .billId(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getBillId())
            .appointmentId(INVALID_APPOINTMENT_ID_NEGATIVE_UPDATE_APPOINTMENT_SALE_REQUEST.getAppointmentId())
            .discount(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateAppointmentSaleRequest INVALID_APPOINTMENT_ID_NULL_CREATE_APPOINTMENT_SALE_REQUEST = CreateAppointmentSaleRequest
            .builder()
            .billId(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getBillId())
            .appointmentId(INVALID_APPOINTMENT_ID_NEGATIVE_UPDATE_APPOINTMENT_SALE_REQUEST.getAppointmentId())
            .discount(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getDiscount())
            .build();

    // Discount
    public static final CreateAppointmentSaleRequest INVALID_DISCOUNT_TOO_BIG_CREATE_APPOINTMENT_SALE_REQUEST = CreateAppointmentSaleRequest
            .builder()
            .billId(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getBillId())
            .appointmentId(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getAppointmentId())
            .discount(INVALID_DISCOUNT_TOO_BIG_UPDATE_APPOINTMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateAppointmentSaleRequest VALID_DISCOUNT_MAX_VALUE_CREATE_APPOINTMENT_SALE_REQUEST = CreateAppointmentSaleRequest
            .builder()
            .billId(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getBillId())
            .appointmentId(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getAppointmentId())
            .discount(VALID_DISCOUNT_MAX_VALUE_UPDATE_APPOINTMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateAppointmentSaleRequest VALID_DISCOUNT_ZERO_CREATE_APPOINTMENT_SALE_REQUEST = CreateAppointmentSaleRequest
            .builder()
            .billId(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getBillId())
            .appointmentId(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getAppointmentId())
            .discount(VALID_DISCOUNT_ZERO_UPDATE_APPOINTMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateAppointmentSaleRequest INVALID_DISCOUNT_NEGATIVE_CREATE_APPOINTMENT_SALE_REQUEST = CreateAppointmentSaleRequest
            .builder()
            .billId(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getBillId())
            .appointmentId(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getAppointmentId())
            .discount(INVALID_DISCOUNT_NEGATIVE_UPDATE_APPOINTMENT_SALE_REQUEST.getDiscount())
            .build();

    public static final CreateAppointmentSaleRequest INVALID_DISCOUNT_NULL_CREATE_APPOINTMENT_SALE_REQUEST = CreateAppointmentSaleRequest
            .builder()
            .billId(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getBillId())
            .appointmentId(VALID_CREATE_APPOINTMENT_SALE_REQUEST.getAppointmentId())
            .discount(INVALID_DISCOUNT_NULL_UPDATE_APPOINTMENT_SALE_REQUEST.getDiscount())
            .build();
}
