package de.fhdortmund.schrottverwaltung.eigentuemer.mapper;

import de.fhdortmund.schrottverwaltung.eigentuemer.dto.EigentuemerReceivedDTO;
import de.fhdortmund.schrottverwaltung.eigentuemer.entity.Eigentuemer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EigentuemerMapper {

    @Mapping(target = "anmerkung", ignore = true)
    Eigentuemer toEntity(EigentuemerReceivedDTO dto);
}
