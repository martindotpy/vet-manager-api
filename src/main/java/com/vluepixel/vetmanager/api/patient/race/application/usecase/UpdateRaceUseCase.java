package com.vluepixel.vetmanager.api.patient.race.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.patient.race.application.dto.RaceDto;
import com.vluepixel.vetmanager.api.patient.race.application.mapper.RaceMapper;
import com.vluepixel.vetmanager.api.patient.race.application.port.in.UpdateRacePort;
import com.vluepixel.vetmanager.api.patient.race.domain.model.Race;
import com.vluepixel.vetmanager.api.patient.race.domain.repository.RaceRepository;
import com.vluepixel.vetmanager.api.patient.race.domain.request.UpdateRaceRequest;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Update race use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class UpdateRaceUseCase implements UpdateRacePort {
    private final RaceRepository raceRepository;
    private final RaceMapper raceMapper;

    @Override
    public RaceDto update(UpdateRaceRequest request) {
        MDC.put("operationId", "Race id " + request.getId());
        log.info("Updating race");

        Race updatedRace = raceMapper.fromRequest(request).build();
        updatedRace = raceRepository.save(updatedRace);

        log.info("Race updated");

        return raceMapper.toDto(updatedRace);
    }
}
