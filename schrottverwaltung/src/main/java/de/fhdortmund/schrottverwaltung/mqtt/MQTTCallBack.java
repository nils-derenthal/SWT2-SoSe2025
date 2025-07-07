package de.fhdortmund.schrottverwaltung.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Arrays;
import java.util.Map;

@Slf4j
public class MQTTCallBack implements MqttCallback {
    Map<String, MQTTMessageProcessor> dispatchMap;
    public MQTTCallBack(Map<String, MQTTMessageProcessor> map){
        this.dispatchMap = map;
    }

    public void connectionLost(Throwable throwable) {
        log.error("Connection to MQTT broker lost");
        log.error(String.valueOf(throwable.getCause()));
        System.out.println(Arrays.toString(throwable.getStackTrace()));
    }


    public void deliveryComplete(IMqttDeliveryToken token) {
        //wird nicht aufgerufen da subscriber keine Nachrichten versendet
        log.info("Delivery with tokenMessageId:{} complete", token.getMessageId());
    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        log.info("Message received:\t" + new String(mqttMessage.getPayload()));
        dispatchMap.get(s).processMessage(s, mqttMessage);
    }
}
