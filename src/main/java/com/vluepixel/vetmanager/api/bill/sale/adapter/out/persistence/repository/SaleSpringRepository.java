package com.vluepixel.vetmanager.api.bill.sale.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vluepixel.vetmanager.api.bill.sale.domain.model.Sale;

/**
 * Spring Data JPA repository for {@link Sale}.
 */
public interface SaleSpringRepository extends JpaRepository<Sale, Integer> {
}
