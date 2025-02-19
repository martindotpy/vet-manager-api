package com.vluepixel.vetmanager.api.bill.sale.adapter.in.controller;

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

import com.vluepixel.vetmanager.api.bill.sale.adapter.in.response.PaginatedSaleResponse;
import com.vluepixel.vetmanager.api.bill.sale.adapter.in.response.SaleResponse;
import com.vluepixel.vetmanager.api.bill.sale.application.port.in.CreateSalePort;
import com.vluepixel.vetmanager.api.bill.sale.application.port.in.DeleteSalePort;
import com.vluepixel.vetmanager.api.bill.sale.application.port.in.FindSalePort;
import com.vluepixel.vetmanager.api.bill.sale.application.port.in.UpdateSalePort;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.CreateSaleRequest;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.UpdateSaleRequest;
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
 * Sale controller.
 */
@Tag(name = "Sale", description = "Sale")
@RestControllerAdapter
@RequestMapping("/sale")
@RequiredArgsConstructor
public final class SaleController {
    private final FindSalePort findSalePort;
    private final CreateSalePort createSalePort;
    private final UpdateSalePort updateSalePort;
    private final DeleteSalePort deleteSalePort;

    /**
     * Get all sale by paginated criteria.
     *
     * @param page    The page number.
     * @param size    The page size.
     * @param order   The order.
     * @param orderBy The order by field.
     * @param id      The sale id.
     * @param name    The sale name.
     * @return paginated response with the sale found
     * @throws ValidationException If the page is less than 1, the id is less than
     *                             1, the size is less than 1, the order is defined
     *                             and the order_by is not defined.
     */
    @Operation(summary = "Get all sales by paginated criteria", responses = {
            @ApiResponse(responseCode = "200", description = "Sale found"),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @GetMapping
    public ResponseEntity<PaginatedSaleResponse> getByPaginatedCriteria(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) OrderType order,
            @RequestParam(required = false, name = "order_by") String orderBy,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name)
            throws ValidationException {
        return okPaginated(
                findSalePort::findPaginatedBy,
                page,
                size,
                Order.of(order, orderBy),
                Criteria.of(
                        like("id", id),
                        like("name", name)),
                "Categorías encontradas",
                InvalidStateValidation.of(
                        id != null && id < 1,
                        "query.id",
                        "El id debe ser mayor a 0"));
    }

    /**
     * Get a sale by id.
     *
     * @param id The sale id.
     * @return response with the sale found
     * @throws ValidationException If the id is less than 1.
     * @throws NotFoundException   If the sale is not found.
     */
    @Operation(summary = "Get a sale by id", responses = {
            @ApiResponse(responseCode = "200", description = "Sale found"),
            @ApiResponse(responseCode = "404", description = "Sale not found", content = @Content(schema = @Schema(implementation = FailureResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<SaleResponse> getById(@PathVariable Integer id)
            throws ValidationException, NotFoundException {
        return ok(() -> findSalePort.findById(id),
                "Categoría encontrada",
                InvalidStateValidation.of(
                        id < 1,
                        "query.id",
                        "El id debe ser mayor a 0"));
    }

    /**
     * Create a sale.
     *
     * @param request The create sale request.
     * @return response with the created sale
     * @throws ValidationException If the request is invalid.
     */
    @Operation(summary = "Create a sale", responses = {
            @ApiResponse(responseCode = "200", description = "Sale created"),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @PostMapping
    public ResponseEntity<SaleResponse> create(@RequestBody CreateSaleRequest request)
            throws ValidationException {
        return ok(() -> createSalePort.create(request),
                "Categoría creada exitosamente",
                ValidationRequest.of(request));
    }

    /**
     * Update a sale.
     *
     * @param request The update sale request.
     * @return response with the updated sale
     * @throws ValidationException If the request is invalid.
     * @throws NotFoundException   If the sale is not found.
     */
    @Operation(summary = "Update a sale", responses = {
            @ApiResponse(responseCode = "200", description = "Sale updated"),
            @ApiResponse(responseCode = "404", description = "Sale not found", content = @Content(schema = @Schema(implementation = FailureResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @PutMapping
    public ResponseEntity<SaleResponse> update(@RequestBody UpdateSaleRequest request)
            throws ValidationException, NotFoundException {
        return ok(() -> updateSalePort.update(request),
                "Cita actualizada exitosamente",
                ValidationRequest.of(request));
    }

    /**
     * Delete a sale.
     *
     * @param id The sale id.
     * @return response with an ok message
     * @throws ValidationException If the id is less than 1.
     */
    @Operation(summary = "Delete a sale", responses = {
            @ApiResponse(responseCode = "200", description = "Sale deleted"),
            @ApiResponse(responseCode = "404", description = "Sale not found", content = @Content(schema = @Schema(implementation = FailureResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<BasicResponse> delete(@PathVariable Integer id)
            throws ValidationException, NotFoundException {
        return ok(() -> deleteSalePort.deleteById(id),
                "Cita eliminada exitosamente",
                InvalidStateValidation.of(
                        id < 1,
                        "query.id",
                        "El id debe ser mayor a 0"));
    }
}
