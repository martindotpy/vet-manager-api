package com.vluepixel.vetmanager.api.bill.core.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vluepixel.vetmanager.api.bill.core.domain.model.Bill;

/**
 * Spring Data JPA repository for {@link Bill}.
 */
public interface BillSpringRepository extends JpaRepository<Bill, Long> {
}
