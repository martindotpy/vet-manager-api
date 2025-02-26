package com.vluepixel.vetmanager.api.bill.sale.domain.model;

import java.math.BigDecimal;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.vluepixel.vetmanager.api.bill.core.domain.model.Bill;
import com.vluepixel.vetmanager.api.user.core.domain.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
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
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
@Audited
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @NotNull
    @ManyToOne
    @CreatedBy
    private User seller;

    @NotNull
    @ManyToOne
    private Bill bill;
}
