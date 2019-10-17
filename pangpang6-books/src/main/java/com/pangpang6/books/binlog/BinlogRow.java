package com.pangpang6.books.binlog;

import java.util.HashMap;
import java.util.Map;

public class BinlogRow {
    public static final String EVENT_TYPE_INSERT = "INSERT";
    public static final String EVENT_TYPE_UPDATE = "UPDATE";
    public static final String EVENT_TYPE_DELETE = "DELETE";
    private String eventType;
    private Map<String, BinlogColumn> beforeColumns;
    private Map<String, BinlogColumn> afterColumns;

    public BinlogRow(String eventType) {
        this.eventType = eventType;
        this.beforeColumns = new HashMap();
        this.afterColumns = new HashMap();
    }

    public static String getEventTypeInsert() {
        return EVENT_TYPE_INSERT;
    }

    public static String getEventTypeUpdate() {
        return EVENT_TYPE_UPDATE;
    }

    public static String getEventTypeDelete() {
        return EVENT_TYPE_DELETE;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Map<String, BinlogColumn> getBeforeColumns() {
        return beforeColumns;
    }

    public void setBeforeColumns(Map<String, BinlogColumn> beforeColumns) {
        this.beforeColumns = beforeColumns;
    }

    public Map<String, BinlogColumn> getAfterColumns() {
        return afterColumns;
    }

    public void setAfterColumns(Map<String, BinlogColumn> afterColumns) {
        this.afterColumns = afterColumns;
    }

    public Map<String, BinlogColumn> getCurColumns() {
        return "DELETE".equals(this.eventType) ? this.beforeColumns : this.afterColumns;
    }

    public BinlogColumn getCurColumn(String columnName) {
        return "DELETE".equals(this.eventType) ? (BinlogColumn)this.beforeColumns.get(columnName) : (BinlogColumn)this.afterColumns.get(columnName);
    }

}
