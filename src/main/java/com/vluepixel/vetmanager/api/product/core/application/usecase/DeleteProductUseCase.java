package com.vluepixel.vetmanager.api.product.core.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.product.core.application.port.in.DeleteProductPort;
import com.vluepixel.vetmanager.api.product.core.domain.repository.ProductRepository;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Delete product use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class DeleteProductUseCase implements DeleteProductPort {
    private final ProductRepository productRepository;

    @Override
    public void deleteById(Long id) {
        MDC.put("operationId", "Product id " + id);
        log.info("Deleting product by id");

        productRepository.deleteById(id);

        log.info("Product deleted");
    }
}
