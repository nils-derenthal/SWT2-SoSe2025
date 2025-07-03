package de.fhdortmund.schrottverwaltung.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

@Slf4j
public class MQTTCallBack implements MqttCallback {

    public void connectionLost(Throwable throwable) {
        log.error("Connection to MQTT broker lost");
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        log.info("Message war succesfully sended");
    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println(s);
        System.out.println("Message received:\t"+ new String(mqttMessage.getPayload()) );
        if(s.equals("eigentuemer")) System.out.println("eigentuemer");
        if(s.equals("immobilie")) System.out.println("immobilie");
    }

    }
