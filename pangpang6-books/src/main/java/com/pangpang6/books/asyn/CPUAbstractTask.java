package com.pangpang6.books.asyn;



//CPU类任务
public abstract class CPUAbstractTask extends AbstractTask {
    public CPUAbstractTask() {
        this.threadTypeEnum = ThreadTypeEnum.CPU_TYPE;
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    abstract void prepare();

    @Override
    abstract void execute();

    @Override
    abstract void parseResult();
}
