package com.vluepixel.vetmanager.api.product.core.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vluepixel.vetmanager.api.product.core.domain.model.Product;

/**
 * Spring Data JPA repository for {@link Product}.
 */
public interface ProductSpringRepository extends JpaRepository<Product, Long> {
}
