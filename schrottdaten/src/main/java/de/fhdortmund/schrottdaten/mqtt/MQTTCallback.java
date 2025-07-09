package de.fhdortmund.schrottdaten.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

@Slf4j
public class MQTTCallback implements MqttCallback {


    public void connectionLost(Throwable throwable) {
        log.error("Connection to MQTT broker lost");
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        log.info("Delivery with tokenMessageId:{} complete", token.getMessageId());
    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        //wird nie aufgerufen, da publisher keine Nachrichten zurück bekommt
        log.info("Message received:\t"+ new String(mqttMessage.getPayload()));
    }

}
