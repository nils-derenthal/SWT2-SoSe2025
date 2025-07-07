package de.fhdortmund.schrottverwaltung.immobilie.dto;

import de.fhdortmund.schrottverwaltung.eigentuemer.Eigentuemer;
import de.fhdortmund.schrottverwaltung.immobilie.AdresseT;
import de.fhdortmund.schrottverwaltung.immobilie.EigentumsForm;
import de.fhdortmund.schrottverwaltung.immobilie.Gebaeudetyp;
import de.fhdortmund.schrottverwaltung.immobilie.Koordinaten;

public record ImmobilieReceivedDTO(
        Long id,
        AdresseT adresse,
        String bezeichnung,
        String zustand,
        Koordinaten koordinaten,
        String gemarkung,
        String flur,
        String flurstueck,
        Integer quadratMeter,
        Gebaeudetyp gebaeudeTyp,
        EigentumsForm eigentumsForm,
        Eigentuemer eigentuemer
) {
}
