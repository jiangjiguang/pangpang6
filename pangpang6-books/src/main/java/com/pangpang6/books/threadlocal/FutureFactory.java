package com.pangpang6.books.threadlocal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by jiangjiguang on 2017/11/21.
 */
//应用：pegion有同步调用和异步调用，当异步调用时，会自动把Future放到ThreadLocal中，好处就是同时调用多个接口
public class FutureFactory {
    private static Logger log = LoggerFactory.getLogger(FutureFactory.class);
    private static ThreadLocal<Future<?>> threadFuture = new ThreadLocal<Future<?>>();

    public static Future<?> getFuture() {
        Future<?> future = threadFuture.get();
        threadFuture.remove();
        return future;
    }

    public static <T> Future<T> getFuture(Class<T> type) {
        Future<T> future = (Future<T>) threadFuture.get();
        threadFuture.remove();
        return future;
    }

    public static void setFuture(Future<?> future) {
        threadFuture.set(future);
    }

    public static void remove() {
        threadFuture.remove();
    }

    /**
     * 直接返回调用结果，用于异步调用配置情况下的同步调用
     *
     * @param <T>
     *            返回值类型
     * @param res
     *            返回值类
     * @return 调用结果
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static <T> T getResult(Class<T> res) throws InterruptedException, ExecutionException {
        return (T) getFuture().get();
    }

    /**
     * 直接返回调用结果，用于异步调用配置情况下的同步调用
     *
     * @return 调用结果
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static Object getResult() throws InterruptedException, ExecutionException {
        return getFuture().get();
    }

}
