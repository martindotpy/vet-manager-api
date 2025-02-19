package com.vluepixel.vetmanager.api.product.core.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

import com.vluepixel.vetmanager.api.product.category.domain.model.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Product.
 */
@Entity
@Audited
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public final class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned")
    private Long id;

    @Size(max = 125)
    @NotBlank
    @Column(columnDefinition = "varchar(125)")
    private String name;
    @NotNull
    @Positive
    @DecimalMax(value = "9999.99")
    @Column(columnDefinition = "decimal(6, 2)")
    private BigDecimal price;
    @NotBlank
    @Column(columnDefinition = "text")
    private String description;
    @NotNull
    @Max(value = 999)
    @Column(columnDefinition = "smallint unsigned")
    private Integer quantity;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Category> categories;
}
