package com.vluepixel.vetmanager.api.patient.race.application.mapper;

import com.vluepixel.vetmanager.api.patient.race.application.dto.RaceDto;
import com.vluepixel.vetmanager.api.patient.race.domain.model.Race;
import com.vluepixel.vetmanager.api.patient.race.domain.request.CreateRaceRequest;
import com.vluepixel.vetmanager.api.patient.race.domain.request.UpdateRaceRequest;
import com.vluepixel.vetmanager.api.patient.species.application.dto.SpeciesDto;
import com.vluepixel.vetmanager.api.patient.species.domain.model.Species;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-28T20:38:37-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.41.0.z20250213-2037, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class RaceMapperImpl implements RaceMapper {

    @Override
    public Race.RaceBuilder toBuilder(Race t) {
        if ( t == null ) {
            return null;
        }

        Race.RaceBuilder raceBuilder = createRaceBuilder();

        raceBuilder.id( t.getId() );
        raceBuilder.name( StringUtilsMapper.trimString( t.getName() ) );
        raceBuilder.species( t.getSpecies() );

        return raceBuilder;
    }

    @Override
    public RaceDto toDto(Race domain) {
        if ( domain == null ) {
            return null;
        }

        RaceDto.RaceDtoBuilder raceDto = RaceDto.builder();

        if ( domain.getId() != null ) {
            raceDto.id( domain.getId().longValue() );
        }
        raceDto.name( StringUtilsMapper.trimString( domain.getName() ) );
        raceDto.species( speciesToSpeciesDto( domain.getSpecies() ) );

        return raceDto.build();
    }

    @Override
    public Race.RaceBuilder fromRequest(CreateRaceRequest request) {
        if ( request == null ) {
            return null;
        }

        Race.RaceBuilder raceBuilder = createRaceBuilder();

        raceBuilder.species( mapSpeciesIdToSpecies( request.getSpeciesId() ) );
        raceBuilder.name( StringUtilsMapper.trimString( request.getName() ) );

        return raceBuilder;
    }

    @Override
    public Race.RaceBuilder fromRequest(UpdateRaceRequest request) {
        if ( request == null ) {
            return null;
        }

        Race.RaceBuilder raceBuilder = createRaceBuilder();

        raceBuilder.species( mapSpeciesIdToSpecies( request.getSpeciesId() ) );
        raceBuilder.id( request.getId() );
        raceBuilder.name( StringUtilsMapper.trimString( request.getName() ) );

        return raceBuilder;
    }

    protected SpeciesDto speciesToSpeciesDto(Species species) {
        if ( species == null ) {
            return null;
        }

        SpeciesDto.SpeciesDtoBuilder speciesDto = SpeciesDto.builder();

        if ( species.getId() != null ) {
            speciesDto.id( species.getId().longValue() );
        }
        speciesDto.name( StringUtilsMapper.trimString( species.getName() ) );

        return speciesDto.build();
    }
}
