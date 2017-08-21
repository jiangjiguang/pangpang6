package com.pangpang6.utils.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jiangjg on 2017/7/17.
 */
public class Execs {
    private static ThreadFactory makeThreadFactory(String nameFormat) {
        return new ThreadFactoryBuilder().setDaemon(false).setNameFormat(nameFormat).build();
    }

    private static ThreadFactory makeThreadFactory() {
        return new ThreadFactoryBuilder().setDaemon(false).setThreadFactory(new InnerThreadFactory()).build();
    }
    public static ExecutorService multiThreaded(int threads, String nameFormat) {
        return Executors.newFixedThreadPool(threads,
                makeThreadFactory(nameFormat));
    }
    public static ExecutorService multiThreaded(int threads) {
        return Executors.newFixedThreadPool(threads,
                makeThreadFactory());
    }
    public static ScheduledExecutorService multiShceduledThreaded(int threads){
        return Executors.newScheduledThreadPool(threads, makeThreadFactory());
    }

    public static ExecutorService multiCachedThreaded(){
        return Executors.newCachedThreadPool(makeThreadFactory());
    }
    public static ExecutorService multiCachedThreaded(int threads){
        return new ThreadPoolExecutor(0,threads,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                makeThreadFactory());
    }
    public static ExecutorService multiCachedThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, String nameFormat,RejectedExecutionHandler handler){
        return new ThreadPoolExecutor(corePoolSize,maximumPoolSize,
                keepAliveTime, unit,
                new SynchronousQueue<Runnable>(),
                makeThreadFactory(nameFormat),handler);
    }

    public static ExecutorService newSingleThreadExecutor(String nameFormat){
        return Executors.newSingleThreadExecutor(makeThreadFactory(nameFormat));
    }


    private static class InnerThreadFactory implements ThreadFactory{

        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private static final String EMPTY_NAME = "";
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        InnerThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pangpang6-" + poolNumber.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable r) {
            String threadName=namePrefix;
            if(r instanceof NamedRunnable){
                String name=((NamedRunnable)r).name();
                if(name!=null && name!=EMPTY_NAME){
                    threadName="pangpang6-" +poolNumber.getAndIncrement() +"-"+name+ "-thread-" + threadNumber.getAndIncrement();
                }
            }
            if(threadName == null){
                threadName = EMPTY_NAME;
            }
            Thread t = new Thread(group, r,threadName, 0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }

    }

    public static Builder newBuilder() {
        return new Builder();
    }
    public static class NamedThreadFactory{

    }
    public static class Builder {
        private int corePoolSize;
        private int maximumPoolSize;
        private int keepAliveTime;
        private String threadNameFormat;
        private BlockingQueue<Runnable> queue;

        private Builder() {
            queue = new SynchronousQueue<>();
        };

        public Builder witchCorePoolSize(int size) {
            this.corePoolSize = size;
            return this;
        }

        public Builder withMaximumPoolSize(int size) {
            this.maximumPoolSize = size;
            return this;
        }

        public Builder withKeepAliveTime(int sec) {
            this.keepAliveTime = sec;
            return this;
        }

        public Builder withThreadNameFormat(String nameFormat) {
            this.threadNameFormat = nameFormat;
            return this;
        }

        public ExecutorService build() {
            return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, queue, makeThreadFactory(threadNameFormat));
        }
    }

    public interface NamedRunnable extends Runnable{
        String name();
    }
}
