package com.xwtiger.devrescollect.study.javaapi;

/**
 *
 * Created by Busap-112 on 2017/11/6.
 *
 * 关于线程的常用的总结
 *
 */

public class ThreadStudy {


    public static void main(String[] args){



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
