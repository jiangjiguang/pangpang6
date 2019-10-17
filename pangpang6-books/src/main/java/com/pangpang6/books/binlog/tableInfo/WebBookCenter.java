package com.pangpang6.books.binlog.tableInfo;

import com.google.common.collect.Sets;
import com.pangpang6.books.binlog.BinlogParamEntity;

import java.util.HashMap;
import java.util.Map;

public class WebBookCenter {
    private static final Map<String, BinlogParamEntity> tableInfoMap = new HashMap<String, BinlogParamEntity>() {{
        put("dbName",
                new BinlogParamEntity("tableName", Sets.newHashSet("type"),
                        Sets.newHashSet("id"))
        );
    }};

    public static Map<String, BinlogParamEntity> getTableInfoMap() {
        return tableInfoMap;
    }
}
