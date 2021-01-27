package com.pangpang6.utils;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MetricsTest {

    @Test
    public void patternTest() {
        String patternStr = "\\Qmetrics.reporter.\\E([\\S&&[^.]]*)\\.(\\Qclass\\E|\\Qfactory.class\\E)";
        Pattern pattern = Pattern.compile(patternStr);
        System.out.println(pattern.pattern());
        Matcher matcher = pattern.matcher("metrics.reporter.prom&aa.class");
        if (matcher.matches()) {
            String reporterName = matcher.group(1);
            System.out.println(reporterName);
        }
    }
}
