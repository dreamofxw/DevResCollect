package com.xwtiger.devrescollect.study.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xwadmin on 2018/3/6.
 */

public class EvenChecker implements Runnable {
    
    private IntGeenerator intGeenerator;
    private final int id;
    public EvenChecker(IntGeenerator g,int ident){
        intGeenerator = g;
        id = ident;
    }
    
    @Override
    public void run() {
        if(!intGeenerator.isCanceled()){
            System.out.println("threadname"+Thread.currentThread().getName());
        }
        while (!intGeenerator.isCanceled()){
            int val = intGeenerator.next();
            System.out.println("val:"+val+"threadname:"+Thread.currentThread().getName());
            if(val % 2 !=0){
                System.out.println(val+"not event");
                intGeenerator.cancel();
            }
        }
    }
    
    public static void test(IntGeenerator gp,int counts){
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0;i<counts;i++){
            executorService.execute(new EvenChecker(gp,i));
        }
        executorService.shutdown();
    }
    
    public static void test(IntGeenerator gp){
        test(gp,2);
    }
}
