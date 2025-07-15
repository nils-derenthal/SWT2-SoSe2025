package de.fhdortmund.schrottverwaltung.bewertung.mapper;

import de.fhdortmund.schrottverwaltung.bewertung.dto.BewertungDTO;
import de.fhdortmund.schrottverwaltung.bewertung.entity.Bewertung;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BewertungMapper {
    @Mapping(target = "immobilie", ignore = true)
    Bewertung toBewertung(BewertungDTO bewertung);
}
