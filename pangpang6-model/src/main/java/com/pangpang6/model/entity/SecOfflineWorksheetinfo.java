package com.pangpang6.model.entity;

import com.pangpang6.model.Utils;
import com.pangpang6.model.enums.WorkSheetIDType;
import com.pangpang6.model.enums.WorkSheetStatus;
import com.pangpang6.model.enums.WorkSheetType;

import java.sql.Timestamp;

/**
 * Created by jiangjg on 2017/8/16.
 */
public class SecOfflineWorksheetinfo {
    private Long workId;
    private Long processId;
    private Long parentWorkId;
    private Long currentNode;
    private String workName;
    private Integer workType;
    private Integer currentStatus;
    private Integer iDType;
    private String iDValue;
    private Timestamp createDate;
    private Timestamp startDate;
    private String startOper;
    private Timestamp endDate;
    private Integer endNode;
    private String endOper;
    private Integer timeOut;
    private Timestamp dataChange_LastTime;
    private String lastOper;
    private String currentUrl;
    private Timestamp deadlineTime;

    private Integer closeWay;
    private Long closeWorkId;

    @Override
    public String toString() {
        return "SecOfflineWorksheetinfo [workId=" + workId + ", processId="
                + processId + ", parentWorkId=" + parentWorkId
                + ", currentNode=" + currentNode + ", workName=" + workName
                + ", workType=" + workType + ", currentStatus=" + currentStatus
                + ", iDType=" + iDType + ", iDValue=" + iDValue
                + ", createDate=" + createDate + ", startDate=" + startDate
                + ", startOper=" + startOper + ", endDate=" + endDate
                + ", endNode=" + endNode + ", endOper=" + endOper
                + ", timeOut=" + timeOut + ", dataChange_LastTime="
                + dataChange_LastTime + ", lastOper=" + lastOper
                + ", currentUrl=" + currentUrl + ", deadlineTime="
                + deadlineTime + ", closeWay="+closeWay+", closwWorkId="+closeWorkId+"]";
    }

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public Long getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Long currentNode) {
        this.currentNode = currentNode;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public Integer getWorkType() {
        return workType;
    }

    public void setWorkType(Integer workType) {
        this.workType = workType;
    }

    public WorkSheetType getWorkTypeEnum() {
        return WorkSheetType.parse(workType);
    }

    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
    }

    public WorkSheetStatus getCurrentStatusEnum() {
        return WorkSheetStatus.parse(currentStatus);
    }

    public Integer getIDType() {
        return iDType;
    }

    public void setIDType(Integer iDType) {
        this.iDType = iDType;
    }

    public WorkSheetIDType getIDTypeEnum() {
        return WorkSheetIDType.parse(iDType);
    }

    public String getIDValue() {
        return iDValue;
    }

    public void setIDValue(String iDValue) {
        this.iDValue = iDValue;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public String getStartOper() {
        return startOper;
    }

    public void setStartOper(String startOper) {
        this.startOper = startOper;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Integer getEndNode() {
        return endNode;
    }

    public void setEndNode(Integer endNode) {
        this.endNode = endNode;
    }

    public String getEndOper() {
        return endOper;
    }

    public void setEndOper(String endOper) {
        this.endOper = endOper;
    }

    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }

    public Timestamp getDataChange_LastTime() {
        return dataChange_LastTime;
    }

    public void setDataChange_LastTime(Timestamp dataChange_LastTime) {
        this.dataChange_LastTime = dataChange_LastTime;
    }

    public String getLastOper() {
        return lastOper;
    }

    public void setLastOper(String lastOper) {
        this.lastOper = lastOper;
    }

    public String getCurrentUrl() {
        return currentUrl;
    }

    public void setCurrentUrl(String currentUrl) {
        this.currentUrl = currentUrl;
    }

    public String getCreateDateString() {
        return null==createDate ? null : Utils.SDF.format(createDate);
    }

    public String getStartDateString() {
        return null==startDate ? null : Utils.SDF.format(startDate);
    }

    public Long getParentWorkId() {
        return parentWorkId;
    }

    public void setParentWorkId(Long parentWorkId) {
        this.parentWorkId = parentWorkId;
    }

    public Timestamp getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(Timestamp deadlineTime) {
        this.deadlineTime = deadlineTime;
    }

    public String getDeadlineTimeString() {
        return null==deadlineTime ? null : Utils.SDF.format(deadlineTime);
    }

    public String getEndDateString() {
        return null==endDate ? null : Utils.SDF.format(endDate);
    }

    public Integer getCloseWay() {
        return closeWay;
    }

    public void setCloseWay(Integer closeWay) {
        this.closeWay = closeWay;
    }

    public Long getCloseWorkId() {
        return closeWorkId;
    }

    public void setCloseWorkId(Long closeWorkId) {
        this.closeWorkId = closeWorkId;
    }
}
