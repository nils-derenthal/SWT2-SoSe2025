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
    @Override
    public void processMessage(String topic, MqttMessage message) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        EigentuemerReceivedDTO eigentuemer = objectMapper.readValue(new String(message.getPayload()), EigentuemerReceivedDTO.class);
        eigentuemerService.saveEigentuemer(eigentuemer);
    }

}
