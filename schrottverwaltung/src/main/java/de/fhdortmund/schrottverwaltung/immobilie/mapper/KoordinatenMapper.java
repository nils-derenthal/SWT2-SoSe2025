package de.fhdortmund.schrottverwaltung.immobilie.mapper;

import de.fhdortmund.schrottverwaltung.immobilie.dto.KoordinatenDTO;
import de.fhdortmund.schrottverwaltung.immobilie.entity.Koordinaten;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface KoordinatenMapper {

    @Mapping(target = "XKoordinate", source = "xKoordinate")
    @Mapping(target = "YKoordinate", source = "yKoordinate")
    Koordinaten toEntity(KoordinatenDTO dto);

    @Mapping(target = "xKoordinate", source = "XKoordinate")
    @Mapping(target = "yKoordinate", source = "YKoordinate")
    KoordinatenDTO toDto(Koordinaten koordinaten);
}
