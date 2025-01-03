package com.home.dev.config_server_provider.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.dev.config_server_provider.kafka.PropertyChangeKafkaListener;
import com.home.dev.config_server_provider.service.ProviderService;
import com.home.dev.config_server_provider.service.model.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.home.dev.config_server_provider.util.Constants.PropertiesConfigs.SLASH;

@Configuration
@EnableKafka
public class Beans {

    @Bean
    public ProviderService providerService(RestClient restClient) {
        return new ProviderService(restClient);
    }

    @Bean
    public RestClient restClient(@Value("${config.server.url}") String url) {
        if (url.charAt(url.length() - 1) != SLASH) {
            url = url + SLASH;
        }
        return RestClient.create(URI.create(url));
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory(@Value("${config.server.kafka.bootstrapservers}") String bootstrapServers) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public PropertyChangeKafkaListener kafkaListener(List<Properties> properties, ObjectMapper objectMapper) {
        return new PropertyChangeKafkaListener(properties, objectMapper);
    }
}
