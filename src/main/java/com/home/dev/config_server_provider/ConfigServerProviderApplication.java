package com.home.dev.config_server_provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConfigServerProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerProviderApplication.class, args);
	}

}
