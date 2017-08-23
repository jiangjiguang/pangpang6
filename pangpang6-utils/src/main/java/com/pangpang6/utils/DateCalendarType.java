package com.pangpang6.utils;

import java.util.Calendar;

/**
 * Created by jiangjg on 2017/7/11.
 */
public enum DateCalendarType {
    y(Calendar.YEAR, "y"),
    M(Calendar.MONTH,"M"),
    d(Calendar.DAY_OF_YEAR,"d"),
    h(Calendar.HOUR_OF_DAY,"h");


    private final int code;
    private final String message;

    DateCalendarType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
