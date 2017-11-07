package com.xwtiger.devrescollect.study.javaapi;

/**
 * Created by Busap-112 on 2017/11/6.
 *
 * 记录一些特殊的java 关键字的使用
 *
 * **************start**************
 *  exp:private volatile int value;
 *  一旦一个共享变量（类的成员变量、类的静态成员变量）被volatile修饰之后，那么就具备了两层语义：

 1）保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
 但是volatile 不能保证对变量的操作的原子性

 2）禁止进行指令重排序（保证了代码执行的有序性）。

 ***************end*************
 *
 *
 *
 *
 *
 *
 */

public class JavaKeyWordStudy {
    public static void main(String[] args){

        //test();

    }

    public static volatile int inc = 0;

    /**
     * 同步保证原子操作
     */
    public synchronized static void increase() {
        inc++;
    }

    public static void test(){
        System.out.println(" ---start-------Thread.activeCount() ="+Thread.activeCount());
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        threadGroup.list();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<10000;j++){
                        increase();
                    }
                };
            }.start();
        }
        System.out.println("Thread.activeCount() ="+Thread.activeCount());
        System.out.println("------------循环开始-----------");
        while(Thread.activeCount()>2){//保证前面的线程都执行完
            System.out.println("threadName ="+Thread.currentThread().getName());
            System.out.println("Thread.activeCount() ="+Thread.activeCount());
            Thread.yield();
        }
        System.out.println("---------循环结束----------");
        System.out.println("Thread.activeCount() ="+Thread.activeCount());
        System.out.println(inc);
    }
}
