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
     * Initializes and connects the MQTT client to the local MQTT broker.
     * <p>
     * This method is automatically invoked after bean construction via the {@code @PostConstruct} annotation.
     * It performs the following steps:
     * <ul>
     *     <li>Creates a new {@link MqttClient} instance with a generated client ID</li>
     *     <li>Registers a {@link MQTTCallBack} that processes incoming messages for the topics</li>
     *     <li>Connects to the MQTT broker at {@code tcp://localhost:1883}</li>
     *     <li>Subscribes to the topics {@code "immobilie"} and {@code "eigentuemer"} with QoS level 2</li>
     * </ul>
     * <p>
     * The use of QoS level 2 ensures exactly-once delivery for each message, which is critical for data consistency.
     * <p>
     * If the connection to the broker fails, a {@link RuntimeException} is thrown.
     *
     * @throws RuntimeException if the MQTT client cannot connect or subscribe to the broker
     */
    @PostConstruct
    public void connect() {
        try{
            log.info("Trying to connect to MqttClient");
            client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());

            dispatchMap = new HashMap<>();
            dispatchMap.put("eigentuemer", new EigentuemerMessageProcessor(eigentuemerService));
            dispatchMap.put("immobilie", new ImmobilienMessageProcessor(immobilienService));
            MqttConnectOptions options = new MqttConnectOptions();
            options.setKeepAliveInterval(60);
            options.setAutomaticReconnect(true);
            options.setConnectionTimeout(10);

            client.setCallback(new MQTTCallBack(dispatchMap));

            client.connect();
            client.subscribe("immobilie", 1);
            client.subscribe("eigentuemer",1);
            log.info("Connected to MqttClient at tcp://localhost:1883");

        }catch (MqttException e){
            log.error("Could not connect to MqttClient");
            throw new RuntimeException(e);
        }
    }
}
