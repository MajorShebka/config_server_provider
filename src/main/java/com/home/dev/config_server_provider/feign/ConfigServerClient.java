package com.home.dev.config_server_provider.feign;

import com.home.dev.config_server_provider.feign.message.PropertyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;

@FeignClient("configServerClient")
public interface ConfigServerClient {

    @GetMapping
    PropertyResponse getProperty(URI uri, @PathVariable String serviceName);
}
