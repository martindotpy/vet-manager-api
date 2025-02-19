package com.vluepixel.vetmanager.api.bill.sale.domain.model;

import org.hibernate.envers.Audited;

import com.vluepixel.vetmanager.api.medicalrecord.treatment.domain.model.Treatment;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Treatment sale.
 */
@Entity
@Audited
@Inheritance
@Getter
@SuperBuilder
public final class TreatmentSale extends Sale {
    @NotNull
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_treatment_sale_treatment"))
    private Treatment treatment;
}
