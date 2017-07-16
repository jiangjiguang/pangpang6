package com.pangpang6.books.pattern.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by root on 17-7-9.
 */
public class Cilent {
    public static final Logger logger = LoggerFactory.getLogger(CPU.class);

    public static void main(String[] args)
    {
        Computer computer = new Computer();
        computer.start();
        logger.info("=================");
        computer.shutDown();
    }
}
