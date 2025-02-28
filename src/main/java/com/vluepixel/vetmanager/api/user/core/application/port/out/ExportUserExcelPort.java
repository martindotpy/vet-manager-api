package com.vluepixel.vetmanager.api.user.core.application.port.out;

import com.vluepixel.vetmanager.api.shared.application.port.out.ExportExcelPort;
import com.vluepixel.vetmanager.api.user.core.application.dto.UserDto;
import com.vluepixel.vetmanager.api.user.core.domain.model.User;

/**
 * Export user excel port.
 */
public interface ExportUserExcelPort extends ExportExcelPort<User, UserDto> {
}
