package com.pangpang6.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 事件类型
 * Created by jiangjg on 2017/8/16.
 */
public enum WorkSheetType {

    TransAudit(0, "交易审核"), BlackList(1, "黑名单"), CustLock(2, "账户工单审核"), OrderAuth(
            3, "订单授权申请"), CustComplaint(4, "客户投诉"), CaseInvestigation(5, "案件调查"), CreditCheat(
            6, "信用卡欺诈"), PayRisk(7, "支付风险工单"), PhoneConfirm(8, "电核工单"), SecondConfirm(9, "复核工单"), AfterAuth(10, "授权后工单");
    /**
     *
     */
    private Integer index;
    private String name;

    private static Map<Integer, WorkSheetType> valueMap = new HashMap<Integer, WorkSheetType>();

    static {
        for (WorkSheetType item : WorkSheetType.values()) {
            valueMap.put(item.index, item);
        }
    }

    public static WorkSheetType parse(Integer index) {
        return valueMap.get(index);
    }

    private WorkSheetType(int index, String name) {
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
