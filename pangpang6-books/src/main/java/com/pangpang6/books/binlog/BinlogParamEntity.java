package com.pangpang6.books.binlog;

import java.util.Set;

public class BinlogParamEntity {
    private String tableName;
    private Set<String> eventTypes;
    private Set<String> fields;

    public BinlogParamEntity() {
    }

    public BinlogParamEntity(String tableName, Set<String> eventTypes, Set<String> fields) {
        this.tableName = tableName;
        this.eventTypes = eventTypes;
        this.fields = fields;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Set<String> getEventTypes() {
        return eventTypes;
    }

    public void setEventTypes(Set<String> eventTypes) {
        this.eventTypes = eventTypes;
    }

    public Set<String> getFields() {
        return fields;
    }

    public void setFields(Set<String> fields) {
        this.fields = fields;
    }
}
