package com.vluepixel.vetmanager.api.patient.core.adapter.in.controller;

import static com.vluepixel.vetmanager.api.shared.adapter.in.util.ResponseShortcuts.ok;
import static com.vluepixel.vetmanager.api.shared.adapter.in.util.ResponseShortcuts.okPaginated;
import static com.vluepixel.vetmanager.api.shared.domain.criteria.Filter.like;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vluepixel.vetmanager.api.patient.core.adapter.in.response.PaginatedPatientResponse;
import com.vluepixel.vetmanager.api.patient.core.adapter.in.response.PatientResponse;
import com.vluepixel.vetmanager.api.patient.core.application.port.in.CreatePatientPort;
import com.vluepixel.vetmanager.api.patient.core.application.port.in.DeletePatientPort;
import com.vluepixel.vetmanager.api.patient.core.application.port.in.FindPatientPort;
import com.vluepixel.vetmanager.api.patient.core.application.port.in.UpdatePatientPort;
import com.vluepixel.vetmanager.api.patient.core.domain.enums.PatientGender;
import com.vluepixel.vetmanager.api.patient.core.domain.request.CreatePatientRequest;
import com.vluepixel.vetmanager.api.patient.core.domain.request.UpdatePatientRequest;
import com.vluepixel.vetmanager.api.shared.adapter.in.response.BasicResponse;
import com.vluepixel.vetmanager.api.shared.adapter.in.response.DetailedFailureResponse;
import com.vluepixel.vetmanager.api.shared.adapter.in.response.FailureResponse;
import com.vluepixel.vetmanager.api.shared.application.annotation.RestControllerAdapter;
import com.vluepixel.vetmanager.api.shared.domain.criteria.Criteria;
import com.vluepixel.vetmanager.api.shared.domain.criteria.Order;
import com.vluepixel.vetmanager.api.shared.domain.criteria.OrderType;
import com.vluepixel.vetmanager.api.shared.domain.exception.NotFoundException;
import com.vluepixel.vetmanager.api.shared.domain.exception.ValidationException;
import com.vluepixel.vetmanager.api.shared.domain.validation.ValidationRequest;
import com.vluepixel.vetmanager.api.shared.domain.validation.impl.InvalidStateValidation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Patient controller.
 */
@Tag(name = "Patient", description = "Patient")
@RestControllerAdapter
@RequestMapping("/patient")
@RequiredArgsConstructor
public final class PatientController {
    private final FindPatientPort findPatientPort;
    private final CreatePatientPort createPatientPort;
    private final UpdatePatientPort updatePatientPort;
    private final DeletePatientPort deletePatientPort;

    /**
     * Get all patient by paginated criteria.
     *
     * @param page                     The page number.
     * @param size                     The page size.
     * @param order                    The order type.
     * @param orderBy                  The order by field.
     * @param id                       The patient id.
     * @param name                     The patient name.
     * @param age                      The patient age.
     * @param gender                   The patient gender.
     * @param deceased                 The patient deceased status.
     * @param raceId                   The race id.
     * @param raceName                 The race name.
     * @param speciesId                The species id.
     * @param speciesName              The species name.
     * @param clientId                 The client id.
     * @param clientFirstName          The client first name.
     * @param clientLastName           The client last name.
     * @param clientIdentification     The client identification.
     * @param clientIdentificationType The client identification type.
     * @param clientAddress            The client address.
     * @param clientPhone              The client phone.
     * @param clientEmail              The client email.
     * @return paginated response with the patients found
     * @throws ValidationException If the request parameters are invalid.
     */
    @Operation(summary = "Get all patient by paginated criteria", responses = {
            @ApiResponse(responseCode = "200", description = "Patients found"),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @GetMapping
    public ResponseEntity<PaginatedPatientResponse> getByPaginatedCriteria(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) OrderType order,
            @RequestParam(required = false, name = "order_by") String orderBy,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) PatientGender gender,
            @RequestParam(required = false) Boolean deceased,
            @RequestParam(required = false, name = "race_id") String raceId,
            @RequestParam(required = false, name = "race_name") String raceName,
            @RequestParam(required = false, name = "species_id") String speciesId,
            @RequestParam(required = false, name = "species_name") String speciesName,
            @RequestParam(required = false, name = "owner_id") String ownerId,
            @RequestParam(required = false, name = "owner_first_name") String ownerFirstName,
            @RequestParam(required = false, name = "owner_last_name") String ownerLastName,
            @RequestParam(required = false, name = "owner_identification") String ownerIdentification,
            @RequestParam(required = false, name = "owner_identification_type") String ownerIdentificationType,
            @RequestParam(required = false, name = "owner_address") String ownerAddress,
            @RequestParam(required = false, name = "owner_phone") String ownerPhone,
            @RequestParam(required = false, name = "owner_email") String ownerEmail)
            throws ValidationException {
        return okPaginated(
                findPatientPort::findPaginatedBy,
                page,
                size,
                Order.of(order, orderBy),
                Criteria.of(
                        like("id", id),
                        like("name", name),
                        like("age", age),
                        like("gender", gender),
                        like("deceased", deceased),
                        like("race.id", raceId),
                        like("race.name", raceName),
                        like("race.species.id", speciesId),
                        like("race.species.name", speciesName),
                        like("owner.id", ownerId),
                        like("owner.firstName", ownerFirstName),
                        like("owner.lastName", ownerLastName),
                        like("owner.identification", ownerIdentification),
                        like("owner.identificationType", ownerIdentificationType),
                        like("owner.address", ownerAddress),
                        like("owner.phone", ownerPhone),
                        like("owner.email", ownerEmail)),
                "Pacientes encontrados exitosamente",
                InvalidStateValidation.of(
                        id != null && id < 1,
                        "query.id",
                        "El id debe ser mayor a 0"));
    }

    /**
     * Get a patient by id.
     *
     * @param id The patient id.
     * @return response with the patient found
     * @throws ValidationException If the id is less than 1.
     */
    @Operation(summary = "Get a patient by id", responses = {
            @ApiResponse(responseCode = "200", description = "Patient found"),
            @ApiResponse(responseCode = "404", description = "Patient not found", content = @Content(schema = @Schema(implementation = FailureResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getById(@PathVariable Long id)
            throws ValidationException, NotFoundException {
        return ok(() -> findPatientPort.findById(id),
                "Paciente encontrado exitosamente",
                InvalidStateValidation.of(
                        id < 1,
                        "query.id",
                        "El id debe ser mayor a 0"));
    }

    /**
     * Create a patient.
     *
     * @param request The create patient request.
     * @return response with the patient created
     * @throws ValidationException If the request is invalid.
     */
    @Operation(summary = "Create a patient", responses = {
            @ApiResponse(responseCode = "200", description = "Patient created"),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @PostMapping
    public ResponseEntity<PatientResponse> create(@RequestBody CreatePatientRequest request)
            throws ValidationException {
        return ok(() -> createPatientPort.create(request),
                "Paciente creado exitosamente",
                ValidationRequest.of(request));
    }

    /**
     * Update a patient.
     *
     * @param request The update patient request.
     * @return response with the patient updated
     * @throws ValidationException If the request is invalid.
     */
    @Operation(summary = "Update a patient", responses = {
            @ApiResponse(responseCode = "200", description = "Patient updated"),
            @ApiResponse(responseCode = "404", description = "Patient not found", content = @Content(schema = @Schema(implementation = FailureResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @PutMapping
    public ResponseEntity<PatientResponse> update(@RequestBody UpdatePatientRequest request)
            throws ValidationException, NotFoundException {
        return ok(() -> updatePatientPort.update(request),
                "Paciente eliminado exitosamente",
                ValidationRequest.of(request));
    }

    /**
     * Delete a patient.
     *
     * @param id The patient id.
     * @return response with an ok message
     * @throws ValidationException If the id is less than 1.
     */
    @Operation(summary = "Delete a patient")
    @DeleteMapping("/{id}")
    public ResponseEntity<BasicResponse> delete(@PathVariable Long id)
            throws ValidationException, NotFoundException {
        return ok(() -> deletePatientPort.deleteById(id),
                "Paciente eliminado exitosamente",
                InvalidStateValidation.of(
                        id < 1,
                        "query.id",
                        "El id debe ser mayor a 0"));
    }
}
