package com.pangpang6.hadoop.hadoop2.tq;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by jiangjiguang on 2018/3/25.
 */
public class Weather implements WritableComparable<Weather> {
    private int year;
    private int month;
    private int day;
    //温度
    private int wd;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWd() {
        return wd;
    }

    public void setWd(int wd) {
        this.wd = wd;
    }

    @Override
    public int compareTo(Weather w) {
        int c1 = Integer.compare(this.year, w.getYear());
        if (c1 != 0) {
            return c1;
        }
        int c2 = Integer.compare(this.month, w.getMonth());
        if (c2 != 0) {
            return c2;
        }
        int c3 = Integer.compare(this.wd, w.getWd());
        if (c3 != 0) {
            return c3;
        }
        return 0;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(year);
        out.writeInt(month);
        out.writeInt(day);
        out.writeInt(wd);

    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.year = in.readInt();
        this.month = in.readInt();
        this.day = in.readInt();
        this.wd = in.readInt();
    }

    @Override
    public String toString() {
        return "Weather{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", wd=" + wd +
                '}';
    }
}
