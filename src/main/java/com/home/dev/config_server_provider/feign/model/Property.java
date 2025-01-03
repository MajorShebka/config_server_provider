package com.home.dev.config_server_provider.feign.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Property {

    private Long id;
    private String name;
    private String value;
    private String serviceName;
}
