package com.pangpang6.books.binlog;

import java.util.Map;

public class BinlogItemEntity {
    private String eventType;
    private Map<String, String> preValueMap;
    private Map<String, String> curValueMap;
    private Map<String, String> afterValueMap;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Map<String, String> getPreValueMap() {
        return preValueMap;
    }

    public void setPreValueMap(Map<String, String> preValueMap) {
        this.preValueMap = preValueMap;
    }

    public Map<String, String> getCurValueMap() {
        return curValueMap;
    }

    public void setCurValueMap(Map<String, String> curValueMap) {
        this.curValueMap = curValueMap;
    }

    public Map<String, String> getAfterValueMap() {
        return afterValueMap;
    }

    public void setAfterValueMap(Map<String, String> afterValueMap) {
        this.afterValueMap = afterValueMap;
    }
}
