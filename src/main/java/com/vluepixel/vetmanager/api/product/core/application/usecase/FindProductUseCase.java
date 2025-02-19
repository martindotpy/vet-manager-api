package com.vluepixel.vetmanager.api.product.core.application.usecase;

import static com.vluepixel.vetmanager.api.shared.adapter.in.util.AnsiShortcuts.fgBrightBlue;
import static com.vluepixel.vetmanager.api.shared.adapter.in.util.AnsiShortcuts.fgBrightGreen;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.product.core.application.dto.ProductDto;
import com.vluepixel.vetmanager.api.product.core.application.mapper.ProductMapper;
import com.vluepixel.vetmanager.api.product.core.application.port.in.FindProductPort;
import com.vluepixel.vetmanager.api.product.core.domain.model.Product;
import com.vluepixel.vetmanager.api.product.core.domain.repository.ProductRepository;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;
import com.vluepixel.vetmanager.api.shared.domain.criteria.PaginatedCriteria;
import com.vluepixel.vetmanager.api.shared.domain.exception.NotFoundException;
import com.vluepixel.vetmanager.api.shared.domain.query.Paginated;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Find product use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public final class FindProductUseCase implements FindProductPort {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Paginated<ProductDto> findPaginatedBy(PaginatedCriteria criteria) {
        MDC.put("operationId", "Product by criteria: " + fgBrightBlue(criteria.hashCode()));
        log.info("Finding product by {}",
                fgBrightBlue(criteria));

        Paginated<Product> paginatedProduct = productRepository.findPaginatedBy(criteria);

        log.info("{} product found",
                fgBrightGreen(paginatedProduct.getContent().size()));

        return paginatedProduct.map(productMapper::toDto);
    }

    @Override
    public ProductDto findById(Long id) {
        MDC.put("operationId", "Product id " + id);
        log.info("Finding product by id");

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Product.class, id));

        log.info("Product found");

        return productMapper.toDto(product);
    }
}
