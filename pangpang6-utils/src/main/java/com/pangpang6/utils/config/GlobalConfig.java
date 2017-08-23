package com.pangpang6.utils.config;

import com.pangpang6.utils.ClassUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.ConfigurationConverter;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.Set;

/**
 * Created by jiangjg on 2017/6/7.
 */
public class GlobalConfig {
    private static Logger logger = LoggerFactory.getLogger(GlobalConfig.class);

    private static final String CONFIG_FILENAME = "/GlobalConfig.properties";
    private static CombinedConfiguration config = new CombinedConfiguration();

    private static final boolean APOLLO_PRESENT = ClassUtils.isPresent("com.pangpang3.framework.apollo.Config", GlobalConfig.class.getClassLoader());
    private static final Config config2;

    static {
        try {
            InputStream in = GlobalConfig.class.getResourceAsStream(CONFIG_FILENAME);
            PropertiesConfiguration configuration = new PropertiesConfiguration();
            // 不自动分割值
            configuration.setDelimiterParsingDisabled(true);
            configuration.load(in);
            config.addConfiguration(configuration);
        } catch (Exception ex) {
            throw new RuntimeException("Load config file[" + CONFIG_FILENAME + "] exception.", ex);
        }
        try {
            if (APOLLO_PRESENT) {
                config2 = new ApolloConfigAdapter();
            } else {
                config2 = new NoConfig();
            }
        } catch (Exception ex) {
            throw new RuntimeException("Initialize apollo config exception.", ex);
        }
    }

    @Deprecated
    public static Properties getProperties() {
        Properties properties = Optional.ofNullable(ConfigurationConverter.getProperties(config)).orElse(new Properties());
        Set<String> config2Names = config2.getPropertyNames();
        if (CollectionUtils.isNotEmpty(config2Names)) {
            for (String name : config2Names) {
                String property = config2.getProperty(name, null);
                if (property != null) {
                    properties.put(name, property);
                }
            }
        }
        return properties;
    }

    public static boolean containsKey(String key) {
        if (config.containsKey(key)) {
            return true;
        } else {
            Set<String> config2Names = config2.getPropertyNames();
            if (CollectionUtils.isNotEmpty(config2Names)) {
                return config2Names.contains(key);
            } else {
                return false;
            }
        }
    }

    public static String getString(String key) {
        return getString(key, null);
    }

    public static String getString(String key, String defaultValue) {
        return config.getString(key, config2.getProperty(key, defaultValue));
    }

    public static Boolean getBoolean(String key) {
        return getBoolean(key, null);
    }

    public static Boolean getBoolean(String key, Boolean defaultValue) {
        return config.getBoolean(key, config2.getBooleanProperty(key, defaultValue));
    }

    public static Byte getByte(String key) {
        return getByte(key, null);
    }

    public static Byte getByte(String key, Byte defaultValue) {
        return config.getByte(key, config2.getByteProperty(key, defaultValue));
    }

    public static Short getShort(String key) {
        return getShort(key, null);
    }

    public static Short getShort(String key, Short defaultValue) {
        return config.getShort(key, config2.getShortProperty(key, defaultValue));
    }

    public static Integer getInt(String key) {
        return getInteger(key, null);
    }

    public static Integer getInteger(String key, Integer defaultValue) {
        return config.getInteger(key, config2.getIntProperty(key, defaultValue));
    }

    public static Long getLong(String key) {
        return config.getLong(key, null);
    }

    public static Long getLong(String key, Long defaultValue) {
        return config.getLong(key, config2.getLongProperty(key, defaultValue));
    }

    public static Float getFloat(String key) {
        return getFloat(key, null);
    }

    public static Float getFloat(String key, Float defaultValue) {
        return config.getFloat(key, config2.getFloatProperty(key, defaultValue));
    }

    public static Double getDouble(String key) {
        return getDouble(key, null);
    }

    public static Double getDouble(String key, Double defaultValue) {
        return config.getDouble(key, config2.getDoubleProperty(key, defaultValue));
    }

    public static BigDecimal getBigDecimal(String key) {
        return getBigDecimal(key);
    }


    /**
     * 使用框架的apollo替代，以后将不提供此方法
     *
     * @param serviceUrl
     * @param env
     * @param appId
     * @param key
     * @return
     */
    @Deprecated
    public static String getString(String serviceUrl, String env, String appId, String key) {
        String url = String.format("%s/%s/%s/%s", serviceUrl, env, appId, key);
        logger.debug("app config url: {}", url);
        try {
            return StringUtils.trimToNull(Request.Get(url).connectTimeout(1000).socketTimeout(5000).execute().returnContent().asString());
        } catch (IOException e) {
            logger.error("retrieve app config failed, url={}", url, e);
            throw new RuntimeException("retrieve app config failed, url=" + url, e);
        }
    }
}
