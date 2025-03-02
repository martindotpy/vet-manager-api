package com.vluepixel.vetmanager.api.patient.core.adapter.in.controller;

import static com.vluepixel.vetmanager.api.shared.adapter.in.util.ResponseShortcuts.ok;
import static com.vluepixel.vetmanager.api.shared.adapter.in.util.ResponseShortcuts.okPaginated;
import static com.vluepixel.vetmanager.api.shared.domain.criteria.Filter.equal;
import static com.vluepixel.vetmanager.api.shared.domain.criteria.Filter.like;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vluepixel.vetmanager.api.client.core.domain.enums.IdentificationType;
import com.vluepixel.vetmanager.api.patient.core.adapter.in.response.PaginatedPatientResponse;
import com.vluepixel.vetmanager.api.patient.core.adapter.in.response.PatientResponse;
import com.vluepixel.vetmanager.api.patient.core.application.port.in.CreatePatientPort;
import com.vluepixel.vetmanager.api.patient.core.application.port.in.DeletePatientPort;
import com.vluepixel.vetmanager.api.patient.core.application.port.in.FindPatientPort;
import com.vluepixel.vetmanager.api.patient.core.application.port.in.UpdatePatientPort;
import com.vluepixel.vetmanager.api.patient.core.application.port.out.ExportPatientExcelPort;
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
import com.vluepixel.vetmanager.api.shared.domain.exception.InternalServerErrorException;
import com.vluepixel.vetmanager.api.shared.domain.exception.NotFoundException;
import com.vluepixel.vetmanager.api.shared.domain.exception.ValidationException;
import com.vluepixel.vetmanager.api.shared.domain.validation.PayloadValidation;
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
    private final ExportPatientExcelPort exportPatientExcelPort;
    private final CreatePatientPort createPatientPort;
    private final UpdatePatientPort updatePatientPort;
    private final DeletePatientPort deletePatientPort;

    /**
     * Get all patient by paginated criteria.
     *
     * @param page                    The page number.
     * @param size                    The page size.
     * @param order                   The order type.
     * @param orderBy                 The order by field.
     * @param id                      The patient id.
     * @param name                    The patient name.
     * @param gender                  The patient gender.
     * @param deceased                The patient deceased status.
     * @param raceId                  The race id.
     * @param raceName                The race name.
     * @param speciesId               The species id.
     * @param speciesName             The species name.
     * @param ownerId                 The owner id.
     * @param ownerFirstName          The owner first name.
     * @param ownerLastName           The owner last name.
     * @param ownerIdentification     The owner identification.
     * @param ownerIdentificationType The owner identification type.
     * @param ownerAddress            The owner address.
     * @param ownerPhone              The owner phone.
     * @param ownerEmail              The owner email.
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
            @RequestParam(required = false) PatientGender gender,
            @RequestParam(required = false) Boolean deceased,
            @RequestParam(required = false, name = "race_id") Integer raceId,
            @RequestParam(required = false, name = "race_name") String raceName,
            @RequestParam(required = false, name = "species_id") Integer speciesId,
            @RequestParam(required = false, name = "species_name") String speciesName,
            @RequestParam(required = false, name = "owner_id") Long ownerId,
            @RequestParam(required = false, name = "owner_first_name") String ownerFirstName,
            @RequestParam(required = false, name = "owner_last_name") String ownerLastName,
            @RequestParam(required = false, name = "owner_identification") String ownerIdentification,
            @RequestParam(required = false, name = "owner_identification_type") IdentificationType ownerIdentificationType,
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
                        like("gender", gender),
                        equal("deceased", deceased),
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
                        like("owner.phones", ownerPhone),
                        like("owner.emails", ownerEmail)),
                "Pacientes encontrados exitosamente",
                InvalidStateValidation.of(
                        id != null && id < 1,
                        "query.id",
                        "El id debe ser mayor a 0"),
                InvalidStateValidation.of(
                        raceId != null && raceId < 1,
                        "query.race_id",
                        "El id de la raza debe ser mayor a 0"),
                InvalidStateValidation.of(
                        speciesId != null && speciesId < 1,
                        "query.species_id",
                        "El id de la especie debe ser mayor a 0"),
                InvalidStateValidation.of(
                        ownerId != null && ownerId < 1,
                        "query.owner_id",
                        "El id del dueño debe ser mayor a 0"));
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
     * Export patients to excel.
     *
     * @return response with the excel file
     * @throws InternalServerErrorException if the export fails.
     */
    @Operation(summary = "Export patients to excel", description = "Export all patients to excel", responses = {
            @ApiResponse(responseCode = "200", description = "Patients exported successfully"),
            @ApiResponse(responseCode = "500", description = "Error exporting patients to excel", content = @Content(schema = @Schema(implementation = FailureResponse.class)))
    })
    @GetMapping("/excel")
    public ResponseEntity<byte[]> exportExcel()
            throws InternalServerErrorException {
        return ok(exportPatientExcelPort::export,
                "Pacientes " + LocalDateTime.now().format(ExportPatientExcelPort.DATE_TIME_FORMATTER) + ".xlsx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                "No se ha podido exportar los pacientes a excel correctamente");
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
                PayloadValidation.of(request));
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
                "Paciente actualizado exitosamente",
                PayloadValidation.of(request));
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
