package com.pangpang6.books.asyn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 实现自己的拒绝策略
 */
public class MyRejectedExecution implements RejectedExecutionHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyRejectedExecution.class);

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        String errorMsg = String.format("Task %s rejected from %s",
                r == null ? "" : r.toString(), executor.toString());
        logger.warn("rejectedExecution: {}", errorMsg);
        throw new RejectedExecutionException(errorMsg);
    }
}
