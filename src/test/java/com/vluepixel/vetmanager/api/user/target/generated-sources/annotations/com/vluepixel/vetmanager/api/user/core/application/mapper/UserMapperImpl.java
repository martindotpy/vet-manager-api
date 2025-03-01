package com.vluepixel.vetmanager.api.user.core.application.mapper;

import com.vluepixel.vetmanager.api.auth.core.domain.request.RegisterUserRequest;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;
import com.vluepixel.vetmanager.api.user.core.application.dto.UserDto;
import com.vluepixel.vetmanager.api.user.core.domain.model.User;
import com.vluepixel.vetmanager.api.user.core.domain.model.enums.UserRole;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-28T20:38:37-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.41.0.z20250213-2037, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User.UserBuilder toBuilder(User t) {
        if ( t == null ) {
            return null;
        }

        User.UserBuilder userBuilder = createBuilder();

        userBuilder.deleted( t.isDeleted() );
        userBuilder.email( StringUtilsMapper.trimString( t.getEmail() ) );
        userBuilder.firstName( StringUtilsMapper.trimString( t.getFirstName() ) );
        userBuilder.id( t.getId() );
        userBuilder.lastName( StringUtilsMapper.trimString( t.getLastName() ) );
        userBuilder.password( StringUtilsMapper.trimString( t.getPassword() ) );
        userBuilder.profileImageUrl( StringUtilsMapper.trimString( t.getProfileImageUrl() ) );
        List<UserRole> list = t.getRoles();
        if ( list != null ) {
            userBuilder.roles( new ArrayList<UserRole>( list ) );
        }

        return userBuilder;
    }

    @Override
    public UserDto toDto(User domain) {
        if ( domain == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.email( StringUtilsMapper.trimString( domain.getEmail() ) );
        userDto.firstName( StringUtilsMapper.trimString( domain.getFirstName() ) );
        userDto.id( domain.getId() );
        userDto.lastName( StringUtilsMapper.trimString( domain.getLastName() ) );
        userDto.profileImageUrl( StringUtilsMapper.trimString( domain.getProfileImageUrl() ) );
        List<UserRole> list = domain.getRoles();
        if ( list != null ) {
            userDto.roles( new ArrayList<UserRole>( list ) );
        }

        return userDto.build();
    }

    @Override
    public User toDomain(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder user = createBuilder();

        user.email( StringUtilsMapper.trimString( dto.getEmail() ) );
        user.firstName( StringUtilsMapper.trimString( dto.getFirstName() ) );
        user.id( dto.getId() );
        user.lastName( StringUtilsMapper.trimString( dto.getLastName() ) );
        List<UserRole> list = dto.getRoles();
        if ( list != null ) {
            user.roles( new ArrayList<UserRole>( list ) );
        }

        return user.build();
    }

    @Override
    public User.UserBuilder fromRegister(RegisterUserRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder userBuilder = createBuilder();

        userBuilder.email( StringUtilsMapper.trimString( request.getEmail() ) );
        userBuilder.firstName( StringUtilsMapper.trimString( request.getFirstName() ) );
        userBuilder.lastName( StringUtilsMapper.trimString( request.getLastName() ) );

        return userBuilder;
    }
}
