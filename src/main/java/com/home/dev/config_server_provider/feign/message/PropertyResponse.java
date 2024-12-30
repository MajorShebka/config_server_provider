package com.home.dev.config_server_provider.feign.message;

import com.home.dev.config_server_provider.feign.model.Property;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyResponse {

    private List<Property> property;
}
