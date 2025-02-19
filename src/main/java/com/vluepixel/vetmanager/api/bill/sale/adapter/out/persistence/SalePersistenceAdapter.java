package com.vluepixel.vetmanager.api.bill.sale.adapter.out.persistence;

import com.vluepixel.vetmanager.api.bill.sale.adapter.out.persistence.repository.SaleSpringRepository;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.Sale;
import com.vluepixel.vetmanager.api.bill.sale.domain.repository.SaleRepository;
import com.vluepixel.vetmanager.api.shared.adapter.out.persistence.CriteriaEntityPersistenceAdapter;
import com.vluepixel.vetmanager.api.shared.application.annotation.PersistenceAdapter;

/**
 * Persistence adapter for {@link Sale}.
 */
@PersistenceAdapter
public class SalePersistenceAdapter
        extends CriteriaEntityPersistenceAdapter<Sale, Integer, SaleSpringRepository>
        implements SaleRepository {
    public SalePersistenceAdapter(SaleSpringRepository repository) {
        super(repository);
    }
}
