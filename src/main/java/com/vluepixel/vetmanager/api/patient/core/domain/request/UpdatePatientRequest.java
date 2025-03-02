package com.vluepixel.vetmanager.api.patient.core.domain.request;

import java.time.LocalDate;

import com.vluepixel.vetmanager.api.patient.core.domain.enums.PatientGender;
import com.vluepixel.vetmanager.api.shared.domain.payload.Payload;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Update patient request.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class UpdatePatientRequest implements Payload {
    @NotNull(message = "El id es requerido")
    @Positive(message = "El id debe ser mayor a 0")
    private Long id;

    @NotBlank(message = "El nombre es requerido")
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    private String name;
    @NotNull(message = "La fecha de nacimiento es requerida")
    private LocalDate birthDate;
    @NotNull(message = "El género es requerido")
    private PatientGender gender;
    private String characteristics;
    private boolean deceased;

    @NotNull(message = "El id de la raza es requerido")
    @Positive(message = "El id de la raza debe ser mayor a 0")
    private Integer raceId;
    @NotNull(message = "El id del dueño es requerido")
    @Positive(message = "El id del dueño debe ser mayor a 0")
    private Long ownerId;

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
