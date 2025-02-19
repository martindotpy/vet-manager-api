package com.vluepixel.vetmanager.api.product.core.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.product.core.application.dto.ProductDto;
import com.vluepixel.vetmanager.api.product.core.application.mapper.ProductMapper;
import com.vluepixel.vetmanager.api.product.core.application.port.in.CreateProductPort;
import com.vluepixel.vetmanager.api.product.core.domain.model.Product;
import com.vluepixel.vetmanager.api.product.core.domain.repository.ProductRepository;
import com.vluepixel.vetmanager.api.product.core.domain.request.CreateProductRequest;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Create product use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class CreateProductUseCase implements CreateProductPort {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto create(CreateProductRequest request) {
        MDC.put("operationId", "Product name " + request.getName());
        log.info("Creating product");

        Product newProduct = productMapper.fromRequest(request).build();
        newProduct = productRepository.save(newProduct);
        newProduct = productRepository.findById(newProduct.getId()).get();

        log.info("Product created");

        return productMapper.toDto(newProduct);
    }
}
