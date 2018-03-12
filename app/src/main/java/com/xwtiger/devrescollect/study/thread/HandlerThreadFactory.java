package com.xwtiger.devrescollect.study.thread;

import android.support.annotation.NonNull;

import java.util.concurrent.ThreadFactory;

/**
 * Created by xwadmin on 2018/3/6.
 */

public class HandlerThreadFactory implements ThreadFactory{
    @Override
    public Thread newThread(@NonNull Runnable r) {
        System.out.println(this+"create new thread");
        Thread t = new Thread(r);
        System.out.println("create :"+t);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("eh = "+t.getUncaughtExceptionHandler());
        return t;
    }
}
