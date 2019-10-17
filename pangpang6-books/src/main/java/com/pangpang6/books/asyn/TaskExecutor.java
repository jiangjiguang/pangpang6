package com.pangpang6.books.asyn;


import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 执行器
 * 管理两种线程池
 * 包含两种任务类型，根据相应的枚举分派到相应的线程池
 */

public class TaskExecutor {
    private static final Logger logger = LoggerFactory.getLogger(TaskExecutor.class);

    private static final int THREAD_SUM = Runtime.getRuntime().availableProcessors();

    private static final int THREAD_SUM_MAX = 20;

    private static final ThreadFactory cpuThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("CPU-thread-%d")
            .build();

    private static final ThreadFactory ioThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("IO-thread-%d")
            .build();

    private static ExecutorService cpuExecutor = null;

    private static ExecutorService ioExecutor = null;

    static {
        logger.info("cpu core: {}", THREAD_SUM);

        try {
            cpuExecutor = new ThreadPoolExecutor(THREAD_SUM, THREAD_SUM, 180, SECONDS,
                    new LinkedBlockingQueue<>(1000),
                    cpuThreadFactory,
                    new MyRejectedExecution());
            logger.info("create cpu thread pool success");
        } catch (RejectedExecutionException ex) {
            throw ex;
        } catch (Exception ex) {
            logger.error("init cpuThreadFactory error {}", ExceptionUtils.getFullStackTrace(ex));
        }
        int corePoolSize = 2 * THREAD_SUM > THREAD_SUM_MAX ? THREAD_SUM_MAX : 2 * THREAD_SUM;
        int maximumPoolSize = 4 * THREAD_SUM > THREAD_SUM_MAX ? THREAD_SUM_MAX : 4 * THREAD_SUM;
        try {
            ioExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 180, SECONDS,
                    new LinkedBlockingQueue<>(5000),
                    ioThreadFactory,
                    new MyRejectedExecution());
            logger.info("create io thread pool success");
        } catch (RejectedExecutionException ex) {
            throw ex;
        } catch (Exception ex) {
            logger.error("init ioExecutor error {}", ExceptionUtils.getFullStackTrace(ex));
        }
    }

    //提交线程池执行任务
    public static boolean execute(AbstractTask task) {
        ThreadTypeEnum threadTypeEnum = task.getThreadTypeEnum();
        if (threadTypeEnum == null) {
            return false;
        }
        ExecutorService executorService = null;
        switch (threadTypeEnum) {
            case CPU_TYPE:
                executorService = cpuExecutor;
                break;
            case IO_TYPE:
                executorService = ioExecutor;
            default:
                break;
        }
        if (executorService == null) {
            return false;
        }

        try {
            executorService.execute(task);
        } catch (Exception ex) {
            logger.warn("execute error {}, {}", JSON.toJSONString(task), ExceptionUtils.getFullStackTrace(ex));
            return false;
        }
        return true;
    }

    private void destroy() {
        this.shutdown(cpuExecutor);
        this.shutdown(ioExecutor);
    }

    private void shutdown(ExecutorService executorService) {
        if (executorService == null) {
            return;
        }
        try {
            executorService.shutdown();
            if (!executorService.awaitTermination(3, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (Exception ex) {
            logger.error("shutdown error {}", ExceptionUtils.getFullStackTrace(ex));
        }
    }
}
