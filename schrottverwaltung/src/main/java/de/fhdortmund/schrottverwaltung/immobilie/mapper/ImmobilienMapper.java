package de.fhdortmund.schrottverwaltung.immobilie.mapper;

import de.fhdortmund.schrottverwaltung.immobilie.dto.ImmobilieReceivedDTO;
import de.fhdortmund.schrottverwaltung.immobilie.entity.Immobilie;
import de.fhdortmund.schrottverwaltung.immobilie.dto.ImmobilieDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = KoordinatenMapper.class)
public interface ImmobilienMapper {
    @Mapping(target = "bild", ignore = true)
    List<ImmobilieDTO> toimmobilieDTO(List<Immobilie> immobilien);

    ImmobilieDTO toimmobilieDTO(Immobilie immobilie);

    @Mapping(target = "archiviert", constant = "false")
    @Mapping(target = "bewertungen", ignore = true)
    @Mapping(target = "immoStati", ignore = true)
    @Mapping(source = "gebaeudeTyp", target = "gebaeudetyp")
    Immobilie toEntity(ImmobilieReceivedDTO dto);
}
