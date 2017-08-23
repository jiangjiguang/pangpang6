package com.pangpang6.utils.config;

/**
 * Created by jiangjg on 2017/6/7.
 */
public interface ConfigFile {
    /**
     * Get file content of the namespace
     * @return file content, {@code null} if there is no content
     */
    String getContent();

    /**
     * Whether the config file has any content
     * @return true if it has content, false otherwise.
     */
    boolean hasContent();

    /**
     * Get the namespace of this config file instance
     * @return the namespace
     */
    String getNamespace();

    /**
     * Get the file format of this config file instance
     * @return the config file format enum
     */
    ConfigFileFormat getConfigFileFormat();
}
