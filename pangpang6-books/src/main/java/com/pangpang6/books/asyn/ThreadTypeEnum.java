package com.pangpang6.books.asyn;

public enum ThreadTypeEnum {
    CPU_TYPE(1, "CPU密集型"),
    IO_TYPE(2, "IO密集型");

    private int code;
    private String desc;

    ThreadTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ThreadTypeEnum parse(int code) {
        for (ThreadTypeEnum type : ThreadTypeEnum.values()) {
            if (type.code == code) {
                return type;
            }
        }
        return null;
    }
}
