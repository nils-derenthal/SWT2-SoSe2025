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
    @Override
    public void processMessage(String topic, MqttMessage message) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ImmobilieReceivedDTO immobilie = objectMapper.readValue(new String(message.getPayload()), ImmobilieReceivedDTO.class);
        immobilienService.saveImmobilie(immobilie);
    }
}
