package de.fhdortmund.schrottverwaltung.eigentuemer.dto;

import de.fhdortmund.schrottverwaltung.immobilie.AdresseT;

public record EigentuemerReceivedDTO(
        Long id,
        String vorname,
        String nachname,
        AdresseT anschrift
) {
}
