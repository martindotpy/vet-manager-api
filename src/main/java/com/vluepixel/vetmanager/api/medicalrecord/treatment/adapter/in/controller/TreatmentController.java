package com.vluepixel.vetmanager.api.medicalrecord.treatment.adapter.in.controller;

import static com.vluepixel.vetmanager.api.shared.adapter.in.util.ResponseShortcuts.ok;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.vluepixel.vetmanager.api.medicalrecord.treatment.adapter.in.response.TreatmentResponse;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.adapter.in.response.TreatmentsResponse;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.application.port.in.CreateTreatmentPort;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.application.port.in.DeleteTreatmentPort;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.application.port.in.FindTreatmentPort;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.application.port.in.UpdateTreatmentPort;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.domain.request.CreateTreatmentRequest;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.domain.request.UpdateTreatmentRequest;
import com.vluepixel.vetmanager.api.shared.adapter.in.response.BasicResponse;
import com.vluepixel.vetmanager.api.shared.adapter.in.response.DetailedFailureResponse;
import com.vluepixel.vetmanager.api.shared.adapter.in.response.FailureResponse;
import com.vluepixel.vetmanager.api.shared.application.annotation.RestControllerAdapter;
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
 * Treatment controller.
 */
@Tag(name = "Treatment", description = "Treatment")
@RestControllerAdapter
@RequiredArgsConstructor
public final class TreatmentController {
    private final FindTreatmentPort findTreatmentPort;
    private final CreateTreatmentPort createTreatmentPort;
    private final UpdateTreatmentPort updateTreatmentPort;
    private final DeleteTreatmentPort deleteTreatmentPort;

    /**
     * Get all treatments by patient id and medical record id.
     *
     * @param patientId The patient id.
     * @param recordId  The medical record id.
     * @return response with the treatments found
     * @throws ValidationException If the id is less than 1.
     */
    @Operation(summary = "Get all treatment by patient id", responses = {
            @ApiResponse(responseCode = "200", description = "Treatments found"),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @GetMapping("/patient/{patient_id}/record/{record_id}/treatment")
    public ResponseEntity<TreatmentsResponse> getByPatientIdAndMedicalRecordId(
            @PathVariable(name = "patient_id") Long patientId,
            @PathVariable(name = "record_id") Long recordId)
            throws ValidationException {
        return ok(() -> findTreatmentPort.findAllByPatientIdAndMedicalRecordId(patientId, recordId),
                "Tratamientos del paciente encontrados exitosamente",
                InvalidStateValidation.of(
                        patientId < 1,
                        "path.patient_id",
                        "El id del paciente debe ser mayor a 0"),
                InvalidStateValidation.of(
                        recordId < 1,
                        "path.record_id",
                        "El id del historial médico debe ser mayor a 0"));
    }

    /**
     * Create a treatment.
     *
     * @param patientId The patient id.
     * @param recordId  The medical record id.
     * @param request   The create treatment request.
     * @return response with the treatment created
     * @throws ValidationException If the request is invalid.
     * @throws NotFoundException   If the patient or medical record is not found.
     */
    @Operation(summary = "Create a treatment", responses = {
            @ApiResponse(responseCode = "200", description = "Treatment created"),
            @ApiResponse(responseCode = "404", description = "Patient or medical record not found", content = @Content(schema = @Schema(implementation = FailureResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @PostMapping("/patient/{patient_id}/record/{record_id}/treatment")
    public ResponseEntity<TreatmentResponse> create(
            @PathVariable(name = "patient_id") Long patientId,
            @PathVariable(name = "record_id") Long recordId,
            @RequestBody CreateTreatmentRequest request)
            throws ValidationException, NotFoundException {
        return ok(() -> createTreatmentPort.create(request),
                "Tratamiento creado exitosamente",
                InvalidStateValidation.of(
                        patientId < 1,
                        "path.patient_id",
                        "El id del paciente debe ser mayor a 0"),
                InvalidStateValidation.of(
                        recordId < 1,
                        "path.record_id",
                        "El id del historial médico debe ser mayor a 0"),
                InvalidStateValidation.of(
                        !recordId.equals(request.getMedicalRecordId()),
                        "path.record_id",
                        "El id del historial médico no coincide con el id del registro médico"),
                ValidationRequest.of(request));
    }

    /**
     * Update a treatment.
     *
     * @param patientId The patient id.
     * @param recordId  The medical record id.
     * @param request   The update treatment request.
     * @return response with the treatment updated
     * @throws ValidationException If the request is invalid.
     * @throws NotFoundException   If the patient, medical record or treatment is
     *                             not found.
     */
    @Operation(summary = "Update a treatment", responses = {
            @ApiResponse(responseCode = "200", description = "Treatment updated"),
            @ApiResponse(responseCode = "404", description = "Patient, medical record or treatment not found", content = @Content(schema = @Schema(implementation = FailureResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @PutMapping("/patient/{patient_id}/record/{record_id}/treatment")
    public ResponseEntity<TreatmentResponse> update(
            @PathVariable(name = "patient_id") Long patientId,
            @PathVariable(name = "record_id") Long recordId,
            @RequestBody UpdateTreatmentRequest request)
            throws ValidationException, NotFoundException {
        return ok(() -> updateTreatmentPort.update(patientId, recordId, request),
                "Tratamiento actualizado exitosamente",
                InvalidStateValidation.of(
                        patientId < 1,
                        "path.patient_id",
                        "El id del paciente debe ser mayor a 0"),
                ValidationRequest.of(request));
    }

    /**
     * Delete a treatment by patient id.
     *
     * @param patientId The patient id.
     * @param id        The treatment id.
     * @return response with an ok message
     * @throws ValidationException If the id is less than 1.
     * @throws NotFoundException   If the patient, medical record or treatment is
     *                             not found.
     */
    @Operation(summary = "Delete a treatment", responses = {
            @ApiResponse(responseCode = "200", description = "Treatment deleted"),
            @ApiResponse(responseCode = "404", description = "Patient, medical record or treatment not found", content = @Content(schema = @Schema(implementation = FailureResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @DeleteMapping("/patient/{patient_id}/record/{record_id}/treatment/{id}")
    public ResponseEntity<BasicResponse> delete(
            @PathVariable(name = "patient_id") Long patientId,
            @PathVariable(name = "record_id") Long recordId,
            @PathVariable Long id)
            throws ValidationException, NotFoundException {
        return ok(() -> deleteTreatmentPort.deleteByPatientIdAndMedicalRecordIdAndId(patientId, recordId, id),
                "Tratamiento eliminado exitosamente",
                InvalidStateValidation.of(
                        patientId < 1,
                        "path.patient_id",
                        "El id del paciente debe ser mayor a 0"),
                InvalidStateValidation.of(
                        recordId < 1,
                        "path.record_id",
                        "El id del historial médico debe ser mayor a 0"),
                InvalidStateValidation.of(
                        id < 1,
                        "query.id",
                        "El id debe ser mayor a 0"));
    }
}
