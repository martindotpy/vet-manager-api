package com.vluepixel.vetmanager.api.bill.core.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.envers.Audited;

import com.vluepixel.vetmanager.api.bill.sale.domain.model.Sale;
import com.vluepixel.vetmanager.api.client.core.domain.model.Client;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Bill.
 */
@Entity
@Audited
@SQLDelete(sql = "UPDATE bill SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public final class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned")
    private Long id;

    @NotNull
    @PositiveOrZero
    @DecimalMax(value = "99999.99")
    @Column(columnDefinition = "decimal(7, 2)")
    private BigDecimal total;
    @NotNull
    @PositiveOrZero
    @Max(100)
    @Column(columnDefinition = "tinyint unsigned")
    private Integer discount;
    @NotNull
    @PositiveOrZero
    @DecimalMax(value = "99999.99")
    @Column(columnDefinition = "decimal(7, 2)")
    private BigDecimal totalPaid;
    @Builder.Default
    private boolean paid = false;
    private LocalDateTime lastPaidAt;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "bill", fetch = FetchType.EAGER)
    private List<Sale> sales;
    @NotNull
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_bill_client"))
    private Client client;

    @Builder.Default
    private boolean deleted = false;
}
