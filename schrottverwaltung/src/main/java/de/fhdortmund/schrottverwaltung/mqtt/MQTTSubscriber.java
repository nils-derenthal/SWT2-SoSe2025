package de.fhdortmund.schrottverwaltung.mqtt;

import de.fhdortmund.schrottverwaltung.eigentuemer.EigentuemerMessageProcessor;
import de.fhdortmund.schrottverwaltung.eigentuemer.service.EigentuemerService;
import de.fhdortmund.schrottverwaltung.immobilie.ImmobilienMessageProcessor;
import de.fhdortmund.schrottverwaltung.immobilie.service.ImmobilienService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class MQTTSubscriber {
    MqttClient client;
    private final ImmobilienService immobilienService;
    private final EigentuemerService eigentuemerService;
    Map<String, MQTTMessageProcessor> dispatchMap;

    /**
     * Initializes and connects an MQTT client on application startup.
     *
     * <p>This method is automatically invoked after dependency injection is done,
     * thanks to the {@code @PostConstruct} annotation. It sets up the MQTT client,
     * configures connection options, sets a callback for incoming messages, and subscribes
     * to relevant topics.</p>
     *
     * <p>The client connects to the broker at {@code tcp://mosquitto-broker:1883} and subscribes to
     * topics "immobilie" and "eigentuemer" with QoS level 1. A message dispatching system is
     * initialized using a map that associates topics with their respective message processors.</p>
     *
     * @throws RuntimeException if the client fails to connect or subscribe to topics
     */
    @PostConstruct
    public void connect() {
        try{
            log.info("Trying to connect to MqttClient");
            client = new MqttClient("tcp://mosquitto-broker:1883", MqttClient.generateClientId(), null);

            dispatchMap = new HashMap<>();
            dispatchMap.put("eigentuemer", new EigentuemerMessageProcessor(eigentuemerService));
            dispatchMap.put("immobilie", new ImmobilienMessageProcessor(immobilienService));
            MqttConnectOptions options = new MqttConnectOptions();
            options.setKeepAliveInterval(60);
            options.setAutomaticReconnect(true);
            options.setConnectionTimeout(10);
            options.setCleanSession(false);

            client.setCallback(new MQTTCallBack(dispatchMap));

            client.connect();
            client.subscribe("immobilie", 1);
            client.subscribe("eigentuemer",1);
            log.info("Connected to MqttClient at tcp://mosquitto-broker:1883");

        }catch (MqttException e){
            log.error("Could not connect to MqttClient");
            throw new RuntimeException(e);
        }
    }
}
