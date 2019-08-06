package com.xwtiger.devrescollect.study.javaapi;

import android.support.annotation.NonNull;

import com.xwtiger.devrescollect.MyException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * Created by Busap-112 on 2017/11/6.
 *
 * 关于线程的常用的总结
 *
 *
 * 线程池的原理总结：
 * 触发点：提交任务
 * 1，当前线程小于核心线程 则直接创建线程执行任务
 * 2，大于核心线程，但是队列没有饱和（如果队列设置了大小），则添加到任务队列
 * 3，任务队列已经没有空间，但是线程数小于最大线程数则直接创建线程执行任务
 *
 */

public class ThreadStudy {


    public static void main(String[] args){

        testSynqueue();

    }

    public static BlockingQueue blockingQueue = new LinkedBlockingQueue(4);

    public static ExecutorService executorService = null;
    public static void testSynqueue(){
        //new SynchronousQueue<Runnable>()
        executorService = new ThreadPoolExecutor(1, 10, 60, TimeUnit.SECONDS,
                blockingQueue, new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable r) {
                Thread result = new Thread(r);
                result.setDaemon(false);
                return result;
            }
        });

//        executorService = Executors.newCachedThreadPool();

        for(int i=0;i<10;i++){
            executorService.execute(new TestRunable("test_syn_queue"+i));


        }

        try {
            Thread.sleep(20000);
            for(int j=0;j<10;j++){
//                Field ctl = executorService.getClass().getDeclaredField("ctl");
//                ctl.setAccessible(true);
//                AtomicInteger o = (AtomicInteger) ctl.get(executorService);
//                System.out.println(o.get());
//
//                Method workerCountOf = executorService.getClass().getDeclaredMethod("workerCountOf", int.class);
//                workerCountOf.setAccessible(true);
//                int invoke = (int) workerCountOf.invoke(executorService, o.get());
//                System.out.println("invoke = "+invoke);
//
//                System.out.println("blockingQueue.size ="+blockingQueue.size());


                Field workers = executorService.getClass().getDeclaredField("workers");
                workers.setAccessible(true);
                HashSet hashSet = (HashSet) workers.get(executorService);
                System.out.println("hashsetsize ="+hashSet.size());

                executorService.execute(new TestRunable("test_syn_queue j="+j));
            }

        } catch (InterruptedException e) {
            MyException.printStr(e);
        } catch (Exception e) {
            MyException.printStr(e);
        } 
    }

    final static SynchronousQueue<String> synchronousQueue =  new SynchronousQueue<String>();
    public static void testqueue(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService_out = Executors.newSingleThreadExecutor();


        for(int i=0;i<10;i++){
            executorService_out.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(synchronousQueue.take());
                    } catch (InterruptedException e) {
                        MyException.printStr(e);
                    }
                }
            });
        }



        for(int i=0;i<10;i++){
            System.out.println("i ="+i);
            executorService.execute(new TestRunable("string"+i));

        }

        System.out.println("size ="+synchronousQueue.size());
    }

    public static void testThread1212(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 20, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(12),new ThreadFactory(){
            @Override
            public Thread newThread(@NonNull Runnable r) {
                Thread result = new Thread(r);
                result.setDaemon(false);
                return result;
            }
        });

        for(int i =0;i<30;i++){
            threadPoolExecutor.execute(new TestRunable("sting"+i));
        }
    }

    static class TestRunable implements Runnable{

        public String name;
        public TestRunable(String name){
            this.name = name;
        }

        @Override
        public void run() {
//            try {
//                synchronousQueue.put(name);
//            } catch (InterruptedException e) {
//                MyException.printStr(e);
//            }
            System.out.println(Thread.currentThread().getName()+",name ="+name+",queue_size"+blockingQueue.size());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                MyException.printStr(e);
            }
        }
    }


    public static Thread[] findAllThreads() {
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup = group;
        // 遍历线程组树，获取根线程组
//        while (group != null) {
//            topGroup = group;
//            group = group.getParent();
//        }
        // 激活的线程数加倍
        int estimatedSize = topGroup.activeCount() * 2;
        Thread[] slacks = new Thread[estimatedSize];
        //获取根线程组的所有线程
        int actualSize = topGroup.enumerate(slacks);
        Thread[] threads = new Thread[actualSize];
        System.arraycopy(slacks, 0, threads, 0, actualSize);
        return threads;
    }



}
