package com.vluepixel.vetmanager.api.product.core.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.product.core.application.dto.ProductDto;
import com.vluepixel.vetmanager.api.product.core.application.mapper.ProductMapper;
import com.vluepixel.vetmanager.api.product.core.application.port.in.UpdateProductPort;
import com.vluepixel.vetmanager.api.product.core.domain.model.Product;
import com.vluepixel.vetmanager.api.product.core.domain.repository.ProductRepository;
import com.vluepixel.vetmanager.api.product.core.domain.request.UpdateProductRequest;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Update product use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class UpdateProductUseCase implements UpdateProductPort {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto update(UpdateProductRequest request) {
        MDC.put("operationId", "Product id " + request.getId());
        log.info("Updating product");

        Product updatedProduct = productMapper.fromRequest(request).build();
        updatedProduct = productRepository.save(updatedProduct);
        updatedProduct = productRepository.findById(updatedProduct.getId()).get();

        log.info("Product updated");

        return productMapper.toDto(updatedProduct);
    }
}
