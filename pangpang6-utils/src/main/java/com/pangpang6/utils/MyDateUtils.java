package com.pangpang6.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by jiangjg on 2017/6/5.
 */
public class MyDateUtils {
    public static void main(String[] args) {
        String t1 = "2017-6-05 9:9:53";
        String t2 = "2017-6-05 9:19:53";
        String t3 = "2017-06-05'T'15:23:44";
        System.out.println(date2StrDefult(str2Date(t1)));
        System.out.println(date2StrDefult(str2Date(t2)));
        System.out.println(date2StrDefult(str2Date(t3)));

        String ss = date2Str(new Date(), "yyyy-MM-dd'T'HH:mm:ss");
        System.out.println(ss);
        String t4 = "2017-06-05 15:23:44.222";
        System.out.println(preProcess(t4));

        Date dateTmp = MyDateUtils.str2Date(MyDateUtils.preProcess(t4));
        String aa = MyDateUtils.date2Str(dateTmp, "yyyy-MM-dd'T'HH:mm:ss");
        System.out.println(aa);
    }

    private static FastDateFormat fastDateFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    private static final String[] STANDARM_DATE_FORMAT = { "yyyy-MM-dd", "yyyy/MM/dd",
            "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'hh:mm:ss",
            "yyyy/MM/dd'T'HH:mm:ss","yyyy/MM/dd'T'hh:mm:ss",
            "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd hh:mm:ss",
            "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd hh:mm:ss",
            "yyyy-MM-dd'T'HH:mm:ss+08:00", "yyyy-MM-dd'T'hh:mm:ss+08:00",
            "yyyy/MM/dd'T'HH:mm:ss+08:00", "yyyy/MM/dd'T'hh:mm:ss+08:00",
            "yyyyMMddHHmmssSSS"};
    public static Date str2Date(String string){
        if(StringUtils.isBlank(string)){
            return null;
        }
        Date date;
        try{
            date = DateUtils.parseDate(string, STANDARM_DATE_FORMAT);
        }catch (Exception ex){
            return null;
        }
        return date;
    }

    public static String date2StrDefult(Date date){
        try{
            return fastDateFormat.format(date);
        }catch (Exception ex){
            return null;
        }

    }

    public static String date2Str(Date date, String pattern){
        FastDateFormat fastDateFormat = FastDateFormat.getInstance(pattern);
        try{
            return fastDateFormat.format(date);
        }catch (Exception ex){
            return null;
        }
    }

    public static String preProcess(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        int index = StringUtils.indexOf(str, ".");
        if(index >= 0){
            str = StringUtils.substring(str, 0, index);
        }
        str = StringUtils.replace(str, "'", "");
        return str;
    }

    public static boolean isSameYear(Calendar cal1,Calendar cal2){
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR));
    }

    public static boolean isSameYear(Date date1,Date date2){
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameYear(cal1, cal2);
    }
    /**
     * Checks if two date objects are on the same day and hour.
     * @param cal1
     * @param cal2
     * @return
     */
    public static boolean isSameHour(Calendar cal1,Calendar cal2){
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return DateUtils.isSameDay(cal1, cal2) &&
                cal1.get(Calendar.HOUR_OF_DAY) == cal2.get(Calendar.HOUR_OF_DAY);
    }

    public static boolean isSameMonth(Date date1,Date date2){
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameMonth(cal1, cal2);
    }

    public static boolean isSameMonth(Calendar cal1,Calendar cal2){
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
        );
    }


    /**
     * 判断是否是属于同一时间区域
     * @param dateDistinct
     * @param dt1
     * @param dt2
     * @return
     */
    public static boolean isSameTimeZone(DateCalendarType dateDistinct, Date dt1, Date dt2) {

        if (DateCalendarType.y == dateDistinct) {
            return isSameYear(dt1,dt2);
        } else if (DateCalendarType.M == dateDistinct) {
            return isSameMonth(dt1, dt2);
        } else if (DateCalendarType.d == dateDistinct) {
            return DateUtils.isSameDay(dt1, dt2);
        } else if (DateCalendarType.h == dateDistinct) {
            return isSameHour(dt1, dt2);
        }
        return false;
    }

    public static boolean isSameHour(Date date1,Date date2){
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameHour(cal1, cal2);
    }
}
