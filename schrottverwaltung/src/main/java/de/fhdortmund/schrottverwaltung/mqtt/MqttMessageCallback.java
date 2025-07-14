package de.fhdortmund.schrottverwaltung.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Map;

@Slf4j
public class MqttMessageCallback implements MqttCallback {
    private final Map<String, MqttMessageStrategy<?>> strategies;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public MqttMessageCallback(Map<String, MqttMessageStrategy<?>> strategies){
        this.strategies = strategies;
    }

    public void connectionLost(Throwable throwable) {
        log.error("Connection to MQTT broker lost");
        log.error(String.valueOf(throwable.getCause()));
    }


    public void deliveryComplete(IMqttDeliveryToken token) {
        //wird nicht aufgerufen da subscriber keine Nachrichten versendet
        log.info("Delivery with tokenMessageId:{} complete", token.getMessageId());
    }

    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        log.info("Message received with topic: " + topic);
        process(strategies.get(topic), mqttMessage.getPayload());
    }

    private <T> void process(MqttMessageStrategy<T> processor, byte[] message) throws Exception {
        processor.processMessage(
                objectMapper.readValue(message, processor.dtoClass())
        );
    }
}
