package de.fhdortmund.schrottverwaltung.immobilie;

import de.fhdortmund.schrottverwaltung.immobilie.dto.ImmobilieActionDTO;
import de.fhdortmund.schrottverwaltung.immobilie.service.ImmobilienService;
import de.fhdortmund.schrottverwaltung.mqtt.MqttMessageStrategy;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttMessage;

@RequiredArgsConstructor
public class ImmobilienMessageStrategy implements MqttMessageStrategy<ImmobilieActionDTO> {
    private final ImmobilienService immobilienService;

    @Override
    public Class<ImmobilieActionDTO> dtoClass() {
        return ImmobilieActionDTO.class;
    }

    /**
     * Processes an incoming MQTT message for the "immobilie" topic.
     */
    @Override
    public void processMessage(ImmobilieActionDTO dto) {
        var immobilie = dto.immobilie();

        switch (dto.action()) {
            case "ADD" -> immobilienService.saveImmobilie(immobilie);
            case "UPDATE" -> immobilienService.updateImmobilie(immobilie);
            case "DELETE" -> immobilienService.deleteImmobilie(immobilie.id());
        }
    }
}
