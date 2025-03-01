package com.vluepixel.vetmanager.api.bill.sale.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vluepixel.vetmanager.api.appointment.core.domain.model.Appointment;
import com.vluepixel.vetmanager.api.bill.core.domain.model.Bill;
import com.vluepixel.vetmanager.api.bill.sale.application.dto.AppointmentSaleDto;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.AppointmentSale;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.CreateAppointmentSaleRequest;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.UpdateAppointmentSaleRequest;
import com.vluepixel.vetmanager.api.patient.core.application.mapper.PatientMapper;
import com.vluepixel.vetmanager.api.shared.application.mapper.BasicMapper;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;

/**
 * Appointment sale mapper.
 */
@Mapper(componentModel = "spring", uses = { StringUtilsMapper.class, PatientMapper.class })
public interface AppointmentSaleMapper
        extends BasicMapper<AppointmentSale, AppointmentSaleDto> {
    /**
     * Create appointment sale from request.
     *
     * <ul>
     * <li><strong>Ignores:</strong>
     * <ul>
     * <li><code>id</code></li>
     * <li><code>seller</code></li>
     * <li><code>price</code></li>
     * </ul>
     * </li>
     * </ul>
     *
     * @param request the create appointment sale request.
     * @return the appointment sale builder
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "bill", source = "billId")
    @Mapping(target = "appointment", source = "appointmentId")
    AppointmentSale fromRequest(CreateAppointmentSaleRequest request);

    /**
     * Update appointment sale from request.
     *
     * <ul>
     * <li><strong>Ignores:</strong>
     * <ul>
     * <li><code>bill</code></li>
     * <li><code>seller</code></li>
     * <li><code>price</code></li>
     * </ul>
     * </li>
     * </ul>
     *
     * @param request the update appointment sale request.
     * @return the appointment sale builder
     */
    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "bill", ignore = true)
    @Mapping(target = "appointment", source = "appointmentId")
    AppointmentSale fromRequest(UpdateAppointmentSaleRequest request);

    /**
     * Maps the appointment id to an appointment.
     *
     * @param appointmentId the appointment id.
     * @return the appointment
     */
    default Appointment mapAppointmentIdToAppointment(Long appointmentId) {
        return Appointment.builder().id(appointmentId).build();
    }

    /**
     * Maps the bill id to an bill.
     *
     * @param billId the bill id.
     * @return the bill
     */
    default Bill mapBillIdToBill(Long billId) {
        return Bill.builder().id(billId).build();
    }
}
