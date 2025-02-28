package com.vluepixel.vetmanager.api.product.core.adapter.out.service;

import org.springframework.stereotype.Service;

import com.vluepixel.vetmanager.api.product.core.application.dto.ProductDto;
import com.vluepixel.vetmanager.api.product.core.application.mapper.ProductMapper;
import com.vluepixel.vetmanager.api.product.core.application.port.out.ExportProductExcelPort;
import com.vluepixel.vetmanager.api.product.core.domain.model.Product;
import com.vluepixel.vetmanager.api.product.core.domain.repository.ProductRepository;
import com.vluepixel.vetmanager.api.shared.adapter.out.service.ExportExcelSubService;

/**
 * Export product excel service.
 */
@Service
public final class ExportProductExcelService extends ExportExcelSubService<Product, ProductDto>
        implements ExportProductExcelPort {
    public ExportProductExcelService(ProductRepository productRepository, ProductMapper productMapper) {
        super(productRepository, productMapper, ProductDto.class);
    }
}
