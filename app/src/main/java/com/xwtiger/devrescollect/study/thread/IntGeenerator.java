package com.xwtiger.devrescollect.study.thread;

/**
 * Created by xwadmin on 2018/3/6.
 */

public abstract class IntGeenerator {

    private volatile boolean canceled = false;

    public abstract int next();

    public void cancel(){
        this.canceled = true;
    }

    public boolean isCanceled(){
        return canceled;
    }

}
