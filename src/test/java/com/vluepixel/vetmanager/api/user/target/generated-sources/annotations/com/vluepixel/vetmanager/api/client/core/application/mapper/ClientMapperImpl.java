package com.vluepixel.vetmanager.api.client.core.application.mapper;

import com.vluepixel.vetmanager.api.client.core.application.dto.ClientDto;
import com.vluepixel.vetmanager.api.client.core.domain.model.Client;
import com.vluepixel.vetmanager.api.client.core.domain.request.CreateClientRequest;
import com.vluepixel.vetmanager.api.client.core.domain.request.UpdateClientRequest;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;
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
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Client.ClientBuilder toBuilder(Client t) {
        if ( t == null ) {
            return null;
        }

        Client.ClientBuilder clientBuilder = createClientBuilder();

        clientBuilder.address( StringUtilsMapper.trimString( t.getAddress() ) );
        clientBuilder.deleted( t.isDeleted() );
        List<String> list = t.getEmails();
        if ( list != null ) {
            clientBuilder.emails( new ArrayList<String>( list ) );
        }
        clientBuilder.firstName( StringUtilsMapper.trimString( t.getFirstName() ) );
        clientBuilder.id( t.getId() );
        clientBuilder.identification( StringUtilsMapper.trimString( t.getIdentification() ) );
        clientBuilder.identificationType( t.getIdentificationType() );
        clientBuilder.lastName( StringUtilsMapper.trimString( t.getLastName() ) );
        List<String> list1 = t.getPhones();
        if ( list1 != null ) {
            clientBuilder.phones( new ArrayList<String>( list1 ) );
        }

        return clientBuilder;
    }

    @Override
    public ClientDto toDto(Client domain) {
        if ( domain == null ) {
            return null;
        }

        ClientDto.ClientDtoBuilder clientDto = ClientDto.builder();

        clientDto.address( StringUtilsMapper.trimString( domain.getAddress() ) );
        List<String> list = domain.getEmails();
        if ( list != null ) {
            clientDto.emails( new ArrayList<String>( list ) );
        }
        clientDto.firstName( StringUtilsMapper.trimString( domain.getFirstName() ) );
        clientDto.id( domain.getId() );
        clientDto.identification( StringUtilsMapper.trimString( domain.getIdentification() ) );
        clientDto.identificationType( domain.getIdentificationType() );
        clientDto.lastName( StringUtilsMapper.trimString( domain.getLastName() ) );
        List<String> list1 = domain.getPhones();
        if ( list1 != null ) {
            clientDto.phones( new ArrayList<String>( list1 ) );
        }

        return clientDto.build();
    }

    @Override
    public Client.ClientBuilder fromRequest(CreateClientRequest request) {
        if ( request == null ) {
            return null;
        }

        Client.ClientBuilder clientBuilder = createClientBuilder();

        clientBuilder.address( StringUtilsMapper.trimString( request.getAddress() ) );
        List<String> list = request.getEmails();
        if ( list != null ) {
            clientBuilder.emails( new ArrayList<String>( list ) );
        }
        clientBuilder.firstName( StringUtilsMapper.trimString( request.getFirstName() ) );
        clientBuilder.identification( StringUtilsMapper.trimString( request.getIdentification() ) );
        clientBuilder.identificationType( request.getIdentificationType() );
        clientBuilder.lastName( StringUtilsMapper.trimString( request.getLastName() ) );
        List<String> list1 = request.getPhones();
        if ( list1 != null ) {
            clientBuilder.phones( new ArrayList<String>( list1 ) );
        }

        return clientBuilder;
    }

    @Override
    public Client.ClientBuilder fromRequest(UpdateClientRequest request) {
        if ( request == null ) {
            return null;
        }

        Client.ClientBuilder clientBuilder = createClientBuilder();

        clientBuilder.address( StringUtilsMapper.trimString( request.getAddress() ) );
        List<String> list = request.getEmails();
        if ( list != null ) {
            clientBuilder.emails( new ArrayList<String>( list ) );
        }
        clientBuilder.firstName( StringUtilsMapper.trimString( request.getFirstName() ) );
        clientBuilder.id( request.getId() );
        clientBuilder.identification( StringUtilsMapper.trimString( request.getIdentification() ) );
        clientBuilder.identificationType( request.getIdentificationType() );
        clientBuilder.lastName( StringUtilsMapper.trimString( request.getLastName() ) );
        List<String> list1 = request.getPhones();
        if ( list1 != null ) {
            clientBuilder.phones( new ArrayList<String>( list1 ) );
        }

        return clientBuilder;
    }
}
