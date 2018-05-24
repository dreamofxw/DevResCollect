package com.xwtiger.devrescollect.statistics;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author:xw
 * Date:2018-05-24 17:08
 * Description:
 */
public class YouShuStatistics {


    private static YouShuStatistics instance = null;

    private YouShuStatistics(){};

    public YouShuStatistics getInstance(){
        if(instance ==null){
            synchronized (YouShuStatistics.this){
                if(instance ==null){
                    instance = new YouShuStatistics();
                }
            }
        }
        return instance;
    }

    private final List<String> eventList = Collections.synchronizedList(new LinkedList<String>());


    private ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * 添加事件
     * @param event
     */
    public void addEvent(final String event){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (YouShuStatistics.this){
                    eventList.add(event);
                }
            }
        });
    }

    public void removeEvent(final String str){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (YouShuStatistics.this){
                     eventList.remove(str);
                }
            }
        });
    }







}
