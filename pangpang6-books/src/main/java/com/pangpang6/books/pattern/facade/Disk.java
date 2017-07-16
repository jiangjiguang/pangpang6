package com.pangpang6.books.pattern.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by root on 17-7-9.
 */
public class Disk {
    public static final Logger logger = LoggerFactory.getLogger(Disk.class);
    public void start() {
        logger.info("Disk is start...");
    }

    public void shutDown() {
        logger.info("Disk is shutDown...");
    }
}
