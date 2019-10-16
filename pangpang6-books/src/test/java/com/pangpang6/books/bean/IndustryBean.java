package com.pangpang6.books.bean;

public class IndustryBean implements Comparable<IndustryBean> {
    private int industry2Id;
    private String industry2Name;

    public IndustryBean() {
    }

    public IndustryBean(int industry2Id, String industry2Name) {
        this.industry2Id = industry2Id;
        this.industry2Name = industry2Name;
    }

    public int getIndustry2Id() {
        return industry2Id;
    }

    public void setIndustry2Id(int industry2Id) {
        this.industry2Id = industry2Id;
    }

    public String getIndustry2Name() {
        return industry2Name;
    }

    public void setIndustry2Name(String industry2Name) {
        this.industry2Name = industry2Name;
    }

    @Override
    public int compareTo(IndustryBean o) {
        if (this.industry2Id < o.industry2Id) {
            return -1;
        }
        if (this.industry2Id > o.industry2Id) {
            return 1;
        }
        return 0;
    }
}
