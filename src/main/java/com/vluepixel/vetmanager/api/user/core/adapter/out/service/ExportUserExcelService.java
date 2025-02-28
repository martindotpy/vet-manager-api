package com.vluepixel.vetmanager.api.user.core.adapter.out.service;

import org.springframework.stereotype.Service;

import com.vluepixel.vetmanager.api.shared.adapter.out.service.ExportExcelSubService;
import com.vluepixel.vetmanager.api.user.core.application.dto.UserDto;
import com.vluepixel.vetmanager.api.user.core.application.mapper.UserMapper;
import com.vluepixel.vetmanager.api.user.core.application.port.out.ExportUserExcelPort;
import com.vluepixel.vetmanager.api.user.core.domain.model.User;
import com.vluepixel.vetmanager.api.user.core.domain.repository.UserRepository;

/**
 * Export user excel service.
 */
@Service
public final class ExportUserExcelService extends ExportExcelSubService<User, UserDto>
        implements ExportUserExcelPort {
    public ExportUserExcelService(UserRepository userRepository, UserMapper userMapper) {
        super(userRepository, userMapper, UserDto.class);
    }
}
