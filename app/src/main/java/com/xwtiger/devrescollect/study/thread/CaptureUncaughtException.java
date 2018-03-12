package com.xwtiger.devrescollect.study.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xwadmin on 2018/3/6.
 */

public class CaptureUncaughtException {

    public static void main(String[] args){

        ExecutorService executorService = Executors.newCachedThreadPool(new HandlerThreadFactory());
        executorService.execute(new ExceptionThread2());

    }

}
