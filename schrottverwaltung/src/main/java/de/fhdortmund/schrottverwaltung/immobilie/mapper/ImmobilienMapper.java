package de.fhdortmund.schrottverwaltung.immobilie.mapper;

import de.fhdortmund.schrottverwaltung.immobilie.entity.Immobilie;
import de.fhdortmund.schrottverwaltung.immobilie.dto.ImmobilieDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ImmobilienMapper {
    List<ImmobilieDTO> toimmobilieDTO(List<Immobilie> immobilien);
    ImmobilieDTO toimmobilieDTO(Immobilie immobilie);
}
