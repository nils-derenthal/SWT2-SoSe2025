package de.fhdortmund.schrottverwaltung.immobilie.dto;

import de.fhdortmund.schrottverwaltung.immobilie.EigentumsForm;
import de.fhdortmund.schrottverwaltung.immobilie.Gebaeudetyp;

public record ImmoInfoDTO(Long adresse,
                          Long koordinaten,
                          String gemarkung,
                          String flur,
                          String flurstueck,
                          Integer quadratmeter,
                          Gebaeudetyp gebaeudetyp,
                          EigentumsForm eigentumsForm) {
}
