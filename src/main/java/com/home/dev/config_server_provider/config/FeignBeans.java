package com.home.dev.config_server_provider.config;

import feign.Feign;
import org.springframework.context.annotation.Bean;

public class FeignBeans {

    @Bean
    public Feign feignClient() {
        Feign.builder().client()
        return Feign.builder().build();
    }
}
