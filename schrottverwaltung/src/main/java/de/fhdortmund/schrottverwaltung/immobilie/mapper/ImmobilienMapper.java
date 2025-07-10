package de.fhdortmund.schrottverwaltung.immobilie.mapper;

import de.fhdortmund.schrottverwaltung.immobilie.dto.ImmobilieReceivedDTO;
import de.fhdortmund.schrottverwaltung.immobilie.entity.Immobilie;
import de.fhdortmund.schrottverwaltung.immobilie.dto.ImmobilieDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ImmobilienMapper {
    List<ImmobilieDTO> toimmobilieDTO(List<Immobilie> immobilien);

    @Mapping(target = "archiviert", constant = "false")
    @Mapping(target = "bewertungen", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(source = "gebaeudeTyp", target = "gebaeudetyp")
    @Mapping(source = "bild", target = "bildBase64")
    Immobilie toEntity(ImmobilieReceivedDTO dto);
}
