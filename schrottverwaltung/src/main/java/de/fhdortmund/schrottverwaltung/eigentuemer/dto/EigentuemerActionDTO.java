package de.fhdortmund.schrottverwaltung.eigentuemer.dto;

import de.fhdortmund.schrottverwaltung.eigentuemer.dto.EigentuemerReceivedDTO;

public record EigentuemerActionDTO (String action, EigentuemerReceivedDTO eigentuemer) {
}
