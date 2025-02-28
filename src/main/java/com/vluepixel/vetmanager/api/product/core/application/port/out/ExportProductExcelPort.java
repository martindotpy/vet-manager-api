package com.vluepixel.vetmanager.api.product.core.application.port.out;

import com.vluepixel.vetmanager.api.product.core.application.dto.ProductDto;
import com.vluepixel.vetmanager.api.product.core.domain.model.Product;
import com.vluepixel.vetmanager.api.shared.application.port.out.ExportExcelPort;

/**
 * Export product excel port.
 */
public interface ExportProductExcelPort extends ExportExcelPort<Product, ProductDto> {
}
