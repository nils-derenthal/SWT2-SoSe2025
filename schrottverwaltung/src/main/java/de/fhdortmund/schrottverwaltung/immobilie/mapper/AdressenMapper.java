package de.fhdortmund.schrottverwaltung.immobilie.mapper;

import de.fhdortmund.schrottverwaltung.immobilie.dto.AdresseDTO;
import de.fhdortmund.schrottverwaltung.immobilie.entity.Adresse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AdressenMapper {
    Adresse toAdresse(AdresseDTO adresseDTO);
}
