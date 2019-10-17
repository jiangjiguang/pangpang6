package com.pangpang6.books.binlog;

import java.util.List;
import java.util.Set;

public class BinlogEntity {
    private String tableName;
    private Set<String> eventTypes;
    private List<String> primaryKeys;
    private List<BinlogItemEntity> itemEntityList;

    public BinlogEntity() {
    }

    public BinlogEntity(String tableName, Set<String> eventTypes, List<String> primaryKeys, List<BinlogItemEntity> itemEntityList) {
        this.tableName = tableName;
        this.eventTypes = eventTypes;
        this.primaryKeys = primaryKeys;
        this.itemEntityList = itemEntityList;
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

    public List<String> getPrimaryKeys() {
        return primaryKeys;
    }

    public void setPrimaryKeys(List<String> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public List<BinlogItemEntity> getItemEntityList() {
        return itemEntityList;
    }

    public void setItemEntityList(List<BinlogItemEntity> itemEntityList) {
        this.itemEntityList = itemEntityList;
    }
}
