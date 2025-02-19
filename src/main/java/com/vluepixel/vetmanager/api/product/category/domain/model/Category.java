package com.vluepixel.vetmanager.api.product.category.domain.model;

import org.hibernate.envers.Audited;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Category.
 */
@Entity
@Audited
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public final class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "tinyint unsigned")
    private Integer id;

    @Size(max = 12)
    @NotBlank
    @Column(columnDefinition = "varchar(12)")
    private String name;
}
