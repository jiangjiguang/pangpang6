package com.pangpang6.utils.config;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

/**
 * Created by jiangjg on 2017/6/7.
 */
public class ApolloConfigAdapter  implements Config {
    private static final Logger logger = LoggerFactory.getLogger(ApolloConfigAdapter.class);

    private Config config = ConfigService.getAppConfig();

    @Override
    public String getProperty(String key, String defaultValue) {
        return config.getProperty(key, defaultValue);
    }

    @Override
    public Integer getIntProperty(String key, Integer defaultValue) {
        return config.getIntProperty(key, defaultValue);
    }

    @Override
    public Long getLongProperty(String key, Long defaultValue) {
        return config.getLongProperty(key, defaultValue);
    }

    @Override
    public Short getShortProperty(String key, Short defaultValue) {
        return config.getShortProperty(key, defaultValue);
    }

    @Override
    public Float getFloatProperty(String key, Float defaultValue) {
        return config.getFloatProperty(key, defaultValue);
    }

    @Override
    public Double getDoubleProperty(String key, Double defaultValue) {
        return config.getDoubleProperty(key, defaultValue);
    }

    @Override
    public Byte getByteProperty(String key, Byte defaultValue) {
        return config.getByteProperty(key, defaultValue);
    }

    @Override
    public Boolean getBooleanProperty(String key, Boolean defaultValue) {
        return config.getBooleanProperty(key, defaultValue);
    }

    @Override
    public String[] getArrayProperty(String key, String delimiter, String[] defaultValue) {
        return config.getArrayProperty(key, delimiter, defaultValue);
    }


    public BigDecimal getBigDecimalProperty(String key, BigDecimal defaultValue) {
        String property = config.getProperty(key, null);
        if (property == null) {
            return null;
        } else {
            try {
                return NumberUtils.createBigDecimal(property);
            } catch (Exception e) {
                logger.warn("can not convert property{{} : {}} to big decimal", key, property, e);
                return defaultValue;
            }
        }
    }


    public BigInteger getBigIntegerProperty(String key, BigInteger defaultValue) {
        String property = config.getProperty(key, null);
        if (property == null) {
            return null;
        } else {
            try {
                return NumberUtils.createBigInteger(property);
            } catch (Exception e) {
                logger.warn("can not convert property{{} : {}} to big integer", key, property, e);
                return defaultValue;
            }
        }
    }

    @Override
    public Date getDateProperty(String key, Date defaultValue) {
        return config.getDateProperty(key, defaultValue);
    }

    @Override
    public Date getDateProperty(String key, String format, Date defaultValue) {
        return config.getDateProperty(key, format, defaultValue);
    }

    @Override
    public Date getDateProperty(String key, String format, Locale locale, Date defaultValue) {
        return config.getDateProperty(key, format, locale, defaultValue);
    }

    @Override
    public <T extends Enum<T>> T getEnumProperty(String key, Class<T> enumType, T defaultValue) {
        return config.getEnumProperty(key, enumType, defaultValue);
    }

    @Override
    public long getDurationProperty(String key, long defaultValue) {
        return config.getDurationProperty(key, defaultValue);
    }

    public void addChangeListener(ConfigChangeListener listener) {
        config.addChangeListener(listener);
    }

    @Override
    public Set<String> getPropertyNames() {
        return config.getPropertyNames();
    }
}
