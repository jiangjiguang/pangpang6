package com.pangpang6.books.pattern.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by root on 17-7-9.
 */
public class CPU {

    public static final Logger logger = LoggerFactory.getLogger(CPU.class);
    public void start() {
        logger.info("cpu is start...");
    }

    public void shutDown() {
        logger.info("CPU is shutDown...");
    }
}
