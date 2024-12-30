package com.home.dev.config_server_provider.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.home.dev.config_server_provider.util.Constants.PropertiesConfigs.COLON;
import static com.home.dev.config_server_provider.util.Constants.PropertiesConfigs.COMMA;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Properties {

    private Map<String, String> nameToValue;

    public Map<String, String> getMap(String name) {
        String value = nameToValue.get(name);
        String[] split = value.split(COMMA);
        return Arrays.stream(split).map(s -> s.split(COLON)).collect(Collectors.toMap(s -> s[0], s -> s[1]));
    }

    public String getString(String name) {
        return nameToValue.get(name);
    }

    public Integer getInteger(String name) {
        return Integer.parseInt(nameToValue.get(name));
    }

    public List<String> getList(String name) {
        return Arrays.stream(nameToValue.get(name).split(COMMA)).toList();
    }
}
