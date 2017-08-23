package com.pangpang6.utils.config;

import org.apache.commons.collections.SetUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

/**
 * Created by jiangjg on 2017/6/7.
 */
public class NoConfig implements Config {
    @Override
    public String getProperty(String key, String defaultValue) {
        return defaultValue;
    }

    @Override
    public Integer getIntProperty(String key, Integer defaultValue) {
        return defaultValue;
    }

    @Override
    public Long getLongProperty(String key, Long defaultValue) {
        return defaultValue;
    }

    @Override
    public Short getShortProperty(String key, Short defaultValue) {
        return defaultValue;
    }

    @Override
    public Float getFloatProperty(String key, Float defaultValue) {
        return defaultValue;
    }

    @Override
    public Double getDoubleProperty(String key, Double defaultValue) {
        return defaultValue;
    }

    @Override
    public Byte getByteProperty(String key, Byte defaultValue) {
        return defaultValue;
    }

    @Override
    public Boolean getBooleanProperty(String key, Boolean defaultValue) {
        return defaultValue;
    }

    @Override
    public String[] getArrayProperty(String key, String delimiter, String[] defaultValue) {
        return defaultValue;
    }

    @Override
    public Date getDateProperty(String key, Date defaultValue) {
        return defaultValue;
    }

    @Override
    public Date getDateProperty(String key, String format, Date defaultValue) {
        return defaultValue;
    }

    @Override
    public Date getDateProperty(String key, String format, Locale locale, Date defaultValue) {
        return defaultValue;
    }

    @Override
    public <T extends Enum<T>> T getEnumProperty(String key, Class<T> enumType, T defaultValue) {
        return defaultValue;
    }

    @Override
    public long getDurationProperty(String key, long defaultValue) {
        return defaultValue;
    }

    @Override
    public void addChangeListener(ConfigChangeListener listener) {

    }

    @Override
    public Set<String> getPropertyNames() {
        return SetUtils.EMPTY_SET;
    }
}
