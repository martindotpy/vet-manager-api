package com.vluepixel.vetmanager.api.bill.core.adapter.out.persistence;

import com.vluepixel.vetmanager.api.bill.core.adapter.out.persistence.repository.BillSpringRepository;
import com.vluepixel.vetmanager.api.bill.core.domain.model.Bill;
import com.vluepixel.vetmanager.api.bill.core.domain.repository.BillRepository;
import com.vluepixel.vetmanager.api.shared.adapter.out.persistence.CriteriaEntityPersistenceAdapter;
import com.vluepixel.vetmanager.api.shared.application.annotation.PersistenceAdapter;

/**
 * Persistence adapter for {@link Bill}.
 */
@PersistenceAdapter
public class BillPersistenceAdapter
        extends CriteriaEntityPersistenceAdapter<Bill, Long, BillSpringRepository>
        implements BillRepository {
    public BillPersistenceAdapter(BillSpringRepository repository) {
        super(repository);
    }
}
