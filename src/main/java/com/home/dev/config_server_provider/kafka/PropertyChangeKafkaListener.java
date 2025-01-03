package com.home.dev.config_server_provider.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.dev.config_server_provider.feign.model.Property;
import com.home.dev.config_server_provider.service.model.Properties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.List;

@RequiredArgsConstructor
public class PropertyChangeKafkaListener {

    private final List<Properties> properties;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @KafkaListener(topics = "${config.server.kafka.topic}", groupId = "group1")
    public void listen(@Payload String data) {
        Property property = objectMapper.readValue(data, Property.class);
        properties.stream()
                  .filter(p -> p.getServiceName().equals(property.getServiceName()))
                  .map(Properties::getNameToValue)
                  .forEach(nameToValue -> nameToValue.put(property.getName(), property.getValue()));
    }
}
