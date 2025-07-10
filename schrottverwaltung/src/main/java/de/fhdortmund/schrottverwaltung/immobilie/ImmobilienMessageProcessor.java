package de.fhdortmund.schrottverwaltung.immobilie;

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
     *
     * <p>This method is called when a message is received on the "immobilie" topic.
     * It deserializes the JSON payload into an {@link ImmobilieReceivedDTO} object
     * using Jackson's {@link ObjectMapper}, and then delegates the object to the
     * {@code immobilienService} for saving.</p>
     *
     * @param topic   the MQTT topic the message was received on (expected to be "immobilie")
     * @param message the received {@link MqttMessage} containing the JSON payload
     * @throws Exception if the payload cannot be deserialized or saving fails
     */
    @Override
    public void processMessage(String topic, MqttMessage message) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ImmobilieReceivedDTO immobilie = objectMapper.readValue(new String(message.getPayload()), ImmobilieReceivedDTO.class);
        immobilienService.saveImmobilie(immobilie);
    }
}
