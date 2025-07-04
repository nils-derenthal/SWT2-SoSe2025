package de.fhdortmund.schrottverwaltung.immobilie.dto;

import de.fhdortmund.schrottverwaltung.bewertung.dto.BewertungDTO;
import de.fhdortmund.schrottverwaltung.eigentuemer.dto.EigentuemerDTO;

import java.util.List;

public record ImmobilieDTO(
        long id,
        AdresseDTO adresse,
        String bezeichnung,
        boolean archiviert,
        String zustand,
        KoordinatenDTO koordinaten,
        String gemarkung,
        String flur,
        String flurstueck,
        int quadratMeter,
        String gebaeudetyp,
        String eigentumsform,
        List<BewertungDTO> bewertungen,
        EigentuemerDTO eigentuemer,
        ImmoStatusDTO immoStatus
) {
}
