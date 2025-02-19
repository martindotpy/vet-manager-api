package com.vluepixel.vetmanager.api.product.category.adapter.in.controller;

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

import com.vluepixel.vetmanager.api.product.category.adapter.in.response.CategoryResponse;
import com.vluepixel.vetmanager.api.product.category.adapter.in.response.PaginatedCategoryResponse;
import com.vluepixel.vetmanager.api.product.category.application.port.in.CreateCategoryPort;
import com.vluepixel.vetmanager.api.product.category.application.port.in.DeleteCategoryPort;
import com.vluepixel.vetmanager.api.product.category.application.port.in.FindCategoryPort;
import com.vluepixel.vetmanager.api.product.category.application.port.in.UpdateCategoryPort;
import com.vluepixel.vetmanager.api.product.category.domain.request.CreateCategoryRequest;
import com.vluepixel.vetmanager.api.product.category.domain.request.UpdateCategoryRequest;
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
 * Category controller.
 */
@Tag(name = "Category", description = "Category")
@RestControllerAdapter
@RequestMapping("/category")
@RequiredArgsConstructor
public final class CategoryController {
    private final FindCategoryPort findCategoryPort;
    private final CreateCategoryPort createCategoryPort;
    private final UpdateCategoryPort updateCategoryPort;
    private final DeleteCategoryPort deleteCategoryPort;

    /**
     * Get all category by paginated criteria.
     *
     * @param page    The page number.
     * @param size    The page size.
     * @param order   The order.
     * @param orderBy The order by field.
     * @param id      The category id.
     * @param name    The category name.
     * @return paginated response with the category found
     * @throws ValidationException If the page is less than 1, the id is less than
     *                             1, the size is less than 1, the order is defined
     *                             and the order_by is not defined.
     */
    @Operation(summary = "Get all category by paginated criteria", responses = {
            @ApiResponse(responseCode = "200", description = "Category found"),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @GetMapping
    public ResponseEntity<PaginatedCategoryResponse> getByPaginatedCriteria(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) OrderType order,
            @RequestParam(required = false, name = "order_by") String orderBy,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name)
            throws ValidationException {
        return okPaginated(
                findCategoryPort::findPaginatedBy,
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
     * Get a category by id.
     *
     * @param id The category id.
     * @return response with the category found
     * @throws ValidationException If the id is less than 1.
     * @throws NotFoundException   If the category is not found.
     */
    @Operation(summary = "Get a category by id", responses = {
            @ApiResponse(responseCode = "200", description = "Category found"),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content(schema = @Schema(implementation = FailureResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Integer id)
            throws ValidationException, NotFoundException {
        return ok(() -> findCategoryPort.findById(id),
                "Categoría encontrada",
                InvalidStateValidation.of(
                        id < 1,
                        "query.id",
                        "El id debe ser mayor a 0"));
    }

    /**
     * Create a category.
     *
     * @param request The create category request.
     * @return response with the created category
     * @throws ValidationException If the request is invalid.
     */
    @Operation(summary = "Create a category", responses = {
            @ApiResponse(responseCode = "200", description = "Category created"),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CreateCategoryRequest request)
            throws ValidationException {
        return ok(() -> createCategoryPort.create(request),
                "Categoría creada exitosamente",
                ValidationRequest.of(request));
    }

    /**
     * Update a category.
     *
     * @param request The update category request.
     * @return response with the updated category
     * @throws ValidationException If the request is invalid.
     * @throws NotFoundException   If the category is not found.
     */
    @Operation(summary = "Update a category", responses = {
            @ApiResponse(responseCode = "200", description = "Category updated"),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content(schema = @Schema(implementation = FailureResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @PutMapping
    public ResponseEntity<CategoryResponse> update(@RequestBody UpdateCategoryRequest request)
            throws ValidationException, NotFoundException {
        return ok(() -> updateCategoryPort.update(request),
                "Cita actualizada exitosamente",
                ValidationRequest.of(request));
    }

    /**
     * Delete a category.
     *
     * @param id The category id.
     * @return response with an ok message
     * @throws ValidationException If the id is less than 1.
     */
    @Operation(summary = "Delete a category", responses = {
            @ApiResponse(responseCode = "200", description = "Category deleted"),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content(schema = @Schema(implementation = FailureResponse.class))),
            @ApiResponse(responseCode = "422", description = "Validation error", content = @Content(schema = @Schema(implementation = DetailedFailureResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<BasicResponse> delete(@PathVariable Integer id)
            throws ValidationException, NotFoundException {
        return ok(() -> deleteCategoryPort.deleteById(id),
                "Cita eliminada exitosamente",
                InvalidStateValidation.of(
                        id < 1,
                        "query.id",
                        "El id debe ser mayor a 0"));
    }
}
