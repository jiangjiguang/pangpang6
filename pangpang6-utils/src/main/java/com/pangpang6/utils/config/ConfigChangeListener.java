package com.pangpang6.utils.config;

/**
 * Created by jiangjg on 2017/6/7.
 */
public interface ConfigChangeListener {
    /**
     * Invoked when there is any config change for the namespace.
     * @param changeEvent the event for this change
     */
    public void onChange(ConfigChangeEvent changeEvent);
}
