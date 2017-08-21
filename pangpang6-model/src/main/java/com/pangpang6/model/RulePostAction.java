package com.pangpang6.model;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 规则的后处理动作（发邮件、冻结账户等操作）
 * Created by jiangjg on 2017/8/16.
 */
public class RulePostAction {
    /**
     * Offline支持的动作NAME，需要预先在Offline系统里配置或开发
     */
    private String actionName;
    /**
     * 动作执行需要的参数
     */
    private Map<String, Object> actionParams = Maps.newHashMap();

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Map<String, Object> getActionParams() {
        return actionParams;
    }

    public void setActionParams(Map<String, Object> actionParams) {
        this.actionParams = actionParams;
    }
}
