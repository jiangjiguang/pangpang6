package com.pangpang6.utils.config;

/**
 * Created by jiangjg on 2017/6/7.
 */
public interface ConfigRegistry {
    /**
     * Register the config factory for the namespace specified.
     *
     * @param namespace the namespace
     * @param factory   the factory for this namespace
     */
    public void register(String namespace, ConfigFactory factory);

    /**
     * Get the registered config factory for the namespace.
     *
     * @param namespace the namespace
     * @return the factory registered for this namespace
     */
    public ConfigFactory getFactory(String namespace);
}
