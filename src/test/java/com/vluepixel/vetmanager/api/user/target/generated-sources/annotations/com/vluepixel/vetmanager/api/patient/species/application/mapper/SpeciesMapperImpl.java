package com.vluepixel.vetmanager.api.patient.species.application.mapper;

import com.vluepixel.vetmanager.api.patient.species.application.dto.SpeciesDto;
import com.vluepixel.vetmanager.api.patient.species.domain.model.Species;
import com.vluepixel.vetmanager.api.patient.species.domain.request.CreateSpeciesRequest;
import com.vluepixel.vetmanager.api.patient.species.domain.request.UpdateSpeciesRequest;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-28T20:38:37-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.41.0.z20250213-2037, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class SpeciesMapperImpl implements SpeciesMapper {

    @Override
    public Species.SpeciesBuilder toBuilder(Species t) {
        if ( t == null ) {
            return null;
        }

        Species.SpeciesBuilder speciesBuilder = createSpeciesBuilder();

        speciesBuilder.id( t.getId() );
        speciesBuilder.name( StringUtilsMapper.trimString( t.getName() ) );

        return speciesBuilder;
    }

    @Override
    public SpeciesDto toDto(Species domain) {
        if ( domain == null ) {
            return null;
        }

        SpeciesDto.SpeciesDtoBuilder speciesDto = SpeciesDto.builder();

        if ( domain.getId() != null ) {
            speciesDto.id( domain.getId().longValue() );
        }
        speciesDto.name( StringUtilsMapper.trimString( domain.getName() ) );

        return speciesDto.build();
    }

    @Override
    public Species.SpeciesBuilder fromRequest(CreateSpeciesRequest request) {
        if ( request == null ) {
            return null;
        }

        Species.SpeciesBuilder speciesBuilder = createSpeciesBuilder();

        speciesBuilder.name( StringUtilsMapper.trimString( request.getName() ) );

        return speciesBuilder;
    }

    @Override
    public Species.SpeciesBuilder fromRequest(UpdateSpeciesRequest request) {
        if ( request == null ) {
            return null;
        }

        Species.SpeciesBuilder speciesBuilder = createSpeciesBuilder();

        speciesBuilder.id( request.getId() );
        speciesBuilder.name( StringUtilsMapper.trimString( request.getName() ) );

        return speciesBuilder;
    }
}
