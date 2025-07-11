package de.fhdortmund.schrottdaten.mqtt.messages;

import de.fhdortmund.schrottdaten.eigentuemer.entity.Eigentuemer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EigentuemerMessage {
    public Action action;
    public Eigentuemer eigentuemer;
}
