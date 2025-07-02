package de.fhdortmund.schrottverwaltung.immobilie.dto;

public record ImmoStatusDTO(
        long id,
        String status,
        String beschreibung
) {
}
