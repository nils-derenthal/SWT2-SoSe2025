package de.fhdortmund.schrottverwaltung.mqtt;

public interface MqttMessageStrategy<T> {
    Class<T> dtoClass();
    void processMessage(T dto) throws Exception;
}
