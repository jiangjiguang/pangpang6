package com.pangpang6.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 事件类型
 * Created by jiangjg on 2017/8/16.
 */
public enum WorkSheetIDType {

    OrderId		(1, "订单号"),
    UID			(2, "UID"),
    CCardNoCode	(3, "卡号"),
    MobilePhone	(4, "手机号"),
    IP			(5, "IP"),
    ContactEmail(6, "联系邮箱"),
    IdCard		(7, "证件号"),
    DID			(8, "DID");
    /**
     *
     */
    private int index;
    private String name;

    private static Map<Integer, WorkSheetIDType> valueMap = new HashMap<Integer, WorkSheetIDType>();

    static {
        for (WorkSheetIDType item : WorkSheetIDType.values()) {
            valueMap.put(item.index, item);
        }
    }

    public static WorkSheetIDType parse(Integer index) {
        return valueMap.get(index);
    }


    private WorkSheetIDType(int index, String name) {
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
