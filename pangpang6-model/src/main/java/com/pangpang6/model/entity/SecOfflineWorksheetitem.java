package com.pangpang6.model.entity;

import java.sql.Timestamp;

/**
 * Created by jiangjg on 2017/8/16.
 */
public class SecOfflineWorksheetitem {
    private Long wItemId;
    private Long workId;
    private String keyString;
    private String keyValue;
    private Timestamp dataChange_LastTime;
    private String keyPath;
    private String srcKeyField;

    @Override
    public String toString() {
        return "SecOfflineWorksheetitem [wItemId=" + wItemId + ", workId="
                + workId + ", keyString=" + keyString + ", keyValue="
                + keyValue + ", dataChange_LastTime=" + dataChange_LastTime
                + ", keyPath=" + keyPath + ", srcKeyField=" + srcKeyField + "]";
    }

    public Long getWItemId() {
        return wItemId;
    }

    public void setWItemId(Long wItemId) {
        this.wItemId = wItemId;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public String getKeyString() {
        return keyString;
    }

    public void setKeyString(String keyString) {
        this.keyString = keyString;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public Timestamp getDataChange_LastTime() {
        return dataChange_LastTime;
    }

    public void setDataChange_LastTime(Timestamp dataChange_LastTime) {
        this.dataChange_LastTime = dataChange_LastTime;
    }

    public String getKeyPath() {
        return keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }

    public String getSrcKeyField() {
        return srcKeyField;
    }

    public void setSrcKeyField(String srcKeyField) {
        this.srcKeyField = srcKeyField;
    }
}
