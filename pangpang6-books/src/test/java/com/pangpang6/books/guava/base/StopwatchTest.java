package com.pangpang6.books.guava.base;

import com.google.common.base.Stopwatch;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by jiangjiguang on 2017/12/12.
 */
public class StopwatchTest {
    @Test
    public void createStartedTest() throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            TimeUnit.SECONDS.sleep(1);
            int a = 0;
            int b = 2 / a;
        } catch (Exception ex) {
            System.out.println(ExceptionUtils.getFullStackTrace(ex));
        } finally {
            if (stopwatch.isRunning()) {
                stopwatch.stop();
            }
            System.out.println(stopwatch.elapsed(TimeUnit.SECONDS));
        }


    }
}
