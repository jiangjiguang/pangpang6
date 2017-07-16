package com.pangpang6.books.pattern.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by root on 17-7-9.
 */
//门面类（核心）
public class Computer {
    public static final Logger logger = LoggerFactory.getLogger(CPU.class);

    private CPU cpu;
    private Disk disk;
    public Computer() {
        cpu = new CPU();
        disk = new Disk();
    }
    public void start()    {
        logger.info("Computer start begin");
        cpu.start();
        disk.start();
        logger.info("Computer start end");
    }

    public void shutDown()    {
        logger.info("Computer shutDown begin");
        cpu.shutDown();
        disk.shutDown();
        logger.info("Computer shutDown end...");
    }
}
