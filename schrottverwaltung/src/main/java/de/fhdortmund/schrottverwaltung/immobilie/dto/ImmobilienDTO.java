package de.fhdortmund.schrottverwaltung.immobilie.dto;

import de.fhdortmund.schrottverwaltung.bewertung.entity.Bewertung;
import de.fhdortmund.schrottverwaltung.eigentuemer.dto.EigentuemerDTO;

import java.util.List;

public record ImmobilienDTO(
        long id,
        AdresseDTO adresse,
        String bezeichnung,
        String archiviert,
        String zustand,
        KoordinatenDTO koordinaten,
        String gemarkung,
        String flur,
        String flurstueck,
        int quadratMeter,
        String gebaudeTyp,
        String eigentumsForm,
        List<Bewertung> bewertungen,
        EigentuemerDTO eigentuemer,
        ImmoStatusDTO immoStatusDTO
) {
}
