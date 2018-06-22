package com.xwtiger.devrescollect.statistics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * author:xw
 * Date:2018-05-28 15:34
 * Description:
 */
public class TestStatistics {

    public static int count = 0;
    
    public static boolean isFinished = true;
    public static void main(String[] args){

        YouShuStatistics.getInstance().startCheck();
        TestTask task = new TestTask();
        for(int i=0;i<5;i++){
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(task);
        }

//        while(isFinished){
//            System.out.println("Thread.activeCount()"+Thread.activeCount());
//            if(Thread.activeCount() >2){
//
//            }else{
//                isFinished = false;
//                YouShuStatistics.getInstance().destroy();
//            }
//        }
        System.out.println("完成");

    }


    static class TestTask implements Runnable{
        @Override
        public void run() {
            for(int i =0;i<100;i++){
               /* String name = Thread.currentThread().getName();
                YouShuStatistics.getInstance().addEvent(name+"=="+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    


    public static void testZip(){
        String str = "张三，哈哈哈123" ;
        String zip = UploadEvent.compressForZip(str);
        String newstr = UploadEvent.decompressForZip(zip);
        System.out.println(newstr);
    }


    public static void testTimeer(){
        final ScheduledExecutorService timerService = Executors.newScheduledThreadPool(1);
        timerService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if(count<100){
                    System.out.println("count ="+count);
                    count++;
                }else{
                    timerService.shutdown();
                }
            }
        },100,200,TimeUnit.MILLISECONDS);
    }


}
