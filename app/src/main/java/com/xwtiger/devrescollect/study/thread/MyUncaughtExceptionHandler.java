package com.xwtiger.devrescollect.study.thread;

/**
 * Created by xwadmin on 2018/3/6.
 */

public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught :"+e);
    }
}
