package com.vluepixel.vetmanager.api.bill.sale.domain.model;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;

import com.vluepixel.vetmanager.api.appointment.core.domain.model.Appointment;
import com.vluepixel.vetmanager.api.bill.core.domain.model.Bill;
import com.vluepixel.vetmanager.api.user.core.domain.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Appointment sale.
 */
@Entity
@Audited
@Inheritance
@Getter
@SuperBuilder
public final class AppointmentSale extends Sale {
    @NotNull
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_appointment_sale_appointment"))
    private Appointment appointment;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_appointment_sale_seller"))
    @CreatedBy
    private User seller;
    @NotNull
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_appointment_sale_bill"))
    private Bill bill;
}
