package com.pangpang6.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 工作单状态
 * Created by jiangjg on 2017/8/16.
 */
public enum WorkSheetStatus {
    Started(0, "待处理"), Closed(1, "已关闭"), Processing(2, "处理中"), Other(3, "其他");
    /**
     *
     */
    private int index;
    private String name;

    private static Map<Integer, WorkSheetStatus> valueMap = new HashMap<Integer, WorkSheetStatus>();

    static {
        for (WorkSheetStatus item : WorkSheetStatus.values()) {
            valueMap.put(item.index, item);
        }
    }

    public static WorkSheetStatus parse(Integer index) {
        return valueMap.get(index);
    }

    private WorkSheetStatus(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
}
