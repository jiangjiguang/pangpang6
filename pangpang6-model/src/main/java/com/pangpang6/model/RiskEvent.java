package com.pangpang6.model;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Offline的风险事件报文（消息）
 * Created by jiangjg on 2017/8/16.
 */
public class RiskEvent {
    /**
     * 反欺诈系统的事件ID
     */
    private String eventId;
    /**
     * 反欺诈系统的接入点编号
     */
    private String eventPoint;
    /**
     * 兼容SqlServer的reqId
     */
    private long reqId;
    /**
     * 订单号
     */
    private Long orderId;
    /**
     * 订单类型
     */
    private Integer orderType;
    /**
     * 订单子类型
     */
    private Integer subOrderType;
    /**
     * 风险事件详情
     */
    private Map<String, Object> eventBody = new HashMap<>();
    /**
     * 结果分值
     */
    private int riskLevel;
    /**
     * offline配置
     */
    private Map<String, Object> configs = new HashMap<>();
    /**
     * 需要执行的后处理动作（发邮件、冻结账户等操作）
     */
    private List<RulePostAction> postActions = Lists.newArrayList();

    @Override
    public String toString() {
        return "RiskEvent [eventId=" + eventId + ", eventPoint=" + eventPoint
                + ", reqId=" + reqId + ", orderId=" + orderId
                + ", orderType=" + orderType + ", subOrderType=" + subOrderType
                + ", eventBody=" + eventBody + ", riskLevel=" + riskLevel
                + ", postActions=" + postActions + ", configs=" + configs+ "]";
    }

    public String getEventPoint() {
        return eventPoint;
    }

    public void setEventPoint(String eventPoint) {
        this.eventPoint = eventPoint;
    }

    public long getReqId() {
        return reqId;
    }

    public void setReqId(long reqId) {
        this.reqId = reqId;
    }

    public Map<String, Object> getEventBody() {
        return eventBody;
    }

    public void setEventBody(Map<String, Object> eventBody) {
        this.eventBody = eventBody;
    }

    public int getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(int riskLevel) {
        this.riskLevel = riskLevel;
    }

    public List<RulePostAction> getPostActions() {
        return postActions;
    }

    public void setPostActions(List<RulePostAction> postActions) {
        this.postActions = postActions;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getSubOrderType() {
        return subOrderType;
    }

    public void setSubOrderType(Integer subOrderType) {
        this.subOrderType = subOrderType;
    }

    public Map<String, Object> getConfigs() {
        return configs;
    }

    public void setConfigs(Map<String, Object> configs) {
        this.configs = configs;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
