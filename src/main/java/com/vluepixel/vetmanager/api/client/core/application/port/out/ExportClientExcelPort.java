package com.vluepixel.vetmanager.api.client.core.application.port.out;

import com.vluepixel.vetmanager.api.client.core.application.dto.ClientDto;
import com.vluepixel.vetmanager.api.client.core.domain.model.Client;
import com.vluepixel.vetmanager.api.shared.application.port.out.ExportExcelPort;

/**
 * Export client excel port.
 */
public interface ExportClientExcelPort extends ExportExcelPort<Client, ClientDto> {
}
