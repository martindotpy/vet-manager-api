package com.vluepixel.vetmanager.api.product.core.adapter.out.persistence;

import com.vluepixel.vetmanager.api.product.core.adapter.out.persistence.repository.ProductSpringRepository;
import com.vluepixel.vetmanager.api.product.core.domain.model.Product;
import com.vluepixel.vetmanager.api.product.core.domain.repository.ProductRepository;
import com.vluepixel.vetmanager.api.shared.adapter.out.persistence.CriteriaEntityPersistenceAdapter;
import com.vluepixel.vetmanager.api.shared.application.annotation.PersistenceAdapter;

/**
 * Persistence adapter for {@link Product}.
 */
@PersistenceAdapter
public class ProductPersistenceAdapter
        extends CriteriaEntityPersistenceAdapter<Product, Integer, ProductSpringRepository>
        implements ProductRepository {
    public ProductPersistenceAdapter(ProductSpringRepository repository) {
        super(repository);
    }
}
