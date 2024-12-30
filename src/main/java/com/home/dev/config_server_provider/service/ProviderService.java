package com.home.dev.config_server_provider.service;

import com.home.dev.config_server_provider.feign.ConfigServerClient;
import com.home.dev.config_server_provider.feign.message.PropertyResponse;
import com.home.dev.config_server_provider.feign.model.Property;
import com.home.dev.config_server_provider.service.model.Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.home.dev.config_server_provider.util.Constants.PropertiesConfigs.DOT;

@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ConfigServerClient configServerClient;
    @Value("{config.server.url}")
    private String configServerUrl;
    @Value("{config.service.name}")
    private String serviceName;

    public Properties getProperties(String name) {
        URI uri = URI.create(configServerUrl);
        PropertyResponse property = configServerClient.getProperty(uri, serviceName + DOT + name);
        List<Property> properties = property.getProperty();
        Map<String, String> nameToValue = new HashMap<>();
        properties.forEach(p -> {
            nameToValue.put(p.getName(), p.getValue());
        });
        return new Properties(nameToValue);
    }
}
