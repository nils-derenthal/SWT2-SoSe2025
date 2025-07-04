package de.fhdortmund.schrottverwaltung.bewertung.dto;

public record KriteriumDTO(
        long id,
        String bezeichnung,
        int maxGewichtung
) {
}
