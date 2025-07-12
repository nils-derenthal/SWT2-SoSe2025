package de.fhdortmund.schrottverwaltung.eigentuemer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.fhdortmund.schrottverwaltung.eigentuemer.dto.EigentuemerReceivedDTO;
import de.fhdortmund.schrottverwaltung.eigentuemer.service.EigentuemerService;
import de.fhdortmund.schrottverwaltung.mqtt.MQTTMessageProcessor;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttMessage;

@RequiredArgsConstructor
public class EigentuemerMessageProcessor implements MQTTMessageProcessor {
    private final EigentuemerService eigentuemerService;

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
    public void processMessage(String topic, MqttMessage message) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new String(message.getPayload()));
        
        String action = jsonNode.get("action").asText();
        EigentuemerReceivedDTO eigentuemer = objectMapper.treeToValue(jsonNode.get("eigentuemer"), EigentuemerReceivedDTO.class);
        
        switch (action) {
            case "ADD" -> eigentuemerService.saveEigentuemer(eigentuemer);
            case "UPDATE" -> eigentuemerService.updateEigentuemer(eigentuemer);
            case "DELETE" -> eigentuemerService.deleteEigentuemer(eigentuemer.id());
        }
    }
}
