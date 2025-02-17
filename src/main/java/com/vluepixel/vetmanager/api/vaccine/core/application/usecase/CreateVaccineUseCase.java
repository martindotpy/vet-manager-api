package com.vluepixel.vetmanager.api.vaccine.core.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;
import com.vluepixel.vetmanager.api.vaccine.core.application.dto.VaccineDto;
import com.vluepixel.vetmanager.api.vaccine.core.application.mapper.VaccineMapper;
import com.vluepixel.vetmanager.api.vaccine.core.application.port.in.CreateVaccinePort;
import com.vluepixel.vetmanager.api.vaccine.core.domain.model.Vaccine;
import com.vluepixel.vetmanager.api.vaccine.core.domain.repository.VaccineRepository;
import com.vluepixel.vetmanager.api.vaccine.core.domain.request.CreateVaccineRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Create vaccine use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class CreateVaccineUseCase implements CreateVaccinePort {
    private final VaccineRepository vaccineRepository;
    private final VaccineMapper vaccineMapper;

    @Override
    public VaccineDto create(CreateVaccineRequest request) {
        MDC.put("operationId", "Vaccine name " + request.getName());
        log.info("Creating vaccine");

        Vaccine newVaccine = vaccineMapper.fromRequest(request).build();
        newVaccine = vaccineRepository.save(newVaccine);

        log.info("Vaccine created");

        return vaccineMapper.toDto(newVaccine);
    }
}
