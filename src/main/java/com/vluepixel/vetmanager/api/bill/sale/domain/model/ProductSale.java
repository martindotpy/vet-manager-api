package com.vluepixel.vetmanager.api.bill.sale.domain.model;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;

import com.vluepixel.vetmanager.api.product.core.domain.model.Product;
import com.vluepixel.vetmanager.api.user.core.domain.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Product sale.
 */
@Entity
@Audited
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public final class ProductSale extends Sale {
    @NotNull
    @Max(value = 999)
    @Column(columnDefinition = "smallint unsigned")
    private Integer quantity;

    @NotNull
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_product_sale_product"))
    private Product product;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_product_sale_seller"))
    @CreatedBy
    private User seller;
}
