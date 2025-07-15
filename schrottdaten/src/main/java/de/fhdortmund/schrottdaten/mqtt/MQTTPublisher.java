package de.fhdortmund.schrottdaten.mqtt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.fhdortmund.schrottdaten.mqtt.messages.EigentuemerMessage;
import de.fhdortmund.schrottdaten.mqtt.messages.ImmobilienMessage;
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

    /**
     * Initializes the MQTT client connection after the Spring context is constructed.
     * <p>
     * This method is annotated with {@code @PostConstruct}, meaning it will be called
     * automatically by the Spring framework after the bean's dependencies are injected.
     * It connects to the MQTT broker running at {@code tcp://mosquitto-broker:1883}.
     * <p>
     * A new client ID is generated for each connection using {@code MqttClient.generateClientId()}.
     *
     * @throws RuntimeException if a connection failure occurs and needs to be escalated
     */
    @PostConstruct
    public void connect() {
        try {
            log.info("Trying to connect to MqttClient");
            client = new MqttClient("tcp://mosquitto-broker:1883", MqttClient.generateClientId(), null);
            client.connect();
            log.info("Connected to MqttClient at tcp://mosquitto-broker:1883");
        } catch (MqttException e) {
            log.error("Could not connect to MqttClient");
            throw new RuntimeException(e);
        }
    }

    /**
     * Publishes the given data object to a specific MQTT topic based on its type.
     * <p>
     * Supported types are:
     * <ul>
     *     <li>{@code Eigentuemer} → Topic: {@code "eigentuemer"}</li>
     *     <li>{@code Immobilie} → Topic: {@code "immobilie"}</li>
     * </ul>
     * <p>
     * The object is serialized to a JSON string using Jackson's {@code ObjectMapper}
     * and published with MQTT Quality of Service (QoS) level 2, which guarantees
     * that the message is delivered exactly once. This comes with a performance
     * and network overhead.
     *
     * @param data the object to publish; must be of type {@code Eigentuemer} or {@code Immobilie}
     * @param <T>  the type of the object to publish
     * @throws RuntimeException if the MQTT message cannot be published
     *                          or if JSON serialization fails
     */
    public <T> void publishMessage(T data) {
        if (data == null) return;
        try {
            String topic = "";
            if(data.getClass().equals(EigentuemerMessage.class)) topic = "eigentuemer";
            if(data.getClass().equals(ImmobilienMessage.class)) topic = "immobilie";

            ObjectMapper mapper = new ObjectMapper();
            var dataBytes = mapper.writeValueAsBytes(data);

            MqttMessage message = new MqttMessage(dataBytes);
            message.setQos(1);

            client.setCallback(new MQTTCallback());
            client.publish(topic, message);
            log.info("Message was published with topic: {}", topic);

        } catch (MqttException e) {
            log.error("Error while trying to publish message with data: {}", data);
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            log.error("Failed to convert Object to JSON string");
            throw new RuntimeException(e);
        }
    }
}
