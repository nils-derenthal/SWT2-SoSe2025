package de.fhdortmund.schrottverwaltung.immobilie;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.fhdortmund.schrottverwaltung.immobilie.dto.ImmobilieReceivedDTO;
import de.fhdortmund.schrottverwaltung.immobilie.service.ImmobilienService;
import de.fhdortmund.schrottverwaltung.mqtt.MQTTMessageProcessor;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttMessage;

@RequiredArgsConstructor
public class ImmobilienMessageProcessor implements MQTTMessageProcessor {
    private final ImmobilienService immobilienService;

    /**
     * Processes an incoming MQTT message for the "immobilie" topic.
     * <p>
     * This method parses the JSON payload to extract the action (ADD, UPDATE, DELETE)
     * and the immobilie data, then delegates to the appropriate service method.
     *
     * @param topic   the MQTT topic the message was received on (expected to be "immobilie")
     * @param message the received {@link MqttMessage} containing the JSON payload with action and immobilie data
     * @throws Exception if the payload cannot be deserialized or service operation fails
     */
    @Override
    public void processMessage(String topic, MqttMessage message) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new String(message.getPayload()));
        
        String action = jsonNode.get("action").asText();
        ImmobilieReceivedDTO immobilie = objectMapper.treeToValue(jsonNode.get("immobilie"), ImmobilieReceivedDTO.class);
        
        switch (action) {
            case "ADD" -> immobilienService.saveImmobilie(immobilie);
            case "UPDATE" -> immobilienService.updateImmobilie(immobilie);
            case "DELETE" -> immobilienService.deleteImmobilie(immobilie.id());
        }
    }
}
