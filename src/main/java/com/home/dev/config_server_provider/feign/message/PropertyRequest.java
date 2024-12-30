package com.home.dev.config_server_provider.feign.message;

import com.home.dev.config_server_provider.feign.model.Property;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyRequest {

    private Property property;
}
