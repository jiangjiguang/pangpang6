package com.pangpang6.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.util.List;

public class MyFileUtils {

    private static final String ENTRY_LINE = "\r\n";

    public static void writeList(String path, List<String> list, boolean append) {
        FileWriter writer = null;
        try {
            if (append) {
                writer = new FileWriter(path, true);
            } else {
                writer = new FileWriter(path, false);
            }

            for (String item : list) {
                if (StringUtils.isBlank(item)) {
                    continue;
                }
                writer.write(item + ENTRY_LINE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
