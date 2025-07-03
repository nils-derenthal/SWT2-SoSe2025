package de.fhdortmund.schrottdaten.mqtt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.fhdortmund.schrottdaten.eigentuemer.Eigentuemer;
import de.fhdortmund.schrottdaten.immobilie.Immobilie;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class MQTTPublisher {
    MqttClient client;

    @PostConstruct
    public void connect() throws MqttException {
        try{
            log.info("Trying to connect to MqttClient");
            client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
            client.connect();
            log.info("Connected to MqttClient at tcp://localhost:1883");
        }catch (MqttException e){
            log.error("Could not connect to MqttClient");
            throw new RuntimeException(e);
        }
    }

    public <T>void publishMessage(T data){
        if(data == null) return;
        try{
            String topic = "";
            if(data.getClass().equals(Eigentuemer.class)) topic = "eigentuemer";
            if(data.getClass().equals(Immobilie.class)) topic = "immobilie";

            ObjectMapper mapper = new ObjectMapper();
            String dataString = mapper.writeValueAsString(data);
            MqttMessage message = new MqttMessage();
            message.setPayload(dataString.getBytes());
            client.publish(topic, message);
            log.info("Message was published with topic: {}", topic);

        }catch (MqttException e){
            log.error("Error while trying to publish message with data: {}", data);
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            log.error("Failed to convert Object to JSON string");
            throw new RuntimeException(e);
        }
    }
    //TODO NOEL seperate init daten für beide Datenbanken
}
