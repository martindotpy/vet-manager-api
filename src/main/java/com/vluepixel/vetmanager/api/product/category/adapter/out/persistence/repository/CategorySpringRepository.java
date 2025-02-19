package com.vluepixel.vetmanager.api.product.category.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vluepixel.vetmanager.api.product.category.domain.model.Category;

/**
 * Spring Data JPA repository for {@link Category}.
 */
public interface CategorySpringRepository extends JpaRepository<Category, Integer> {
}
