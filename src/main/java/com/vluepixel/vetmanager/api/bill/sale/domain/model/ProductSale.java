package com.vluepixel.vetmanager.api.bill.sale.domain.model;

import org.hibernate.envers.Audited;

import com.vluepixel.vetmanager.api.product.core.domain.model.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Product sale.
 */
@Entity
@Audited
@Inheritance
@Getter
@SuperBuilder
public final class ProductSale extends Sale {
    @NotNull
    @Max(value = 999)
    @Column(columnDefinition = "smallint unsigned")
    private Integer quantity;

    @NotNull
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_product_sale_product"))
    private Product product;
}
