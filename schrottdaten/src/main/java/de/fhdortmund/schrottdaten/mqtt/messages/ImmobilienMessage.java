package de.fhdortmund.schrottdaten.mqtt.messages;

import de.fhdortmund.schrottdaten.immobilie.entity.Immobilie;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ImmobilienMessage {
    public Action action;
    public Immobilie immobilie;
}
