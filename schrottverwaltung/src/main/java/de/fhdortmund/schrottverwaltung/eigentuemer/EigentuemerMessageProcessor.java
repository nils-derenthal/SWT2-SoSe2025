package de.fhdortmund.schrottverwaltung.eigentuemer;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.fhdortmund.schrottverwaltung.eigentuemer.dto.EigentuemerReceivedDTO;
import de.fhdortmund.schrottverwaltung.eigentuemer.service.EigentuemerService;
import de.fhdortmund.schrottverwaltung.mqtt.MQTTMessageProcessor;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.List;

@RequiredArgsConstructor
public class EigentuemerMessageProcessor implements MQTTMessageProcessor {
    private final EigentuemerService eigentuemerService;

    /**
     * Processes an incoming MQTT message for the "eigentuemer" topic.
     *
     * <p>This method is triggered when a message is received on the "eigentuemer" topic.
     * It converts the JSON payload into an {@link EigentuemerReceivedDTO} using Jackson's
     * {@link ObjectMapper}, and passes the resulting object to the {@code eigentuemerService}
     * for further processing or persistence.</p>
     *
     * @param topic   the MQTT topic the message was received on (expected to be "eigentuemer")
     * @param message the received {@link MqttMessage} containing the JSON payload
     * @throws Exception if deserialization of the message fails or the service call throws an exception
     */
    @Override
    public void processMessage(String topic, MqttMessage message) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        EigentuemerReceivedDTO eigentuemer = objectMapper.readValue(new String(message.getPayload()), EigentuemerReceivedDTO.class);
        eigentuemerService.saveEigentuemer(eigentuemer);
    }

}
