package com.vluepixel.vetmanager.api.product.core.domain.repository;

import com.vluepixel.vetmanager.api.product.core.domain.model.Product;
import com.vluepixel.vetmanager.api.shared.domain.repository.CriteriaRepository;

/**
 * Product repository.
 */
public interface ProductRepository extends CriteriaRepository<Product, Integer> {
}
