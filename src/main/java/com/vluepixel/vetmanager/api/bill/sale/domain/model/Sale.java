package com.vluepixel.vetmanager.api.bill.sale.domain.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.CreatedBy;

import com.vluepixel.vetmanager.api.bill.core.domain.model.Bill;
import com.vluepixel.vetmanager.api.user.core.domain.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Sale.
 */
@MappedSuperclass
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned")
    private Long id;

    @NotNull
    @Positive
    @DecimalMax(value = "999999.99")
    @Column(columnDefinition = "decimal(8, 2)")
    private BigDecimal price;
    @NotNull
    @Positive
    @Max(100)
    @Column(columnDefinition = "tinyint unsigned")
    private Integer discount;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_sale_seller"))
    @CreatedBy
    private User seller;
    @NotNull
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_sale_bill"))
    private Bill bill;
}
