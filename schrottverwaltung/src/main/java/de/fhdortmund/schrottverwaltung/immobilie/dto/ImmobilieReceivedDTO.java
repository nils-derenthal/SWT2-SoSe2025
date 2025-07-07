package de.fhdortmund.schrottverwaltung.immobilie.dto;


import de.fhdortmund.schrottverwaltung.eigentuemer.entity.Eigentuemer;
import de.fhdortmund.schrottverwaltung.immobilie.*;
import de.fhdortmund.schrottverwaltung.immobilie.entity.Adresse;
import de.fhdortmund.schrottverwaltung.immobilie.entity.Koordinaten;

public record ImmobilieReceivedDTO(
        Long id,
        Adresse adresse,
        String bezeichnung,
        String zustand,
        Koordinaten koordinaten,
        String gemarkung,
        String flur,
        String flurstueck,
        Integer quadratMeter,
        Gebaeudetyp gebaeudeTyp,
        EigentumsForm eigentumsForm,
        Eigentuemer eigentuemer,
        String bild
) {
}
