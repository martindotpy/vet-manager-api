package com.vluepixel.vetmanager.api.bill.core.adapter.in.controller;

import static com.vluepixel.vetmanager.api.shared.adapter.in.util.ResponseShortcuts.ok;
import static com.vluepixel.vetmanager.api.shared.adapter.in.util.ResponseShortcuts.okPaginated;
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

import com.vluepixel.vetmanager.api.bill.core.adapter.in.response.BillResponse;
import com.vluepixel.vetmanager.api.bill.core.adapter.in.response.PaginatedBillResponse;
import com.vluepixel.vetmanager.api.bill.core.application.port.in.CreateBillPort;
import com.vluepixel.vetmanager.api.bill.core.application.port.in.DeleteBillPort;
import com.vluepixel.vetmanager.api.bill.core.application.port.in.FindBillPort;
import com.vluepixel.vetmanager.api.bill.core.application.port.in.UpdateBillPort;
import com.vluepixel.vetmanager.api.bill.core.application.port.out.ExportBillExcelPort;
import com.vluepixel.vetmanager.api.bill.core.domain.request.CreateBillRequest;
import com.vluepixel.vetmanager.api.bill.core.domain.request.UpdateBillRequest;
import com.vluepixel.vetmanager.api.client.core.domain.enums.IdentificationType;
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
import com.vluepixel.vetmanager.api.shared.domain.validation.ValidationRequest;
import com.vluepixel.vetmanager.api.shared.domain.validation.impl.InvalidStateValidation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Bill controller.
 */
@Tag(name = "Bill", description = "Bill")
@RestControllerAdapter
@RequestMapping("/bill")
@RequiredArgsConstructor
public final class BillController {
    private final FindBillPort findBillPort;
    private final ExportBillExcelPort exportBillExcelPort;
    private final CreateBillPort createBillPort;
    private final UpdateBillPort updateBillPort;
    private final DeleteBillPort deleteBillPort;

    /**
     * Get all bill by paginated criteria.
     *
     * @param page            The page number.
     * @param size            The page size.
     * @param order           The order.
     * @param orderBy         The order by field.
     * @param id              The bill id.
     * @param clientFirstName The bill name.
     * @return paginated response with the bill found
     * @throws ValidationException If the page is less than 1, the id is less than
     *                             1, the size is less than 1, the order is defined
     *                             and the order_by is not defined.
     */
    @Operation(summary = "Get all bill by paginated criteria", responses = {
            @ApiResponse(responseCode = "200", description = "Bill found"),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @GetMapping
    public ResponseEntity<PaginatedBillResponse> getByPaginatedCriteria(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) OrderType order,
            @RequestParam(required = false, name = "order_by") String orderBy,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false, name = "client_id") Long clientId,
            @RequestParam(required = false, name = "client_first_name") String clientFirstName,
            @RequestParam(required = false, name = "client_last_name") String clientLastName,
            @RequestParam(required = false, name = "client_identification") String identification,
            @RequestParam(required = false, name = "client_identification_type") IdentificationType clientIdentificationType,
            @RequestParam(required = false, name = "client_email") String clientEmail,
            @RequestParam(required = false, name = "client_phone") String clientPhone)
            throws ValidationException {
        return okPaginated(
                findBillPort::findPaginatedBy,
                page,
                size,
                Order.of(order, orderBy),
                Criteria.of(
                        like("id", id),
                        like("client.id", clientId),
                        like("client.firstName", clientFirstName),
                        like("client.lastName", clientLastName),
                        like("client.identification", identification),
                        like("client.identificationType", clientIdentificationType),
                        like("client.emails", clientEmail),
                        like("client.phones", clientPhone)),
                "Cuentas encontradas",
                InvalidStateValidation.of(
                        id != null && id < 1,
                        "query.id",
                        "El id debe ser mayor a 0"),
                InvalidStateValidation.of(
                        clientId != null && clientId < 1,
                        "query.client_id",
                        "El id del cliente debe ser mayor a 0"));
    }

    /**
     * Get a bill by id.
     *
     * @param id The bill id.
     * @return response with the bill found
     * @throws ValidationException If the id is less than 1.
     * @throws NotFoundException   If the bill is not found.
     */
    @Operation(summary = "Get a bill by id", responses = {
            @ApiResponse(responseCode = "200", description = "Bill found"),
            @ApiResponse(responseCode = "404", description = "Bill not found", content = @Content(schema = @Schema(implementation = FailureResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<BillResponse> getById(@PathVariable Long id)
            throws ValidationException, NotFoundException {
        return ok(() -> findBillPort.findById(id),
                "Cuenta encontrada",
                InvalidStateValidation.of(
                        id < 1,
                        "query.id",
                        "El id debe ser mayor a 0"));
    }

    /**
     * Export bills to excel.
     *
     * @return response with the excel file
     * @throws InternalServerErrorException if the export fails.
     */
    @Operation(summary = "Export bills to excel", description = "Export all bills to excel", responses = {
            @ApiResponse(responseCode = "200", description = "Bills exported successfully"),
            @ApiResponse(responseCode = "500", description = "Error exporting bills to excel", content = @Content(schema = @Schema(implementation = FailureResponse.class)))
    })
    @GetMapping("/excel")
    public ResponseEntity<byte[]> exportExcel()
            throws InternalServerErrorException {
        return ok(exportBillExcelPort::export,
                "Cuentas " + LocalDateTime.now().format(ExportBillExcelPort.DATE_TIME_FORMATTER) + ".xlsx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                "No se ha podido exportar las cuentas a excel correctamente");
    }

    /**
     * Create a bill.
     *
     * @param request The create bill request.
     * @return response with the created bill
     * @throws ValidationException If the request is invalid.
     */
    @Operation(summary = "Create a bill", responses = {
            @ApiResponse(responseCode = "200", description = "Bill created"),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @PostMapping
    public ResponseEntity<BillResponse> create(@RequestBody CreateBillRequest request)
            throws ValidationException {
        return ok(() -> createBillPort.create(request),
                "Cuenta creada exitosamente",
                ValidationRequest.of(request));
    }

    /**
     * Update a bill.
     *
     * @param request The update bill request.
     * @return response with the updated bill
     * @throws ValidationException If the request is invalid.
     * @throws NotFoundException   If the bill is not found.
     */
    @Operation(summary = "Update a bill", responses = {
            @ApiResponse(responseCode = "200", description = "Bill updated"),
            @ApiResponse(responseCode = "404", description = "Bill not found", content = @Content(schema = @Schema(implementation = FailureResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @PutMapping
    public ResponseEntity<BillResponse> update(@RequestBody UpdateBillRequest request)
            throws ValidationException, NotFoundException {
        return ok(() -> updateBillPort.update(request),
                "Cuenta actualizada exitosamente",
                ValidationRequest.of(request));
    }

    /**
     * Delete a bill.
     *
     * @param id The bill id.
     * @return response with an ok message
     * @throws ValidationException If the id is less than 1.
     */
    @Operation(summary = "Delete a bill", responses = {
            @ApiResponse(responseCode = "200", description = "Bill deleted"),
            @ApiResponse(responseCode = "404", description = "Bill not found", content = @Content(schema = @Schema(implementation = FailureResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<BasicResponse> delete(@PathVariable Long id)
            throws ValidationException, NotFoundException {
        return ok(() -> deleteBillPort.deleteById(id),
                "Cuenta eliminada exitosamente",
                InvalidStateValidation.of(
                        id < 1,
                        "query.id",
                        "El id debe ser mayor a 0"));
    }
}
