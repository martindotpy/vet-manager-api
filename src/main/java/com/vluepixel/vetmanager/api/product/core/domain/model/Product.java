package com.vluepixel.vetmanager.api.product.core.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

import com.vluepixel.vetmanager.api.product.category.domain.model.Category;
import com.vluepixel.vetmanager.api.shared.domain.exception.CannotReduceProductQuantityexception;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
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
    @DecimalMax(value = "999.99")
    @Column(columnDefinition = "decimal(5, 2)")
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
    @JoinTable(name = "product_categories", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"), foreignKey = @ForeignKey(name = "fk_product_categories_product"), inverseForeignKey = @ForeignKey(name = "fk_product_categories_category"))
    private List<Category> categories;

    /**
     * Reduce product quantity.
     *
     * @param quantity the quantity to reduce
     * @throws CannotReduceProductQuantityexception if the quantity is less than 0.
     */
    public void reduceQuantity(Integer quantity) {
        if (this.quantity - quantity < 0) {
            throw new CannotReduceProductQuantityexception();
        }

        this.quantity -= quantity;
    }
}
