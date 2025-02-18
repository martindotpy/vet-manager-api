package com.vluepixel.vetmanager.api.client.core.application.usecase;

import org.slf4j.MDC;

import com.vluepixel.vetmanager.api.client.core.application.dto.ClientDto;
import com.vluepixel.vetmanager.api.client.core.application.mapper.ClientMapper;
import com.vluepixel.vetmanager.api.client.core.application.port.in.UpdateClientPort;
import com.vluepixel.vetmanager.api.client.core.domain.model.Client;
import com.vluepixel.vetmanager.api.client.core.domain.repository.ClientRepository;
import com.vluepixel.vetmanager.api.client.core.domain.request.UpdateClientRequest;
import com.vluepixel.vetmanager.api.shared.application.annotation.UseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Update client use case.
 */
@Slf4j
@UseCase
@RequiredArgsConstructor
public class UpdateClientUseCase implements UpdateClientPort {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientDto update(UpdateClientRequest request) {
        MDC.put("operationId", "Client id " + request.getId());
        log.info("Updating client");

        Client updatedClient = clientMapper.fromRequest(request).build();
        updatedClient = clientRepository.save(updatedClient);

        log.info("Client updated");

        return clientMapper.toDto(updatedClient);
    }
}
