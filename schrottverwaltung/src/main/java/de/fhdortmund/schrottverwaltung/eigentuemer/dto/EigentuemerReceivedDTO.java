package de.fhdortmund.schrottverwaltung.eigentuemer.dto;


import de.fhdortmund.schrottverwaltung.immobilie.entity.Adresse;

public record EigentuemerReceivedDTO(
        Long id,
        String vorname,
        String nachname,
        Adresse anschrift
) {
}
