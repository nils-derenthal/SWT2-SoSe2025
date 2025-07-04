package de.fhdortmund.schrottverwaltung.immobilie.dto;

public record AdresseDTO(
        long id,
        String strasse,
        int hausnummer,
        String hausnummerZusatz,
        int plz,
        String ort,
        String stadtbezirk
) {
}
