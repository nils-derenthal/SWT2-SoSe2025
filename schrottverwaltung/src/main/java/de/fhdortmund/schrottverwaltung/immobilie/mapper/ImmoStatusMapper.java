package de.fhdortmund.schrottverwaltung.immobilie.mapper;

import de.fhdortmund.schrottverwaltung.immobilie.dto.ImmoStatusDTO;
import de.fhdortmund.schrottverwaltung.immobilie.entity.ImmoStatus;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ImmoStatusMapper {
    ImmoStatusDTO toDto(ImmoStatus immoStatus);
    ImmoStatus toModel(ImmoStatusDTO dto);
}
