package de.fhdortmund.schrottverwaltung.mqtt;

import de.fhdortmund.schrottverwaltung.eigentuemer.EigentuemerMessageStrategy;
import de.fhdortmund.schrottverwaltung.eigentuemer.service.EigentuemerService;
import de.fhdortmund.schrottverwaltung.immobilie.ImmobilienMessageStrategy;
import de.fhdortmund.schrottverwaltung.immobilie.service.ImmobilienService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class MQTTSubscriber {
    private final ImmobilienService immobilienService;
    private final EigentuemerService eigentuemerService;

    private final static String EIGENTUEMER_TOPIC = "eigentuemer";
    private final static String IMMOBILIEN_TOPIC = "immobilie";

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
        log.info("Trying to connect to MqttClient");

        final Map<String, MqttMessageStrategy<?>> messageStrategies = Map.of(
                EIGENTUEMER_TOPIC, new EigentuemerMessageStrategy(eigentuemerService),
                IMMOBILIEN_TOPIC, new ImmobilienMessageStrategy(immobilienService)
        );

        final String url = "tcp://mosquitto-broker:1883";

        try {
            MqttClient client = new MqttClient(url, MqttClient.generateClientId(), null);

            MqttConnectOptions options = new MqttConnectOptions();
            options.setKeepAliveInterval(60);
            options.setAutomaticReconnect(true);
            options.setConnectionTimeout(10);
            options.setCleanSession(false);

            client.setCallback(new MqttMessageCallback(messageStrategies));

            client.connect(options);

            log.info("Connected to MqttClient at tcp://mosquitto-broker:1883");

            client.subscribe(EIGENTUEMER_TOPIC, 1);
            client.subscribe(IMMOBILIEN_TOPIC, 1);

        } catch (MqttException e) {
            log.error("Could not connect to MqttClient");
            throw new RuntimeException(e);
        }
    }
}
