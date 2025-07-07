package de.fhdortmund.schrottverwaltung.immobilie.mapper;

import de.fhdortmund.schrottverwaltung.immobilie.dto.ImmobilieReceivedDTO;
import de.fhdortmund.schrottverwaltung.immobilie.entity.Immobilie;
import de.fhdortmund.schrottverwaltung.immobilie.dto.ImmobilieDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {KoordinatenMapper.class, ImmoStatusMapper.class})
public interface ImmobilienMapper {
    @Mapping(target = "bild", ignore = true)

    @Mapping(target = "immoStatus", source = "status")
    ImmobilieDTO toDto(Immobilie immobilie);

    List<ImmobilieDTO> toimmobilieDTO(List<Immobilie> immobilien);

    @Mapping(target = "archiviert", constant = "false")
    @Mapping(target = "bewertungen", ignore = true)
    @Mapping(target = "immoStati", ignore = true)
    @Mapping(source = "gebaeudeTyp", target = "gebaeudetyp")
    Immobilie toEntity(ImmobilieReceivedDTO dto);
}
