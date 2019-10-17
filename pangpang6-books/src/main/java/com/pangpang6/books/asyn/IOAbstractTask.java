package com.pangpang6.books.asyn;



//IO类任务
public abstract class IOAbstractTask extends AbstractTask {
    public IOAbstractTask() {
        this.threadTypeEnum = ThreadTypeEnum.IO_TYPE;
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public void prepare() {
        return;
    }

    @Override
    public abstract void execute();

    @Override
    public void parseResult() {
        return;
    }
}
