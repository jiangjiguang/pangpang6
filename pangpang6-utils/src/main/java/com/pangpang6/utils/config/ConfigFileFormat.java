package com.pangpang6.utils.config;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by jiangjg on 2017/6/7.
 */
public enum ConfigFileFormat {
    Properties("properties"), XML("xml"), JSON("json"), YML("yml"), YAML("yaml");

    private String value;

    ConfigFileFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ConfigFileFormat fromString(String value){
        if (StringUtils.isEmpty(value)){
            throw new IllegalArgumentException("value can not be empty");
        }
        switch (value){
            case "properties":
                return Properties;
            case "xml":
                return XML;
            case "json":
                return JSON;
            case "yml":
                return YML;
            case "yaml":
                return YAML;
        }
        throw new IllegalArgumentException(value + " can not map enum");
    }
}
