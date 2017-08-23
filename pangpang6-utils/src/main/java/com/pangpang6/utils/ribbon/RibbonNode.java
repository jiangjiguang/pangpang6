package com.pangpang6.utils.ribbon;

/**
 * Created by jiangjg on 2017/6/30.
 */
public class RibbonNode {
    private String nodeKey;
    private RibbonNodeStatus nodeStatus = RibbonNodeStatus.ENABLED;
    private Long heartBeatLag;

    public String getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(String nodeKey) {
        this.nodeKey = nodeKey;
    }

    public RibbonNodeStatus getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(RibbonNodeStatus nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    public Long getHeartBeatLag() {
        return heartBeatLag;
    }

    public void setHeartBeatLag(Long heartBeatLag) {
        this.heartBeatLag = heartBeatLag;
    }
}
