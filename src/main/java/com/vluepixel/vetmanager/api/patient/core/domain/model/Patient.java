package com.vluepixel.vetmanager.api.patient.core.domain.model;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.envers.Audited;

import com.vluepixel.vetmanager.api.client.core.domain.model.Client;
import com.vluepixel.vetmanager.api.medicalrecord.core.domain.model.MedicalRecord;
import com.vluepixel.vetmanager.api.patient.core.domain.enums.PatientGender;
import com.vluepixel.vetmanager.api.patient.medicalhistory.domain.model.MedicalHistory;
import com.vluepixel.vetmanager.api.patient.race.domain.model.Race;
import com.vluepixel.vetmanager.api.vaccine.core.domain.model.Vaccine;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Patient.
 */
@Entity
@Audited
@SQLDelete(sql = "UPDATE patient SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public final class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned")
    private Long id;

    @Size(max = 50)
    @NotBlank
    @Column(columnDefinition = "varchar(50)")
    private String name;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    @Enumerated(EnumType.STRING)
    private PatientGender gender;
    @Column(columnDefinition = "text")
    private String characteristics;
    @Builder.Default
    private boolean deceased = false;
    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<@NotNull MedicalHistory> medicalHistories;
    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<@NotNull MedicalRecord> medicalRecords;
    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<@NotNull Vaccine> vaccines;

    @NotNull
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_patient_race"))
    private Race race;
    @NotNull
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_patient_owner"))
    private Client owner;

    @Builder.Default
    private boolean deleted = false;

    /**
     * Calculate age.
     *
     * @return the age
     */
    public Integer calculateAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    /**
     * Validate birth date.
     *
     * @return true if the birth date is valid, false otherwise
     */
    @AssertTrue(message = "La fecha de nacimiento no puede ser mayor a la fecha actual")
    public boolean isBirthDate() {
        if (birthDate == null) {
            return true;
        }

        return !birthDate.isAfter(LocalDate.now());
    }
}
