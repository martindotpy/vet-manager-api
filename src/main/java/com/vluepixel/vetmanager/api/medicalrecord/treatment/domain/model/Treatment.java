package com.vluepixel.vetmanager.api.medicalrecord.treatment.domain.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.envers.Audited;

import com.vluepixel.vetmanager.api.medicalrecord.core.domain.model.MedicalRecord;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Treatment.
 */
@Entity
@Audited
@SQLDelete(sql = "UPDATE treatment SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public final class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned")
    private Long id;

    @NotBlank
    @Column(columnDefinition = "text")
    private String description;
    @NotNull
    @Column(name = "`order`", columnDefinition = "tinyint unsigned")
    private Integer order;

    @NotNull
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_treatment_medical_record"))
    private MedicalRecord medicalRecord;

    @Builder.Default
    private boolean deleted = false;
}
