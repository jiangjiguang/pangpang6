package com.pangpang6.books.asyn;



//任务的抽象类
public abstract class AbstractTask implements Runnable {
    //任务的名字
    protected String taskName;
    //任务类型
    protected ThreadTypeEnum threadTypeEnum;

    //模板方法
    public void run() {
        this.prepare();
        this.execute();
        this.parseResult();
    }

    abstract void prepare();

    abstract void execute();

    abstract void parseResult();

    public ThreadTypeEnum getThreadTypeEnum() {
        return threadTypeEnum;
    }

    public String getTaskName() {
        return taskName;
    }
}
