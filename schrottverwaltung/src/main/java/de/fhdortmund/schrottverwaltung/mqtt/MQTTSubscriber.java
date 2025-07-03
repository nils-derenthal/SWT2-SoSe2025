package de.fhdortmund.schrottverwaltung.mqtt;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MQTTSubscriber {
    MqttClient client;

    @PostConstruct
    public void connect() {
        try{
            log.info("Trying to connect to MqttClient");
            client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
            client.setCallback(new MQTTCallBack());
            client.connect();

            client.subscribe("immobilie");
            client.subscribe("eigentuemer");
            log.info("Connected to MqttClient at tcp://localhost:1883");
        }catch (MqttException e){
            log.error("Could not connect to MqttClient");
            throw new RuntimeException(e);
        }
    }
}
