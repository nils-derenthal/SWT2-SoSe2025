package de.fhdortmund.schrottverwaltung.immobilie.mapper;

import de.fhdortmund.schrottverwaltung.immobilie.dto.ImmoStatusDTO;
import de.fhdortmund.schrottverwaltung.immobilie.entity.ImmoStatus;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ImmoStatusMapper {
    ImmoStatusDTO toDto(ImmoStatus immoStatus);

    @Mapping(target = "id", ignore=true)
    @Mapping(target = "status", source = "status")
    ImmoStatus toEntity(ImmoStatusDTO dto);
}
