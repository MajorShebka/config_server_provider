package com.home.dev.config_server_provider.service;

import com.home.dev.config_server_provider.feign.message.PropertyResponse;
import com.home.dev.config_server_provider.feign.model.Property;
import com.home.dev.config_server_provider.service.model.Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.home.dev.config_server_provider.util.Constants.PropertiesConfigs.DOT;

@Service
@RequiredArgsConstructor
public class ProviderService {

    private final RestClient restClient;
    @Value("${spring.application.name}")
    private String serviceName;

    public Properties getProperties(String name) {
        String propertyName = serviceName + DOT + name;
        PropertyResponse property = restClient.get().uri(propertyName).retrieve().body(PropertyResponse.class);
        List<Property> properties = property.getProperty();
        Map<String, String> nameToValue = new HashMap<>();
        properties.forEach(p -> {
            nameToValue.put(p.getName(), p.getValue());
        });
        return new Properties(propertyName, nameToValue);
    }
}
