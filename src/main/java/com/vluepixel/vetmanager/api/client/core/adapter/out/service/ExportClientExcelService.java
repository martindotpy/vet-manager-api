package com.vluepixel.vetmanager.api.client.core.adapter.out.service;

import org.springframework.stereotype.Service;

import com.vluepixel.vetmanager.api.client.core.application.dto.ClientDto;
import com.vluepixel.vetmanager.api.client.core.application.mapper.ClientMapper;
import com.vluepixel.vetmanager.api.client.core.application.port.out.ExportClientExcelPort;
import com.vluepixel.vetmanager.api.client.core.domain.model.Client;
import com.vluepixel.vetmanager.api.client.core.domain.repository.ClientRepository;
import com.vluepixel.vetmanager.api.shared.adapter.out.service.ExportExcelSubService;

/**
 * Export client excel service.
 */
@Service
public final class ExportClientExcelService extends ExportExcelSubService<Client, ClientDto>
        implements ExportClientExcelPort {
    public ExportClientExcelService(ClientRepository clientRepository, ClientMapper clientMapper) {
        super(clientRepository, clientMapper);
    }
}
