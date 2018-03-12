package com.xwtiger.devrescollect.study.thread;

/**
 * Created by xwadmin on 2018/3/6.
 */

public class ExceptionThread2 implements Runnable {
    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run by"+t);
        System.out.println("eh = "+t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}
