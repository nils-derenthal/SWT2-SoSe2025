package de.fhdortmund.schrottverwaltung.eigentuemer.dto;

import de.fhdortmund.schrottverwaltung.immobilie.dto.AdresseDTO;

public record EigentuemerDTO(
        long id,
        String vorname,
        String nachname,
        AdresseDTO anschrift,
        String anmerkung
) {
}
