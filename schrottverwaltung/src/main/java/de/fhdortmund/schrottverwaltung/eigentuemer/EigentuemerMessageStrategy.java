package de.fhdortmund.schrottverwaltung.eigentuemer;

import de.fhdortmund.schrottverwaltung.eigentuemer.dto.EigentuemerActionDTO;
import de.fhdortmund.schrottverwaltung.eigentuemer.service.EigentuemerService;
import de.fhdortmund.schrottverwaltung.mqtt.MqttMessageStrategy;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttMessage;

@RequiredArgsConstructor
public class EigentuemerMessageStrategy implements MqttMessageStrategy<EigentuemerActionDTO> {
    private final EigentuemerService eigentuemerService;

    @Override
    public Class<EigentuemerActionDTO> dtoClass() {
        return EigentuemerActionDTO.class;
    }

    /**
     * Processes an incoming MQTT message for the "eigentuemer" topic.
     * <p>
     * This method parses the JSON payload to extract the action (ADD, UPDATE, DELETE)
     * and the eigentuemer data, then delegates to the appropriate service method.
     *
     * @param topic   the MQTT topic the message was received on (expected to be "eigentuemer")
     * @param message the received {@link MqttMessage} containing the JSON payload with action and eigentuemer data
     * @throws Exception if the payload cannot be deserialized or service operation fails
     */
    @Override
    public void processMessage(EigentuemerActionDTO dto) throws Exception {
        var eigentuemer = dto.eigentuemer();

        switch (dto.action()) {
            case "ADD" -> eigentuemerService.saveEigentuemer(eigentuemer);
            case "UPDATE" -> eigentuemerService.updateEigentuemer(eigentuemer);
            case "DELETE" -> eigentuemerService.deleteEigentuemer(eigentuemer.id());
        }
    }
}
