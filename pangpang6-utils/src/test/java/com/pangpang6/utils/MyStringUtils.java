package com.pangpang6.utils;

import org.junit.Test;

import java.util.regex.Pattern;

public class MyStringUtils {



    @Test
    public void test01() {
        String str = "metrics.reporter.";

        System.out.println(quote(str));

        String ss = Pattern.quote(quote("metrics.reporter.")) +
                        // [\S&&[^.]] = intersection of non-whitespace and non-period character classes
                        "([\\S&&[^.]]*)\\." +
                        '(' + quote("class") + '|' + quote("factory.class") + ')';

        System.out.println(ss);

    }

    public static String quote(String s) {
        int slashEIndex = s.indexOf("\\E");
        if (slashEIndex == -1)
            return "\\Q" + s + "\\E";

        int lenHint = s.length();
        lenHint = (lenHint < Integer.MAX_VALUE - 8 - lenHint) ?
                (lenHint << 1) : (Integer.MAX_VALUE - 8);

        StringBuilder sb = new StringBuilder(lenHint);
        sb.append("\\Q");
        int current = 0;
        do {
            sb.append(s, current, slashEIndex)
                    .append("\\E\\\\E\\Q");
            current = slashEIndex + 2;
        } while ((slashEIndex = s.indexOf("\\E", current)) != -1);

        return sb.append(s, current, s.length())
                .append("\\E")
                .toString();
    }
}
