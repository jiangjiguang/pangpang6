package com.pangpang6.books.binlog;

import java.util.ArrayList;
import java.util.List;

public class BinlogEntry {
    private String binlogFileName;
    private long binlogOffset;
    private long executeTime;
    private String tableName;
    private String eventType;
    private List<String> primaryKeys;
    private List<BinlogRow> rowDatas = new ArrayList();

    public String getBinlogFileName() {
        return binlogFileName;
    }

    public void setBinlogFileName(String binlogFileName) {
        this.binlogFileName = binlogFileName;
    }

    public long getBinlogOffset() {
        return binlogOffset;
    }

    public void setBinlogOffset(long binlogOffset) {
        this.binlogOffset = binlogOffset;
    }

    public long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(long executeTime) {
        this.executeTime = executeTime;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public List<String> getPrimaryKeys() {
        return primaryKeys;
    }

    public void setPrimaryKeys(List<String> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public List<BinlogRow> getRowDatas() {
        return rowDatas;
    }

    public void setRowDatas(List<BinlogRow> rowDatas) {
        this.rowDatas = rowDatas;
    }
}
