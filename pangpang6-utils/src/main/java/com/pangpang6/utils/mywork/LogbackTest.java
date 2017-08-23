package com.pangpang6.utils.mywork;

/**
 * Created by root on 17-8-22.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class LogbackTest {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(LogbackTest.class);
        logger.error("Hello World", new Exception("111"));
    }
}
