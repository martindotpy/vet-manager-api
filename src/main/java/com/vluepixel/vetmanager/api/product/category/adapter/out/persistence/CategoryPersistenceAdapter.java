package com.vluepixel.vetmanager.api.product.category.adapter.out.persistence;

import com.vluepixel.vetmanager.api.product.category.adapter.out.persistence.repository.CategorySpringRepository;
import com.vluepixel.vetmanager.api.product.category.domain.model.Category;
import com.vluepixel.vetmanager.api.product.category.domain.repository.CategoryRepository;
import com.vluepixel.vetmanager.api.shared.adapter.out.persistence.CriteriaEntityPersistenceAdapter;
import com.vluepixel.vetmanager.api.shared.application.annotation.PersistenceAdapter;

/**
 * Persistence adapter for {@link Category}.
 */
@PersistenceAdapter
public class CategoryPersistenceAdapter
        extends CriteriaEntityPersistenceAdapter<Category, Integer, CategorySpringRepository>
        implements CategoryRepository {
    public CategoryPersistenceAdapter(CategorySpringRepository repository) {
        super(repository);
    }
}
