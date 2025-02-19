package com.vluepixel.vetmanager.api.product.core.adapter.in.controller;

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

import com.vluepixel.vetmanager.api.product.core.adapter.in.response.PaginatedProductResponse;
import com.vluepixel.vetmanager.api.product.core.adapter.in.response.ProductResponse;
import com.vluepixel.vetmanager.api.product.core.application.port.in.CreateProductPort;
import com.vluepixel.vetmanager.api.product.core.application.port.in.DeleteProductPort;
import com.vluepixel.vetmanager.api.product.core.application.port.in.FindProductPort;
import com.vluepixel.vetmanager.api.product.core.application.port.in.UpdateProductPort;
import com.vluepixel.vetmanager.api.product.core.domain.request.CreateProductRequest;
import com.vluepixel.vetmanager.api.product.core.domain.request.UpdateProductRequest;
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
 * Product controller.
 */
@Tag(name = "Product", description = "Product")
@RestControllerAdapter
@RequestMapping("/product")
@RequiredArgsConstructor
public final class ProductController {
    private final FindProductPort findProductPort;
    private final CreateProductPort createProductPort;
    private final UpdateProductPort updateProductPort;
    private final DeleteProductPort deleteProductPort;

    /**
     * Get all product by paginated criteria.
     *
     * @param page    The page number.
     * @param size    The page size.
     * @param order   The order.
     * @param orderBy The order by field.
     * @param id      The product id.
     * @param name    The product name.
     * @return paginated response with the product found
     * @throws ValidationException If the page is less than 1, the id is less than
     *                             1, the size is less than 1, the order is defined
     *                             and the order_by is not defined.
     */
    @Operation(summary = "Get all product by paginated criteria", responses = {
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @GetMapping
    public ResponseEntity<PaginatedProductResponse> getByPaginatedCriteria(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) OrderType order,
            @RequestParam(required = false, name = "order_by") String orderBy,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name)
            throws ValidationException {
        return okPaginated(
                findProductPort::findPaginatedBy,
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
     * Get a product by id.
     *
     * @param id The product id.
     * @return response with the product found
     * @throws ValidationException If the id is less than 1.
     * @throws NotFoundException   If the product is not found.
     */
    @Operation(summary = "Get a product by id", responses = {
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(schema = @Schema(implementation = FailureResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Integer id)
            throws ValidationException, NotFoundException {
        return ok(() -> findProductPort.findById(id),
                "Categoría encontrada",
                InvalidStateValidation.of(
                        id < 1,
                        "query.id",
                        "El id debe ser mayor a 0"));
    }

    /**
     * Create a product.
     *
     * @param request The create product request.
     * @return response with the created product
     * @throws ValidationException If the request is invalid.
     */
    @Operation(summary = "Create a product", responses = {
            @ApiResponse(responseCode = "200", description = "Product created"),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody CreateProductRequest request)
            throws ValidationException {
        return ok(() -> createProductPort.create(request),
                "Categoría creada exitosamente",
                ValidationRequest.of(request));
    }

    /**
     * Update a product.
     *
     * @param request The update product request.
     * @return response with the updated product
     * @throws ValidationException If the request is invalid.
     * @throws NotFoundException   If the product is not found.
     */
    @Operation(summary = "Update a product", responses = {
            @ApiResponse(responseCode = "200", description = "Product updated"),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(schema = @Schema(implementation = FailureResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @PutMapping
    public ResponseEntity<ProductResponse> update(@RequestBody UpdateProductRequest request)
            throws ValidationException, NotFoundException {
        return ok(() -> updateProductPort.update(request),
                "Cita actualizada exitosamente",
                ValidationRequest.of(request));
    }

    /**
     * Delete a product.
     *
     * @param id The product id.
     * @return response with an ok message
     * @throws ValidationException If the id is less than 1.
     */
    @Operation(summary = "Delete a product", responses = {
            @ApiResponse(responseCode = "200", description = "Product deleted"),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(schema = @Schema(implementation = FailureResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<BasicResponse> delete(@PathVariable Integer id)
            throws ValidationException, NotFoundException {
        return ok(() -> deleteProductPort.deleteById(id),
                "Cita eliminada exitosamente",
                InvalidStateValidation.of(
                        id < 1,
                        "query.id",
                        "El id debe ser mayor a 0"));
    }
}
