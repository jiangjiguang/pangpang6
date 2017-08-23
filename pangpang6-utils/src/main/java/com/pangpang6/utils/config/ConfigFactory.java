package com.pangpang6.utils.config;

/**
 * Created by jiangjg on 2017/6/7.
 */
public interface ConfigFactory {
    /**
     * Create the config instance for the namespace.
     *
     * @param namespace the namespace
     * @return the newly created config instance
     */
    public Config create(String namespace);

    /**
     * Create the config file instance for the namespace
     * @param namespace the namespace
     * @return the newly created config file instance
     */
    public ConfigFile createConfigFile(String namespace, ConfigFileFormat configFileFormat);
}
