package com.vluepixel.vetmanager.api.appointment.core.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;

import com.vluepixel.vetmanager.api.appointment.core.application.dto.AppointmentDto;
import com.vluepixel.vetmanager.api.appointment.core.domain.model.Appointment;
import com.vluepixel.vetmanager.api.appointment.core.domain.request.CreateAppointmentRequest;
import com.vluepixel.vetmanager.api.appointment.core.domain.request.UpdateAppointmentRequest;
import com.vluepixel.vetmanager.api.appointment.details.application.mapper.AppointmentDetailsMapper;
import com.vluepixel.vetmanager.api.appointment.details.domain.model.AppointmentDetails;
import com.vluepixel.vetmanager.api.appointment.details.domain.request.CreateAppointmentDetailsRequest;
import com.vluepixel.vetmanager.api.appointment.details.domain.request.UpdateAppointmentDetailsRequest;
import com.vluepixel.vetmanager.api.patient.core.application.mapper.PatientMapper;
import com.vluepixel.vetmanager.api.patient.core.domain.model.Patient;
import com.vluepixel.vetmanager.api.shared.application.mapper.CrudMapper;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;

/**
 * Appointment mapper.
 */
@Mapper(componentModel = "spring", uses = {
        AppointmentDetailsMapper.class, PatientMapper.class, StringUtilsMapper.class
})
public interface AppointmentMapper
        extends CrudMapper<Appointment, AppointmentDto, Appointment.AppointmentBuilder> {
    /**
     * Creates a new {@link Appointment} builder.
     *
     * @return the builder
     */
    @ObjectFactory
    default Appointment.AppointmentBuilder createAppointmentBuilder() {
        return Appointment.builder();
    }

    /**
     * Create appointment from request.
     *
     * <ul>
     * <li><strong>Ignores:</strong>
     * <ul>
     * <li><code>id</code></li>
     * <li><code>deleted</code></li>
     * <li><code>createdAt</code></li>
     * <li><code>createdBy</code></li>
     * </ul>
     * </li>
     * </ul>
     *
     * @param request the create appointment request.
     * @return the appointment builder
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "patient", source = "patientId")
    Appointment.AppointmentBuilder fromRequest(CreateAppointmentRequest request);

    /**
     * Update appointment from request.
     *
     * <ul>
     * <li><strong>Ignores:</strong>
     * <ul>
     * <li><code>deleted</code></li>
     * <li><code>createdAt</code></li>
     * <li><code>createdBy</code></li>
     * </ul>
     * </li>
     * </ul>
     *
     * @param request the update appointment request.
     * @return the appointment builder
     */
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "patient", source = "patientId")
    Appointment.AppointmentBuilder fromRequest(UpdateAppointmentRequest request);

    /**
     * Maps appointment details request to appointment details.
     *
     * @param request the create appointment details request.
     * @return the appointment details
     */
    default AppointmentDetails mapDetailsToDetails(CreateAppointmentDetailsRequest request) {
        return AppointmentDetailsMapper.INSTANCE.fromRequest(request).build();
    }

    /**
     * Maps appointment details request to appointment details.
     *
     * @param request the update appointment details request.
     * @return the appointment details
     */
    default AppointmentDetails mapDetailsToDetails(UpdateAppointmentDetailsRequest request) {
        return AppointmentDetailsMapper.INSTANCE.fromRequest(request).build();
    }

    /**
     * Map patient id to patient.
     *
     * @param patientId the patient id.
     * @return the patient
     */
    default Patient mapPatientIdToPatient(Long patientId) {
        return Patient.builder().id(patientId).build();
    }
}
