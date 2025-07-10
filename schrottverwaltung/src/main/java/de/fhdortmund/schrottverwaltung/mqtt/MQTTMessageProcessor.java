package de.fhdortmund.schrottverwaltung.mqtt;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.List;

public interface MQTTMessageProcessor {
    void processMessage(String topic, MqttMessage message) throws Exception;
}
