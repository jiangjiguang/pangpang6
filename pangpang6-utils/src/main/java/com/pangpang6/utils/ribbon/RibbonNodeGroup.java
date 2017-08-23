package com.pangpang6.utils.ribbon;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 服务器分组
 * Created by jiangjg on 2017/6/30.
 */
public enum RibbonNodeGroup {
    PAY("支付"),
    MOD("模型"),
    FLT("机票"),
    MKT("营销"),
    Others("其他");
    /**
     *
     */
    private String label;
    private static Map<String, RibbonNodeGroup> valueMap = Maps.newHashMap();

    static {
        for (RibbonNodeGroup item : RibbonNodeGroup.values()) {
            valueMap.put(item.toString(), item);
        }
    }

    public static RibbonNodeGroup parse(String value) {
        return valueMap.get(value);
    }

    private RibbonNodeGroup(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
