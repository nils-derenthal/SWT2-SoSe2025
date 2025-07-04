package de.fhdortmund.schrottverwaltung.bewertung.dto;

public record BewertungDTO(
        long id,
        int gewichtung,
        KriteriumDTO kriterium
) {
}
