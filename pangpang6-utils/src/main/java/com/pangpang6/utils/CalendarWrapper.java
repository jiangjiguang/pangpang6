package com.pangpang6.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jiangjg on 2017/6/9.
 */
public class CalendarWrapper {
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_mm = "yyyy-MM-dd HH:mm";

    private Calendar now;
    private Calendar specific;

    private Calendar firstDayOfspecificMonth;
    private Calendar lastDayOfspecificMonth;

    public Date getFirstDateOfMonth() {

        if(null != firstDayOfspecificMonth){
            return firstDayOfspecificMonth.getTime();
        }

        Calendar first = Calendar.getInstance();
        first.set(Calendar.MILLISECOND, 0);
        first.set(Calendar.SECOND, 0);
        first.set(Calendar.MINUTE, 0);
        first.set(Calendar.HOUR_OF_DAY, 0);

        first.set(Calendar.DAY_OF_MONTH, 1);
        first.set(Calendar.MONTH, specific.get(Calendar.MONTH));
        first.set(Calendar.YEAR, specific.get(Calendar.YEAR));

        firstDayOfspecificMonth = first;

        return first.getTime();
    }

    public Date getLastDateOfMonth() {

        if(null != lastDayOfspecificMonth){
            return lastDayOfspecificMonth.getTime();
        }

        Calendar last = Calendar.getInstance();
        last.setTime(specific.getTime());

        last.add(Calendar.MONTH, 1);
        last.set(Calendar.DAY_OF_MONTH, 1);
        last.add(Calendar.DAY_OF_MONTH, -1);

        last.set(Calendar.HOUR_OF_DAY, 23);
        last.set(Calendar.MINUTE, 59);
        last.set(Calendar.SECOND, 59);
        last.set(Calendar.MILLISECOND, 59);

        lastDayOfspecificMonth = last;

        return last.getTime();
    }

    public int getDayOfMonth() {
        return specific.get(Calendar.DAY_OF_MONTH);
    }

    public int getLastDayOfMonth() {

        getLastDateOfMonth();

        return lastDayOfspecificMonth.get(Calendar.DAY_OF_MONTH);
    }

    public int getFirstDayOfMonth() {

        getFirstDateOfMonth();

        return firstDayOfspecificMonth.get(Calendar.DAY_OF_MONTH);
    }

    public boolean isCurrentHour() {
        return isToday() && (now.get(Calendar.HOUR_OF_DAY) == specific.get(Calendar.HOUR_OF_DAY)) ;
    }

    public boolean isCurrentHour(int hourOfDay) {
        return isToday() && (now.get(Calendar.HOUR_OF_DAY) == hourOfDay);
    }


    public boolean isToday() {
        return (now.get(Calendar.YEAR) == specific.get(Calendar.YEAR))
                && (now.get(Calendar.MONTH) == specific.get(Calendar.MONTH))
                && (now.get(Calendar.DAY_OF_MONTH) == specific.get(Calendar.DAY_OF_MONTH));
    }

    public boolean isToday(int dayOfMonth) {

        return (now.get(Calendar.YEAR) == specific.get(Calendar.YEAR))
                && (now.get(Calendar.MONTH) == specific.get(Calendar.MONTH))
                && (now.get(Calendar.DAY_OF_MONTH) == dayOfMonth);
    }

    public static CalendarWrapper parse(String dateStr, String format) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Calendar specific = null;
        try {
            Date date = dateFormat.parse(dateStr);
            specific = Calendar.getInstance();
            specific.setTime(date);
        } catch (ParseException e) {

        }

        //设置指定时间的日历
        CalendarWrapper wrapper = new CalendarWrapper();
        wrapper.specific = specific;
        //设置当天的日历
        wrapper.now = Calendar.getInstance();

        return wrapper;
    }

    public static CalendarWrapper now() {

        //设置指定时间的日历
        CalendarWrapper wrapper = new CalendarWrapper();
        wrapper.specific = Calendar.getInstance();
        //设置当天的日历
        wrapper.now = wrapper.specific;

        return wrapper;
    }


    public static void main(String[] args) {

        CalendarWrapper calendar = CalendarWrapper.parse("2015-05-20 15:00",CalendarWrapper.YYYY_MM_DD);

        System.out.println(calendar.isToday());
        System.out.println(calendar.isCurrentHour());

        System.out.println(calendar.getFirstDayOfMonth());
        System.out.println(calendar.getLastDayOfMonth());

        System.out.println(calendar.getFirstDateOfMonth());
        System.out.println(calendar.getLastDateOfMonth());

        System.out.println(calendar.getDayOfMonth());

    }
}
