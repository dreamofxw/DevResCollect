package com.xwtiger.devrescollect.statistics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;

import com.xwtiger.devrescollect.MyApplication;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.statistics.cache.disc.FileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * author:xw
 * Date:2018-05-24 17:08
 * Description:
 */
public class YouShuStatistics {

    private static int EVENTTHRESHOLD=20;//事件阈值

    private static int loopNum = 0;

    private static int looptime = 3000;//定时循环时间单位毫秒

    private static int loopfact = 1;//

    private static int checkFiletime = 2000;

    private static int uploadfile_threshold= 1024;


    private static final List<String> eventList = Collections.synchronizedList(new LinkedList<String>());

    private static List<String> cacheList = Collections.synchronizedList(new LinkedList<String>());

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    private static ScheduledExecutorService timerService = Executors.newScheduledThreadPool(1);

    private static ExecutorService executorFile = Executors.newSingleThreadExecutor();
    private static ScheduledExecutorService executorCheckFile = Executors.newScheduledThreadPool(1);

    private HomeWatcherReceiver mHomeWatcherReceiver = null;

    private static YouShuStatistics instance = null;


    private YouShuStatistics(){};

    public static YouShuStatistics getInstance(){
        if(instance ==null){
            synchronized (YouShuStatistics.class){
                if(instance ==null){
                    instance = new YouShuStatistics();
                }
            }
        }
        return instance;
    }


    /**
     * 添加事件
     * @param event
     */
    public void addEvent(final String event){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (YouShuStatistics.class){
                    eventList.add(event);
                }
            }
        });
    }

    /**
     * 在application 中开启
     */
    public void startCheck(){
        startCheckCacheSize();
        checkCacheFileSize();
        registerReceiver();
    }

    public void removeEvent(final String str){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (YouShuStatistics.class){
                     eventList.remove(str);
                }
            }
        });
    }

    /**
     * 开启定时器检查内存缓存大小
     */
    private void startCheckCacheSize(){
        timerService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                synchronized (YouShuStatistics.class){
                    checkCacheListSize();
                }
            }
        },1000, looptime,TimeUnit.MILLISECONDS);
    }


    private  void checkCacheListSize(){
        if(eventList.size()>EVENTTHRESHOLD){
            //将数据写入缓存文件
            for (int i=EVENTTHRESHOLD;i>0;i--){
                String removeobj = eventList.remove(i);
                cacheList.add(0,removeobj);
            }
            saveJsonToFile();
        }else{
            //未达到条件什么不做
            System.out.println("checkListSize =未达到条件什么不做");
            if(loopNum >= 5){
                loopfact = 2;
                cacheList.clear();
                cacheList.addAll(eventList);
                eventList.clear();
                if(cacheList.size()>0){
                    saveJsonToFile();
                }

            }else if(loopNum >20){
                loopfact = 4;
            }
            loopNum++;
        }
    }

    private void checkCacheFileSize(){
        executorCheckFile.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                synchronized (FileManager.class){
                    uploadFileByCondition();
                }
            }
        },1000,checkFiletime,TimeUnit.MILLISECONDS);
    }


    private  void saveJsonToFile(){
        final String result = cacheList.toString();
        System.out.println("result ="+result);
        cacheList.clear();
        executorFile.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (FileManager.class){
                    FileManager.saveFile(null,result);
                }
            }
        });
    }

    /**
     * 手动上传文件
     */
    public void manualUploadFile(){
        File file = new File(".//log");
        List<String> list = new ArrayList<String>();
        FileManager.getUploadFileList(file,list);
        System.out.println("上传文件的路径是"+list.toString());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(String str:list){
            FileManager.deleteDirAndFile(new File(str));
        }

    }

    /**
     * app退出或压后台 开始上传文件
     */
    public void uploadFileForBackGroundRunning(){
        //先保存内存缓存到文件
        saveJsonToFile();
        //然后上传文件日志到后台
        uploadFileByCondition();
    }



    private void uploadFileByCondition(){
        File file = MyApplication.context.getExternalCacheDir();
        //String statistics = externalCacheDir.getAbsolutePath()+path;
        //File file = new File();
        if(!file.exists()){
            file.mkdir();
        }
        //File file = new File(".//log");
        int filesize = FileManager.readAllSize(file);
        System.out.println("filesize ="+filesize);
        if(filesize >uploadfile_threshold){
            //上传文件
            final List<String> list = new ArrayList<String>();
            FileManager.getUploadFileList(file,list);
            System.out.println("上传文件的路径是"+list.toString());
            UploadEvent.simulationUpload(new IUploadCallBack() {
                @Override
                public void uploadSuccess() {
                    for(String str:list){
                        FileManager.deleteDirAndFile(new File(str));
                    }
                }

                @Override
                public void uploadFailure() {

                }

                @Override
                public void uploadCancel() {

                }
            });

        }
    }


    public void destroy(){
        if(executorService != null){
            executorService.shutdown();
        }
        if(timerService != null){
            timerService.shutdown();
        }
        if(executorFile != null){
            executorFile.shutdown();
        }
        if(executorCheckFile != null){
            executorCheckFile.shutdown();
        }
        MyApplication.getContext().unregisterReceiver(mHomeWatcherReceiver);

    }


    public static class HomeWatcherReceiver extends BroadcastReceiver {

        private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
        private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

        @Override
        public void onReceive(Context context, Intent intent) {
            String intentAction = intent.getAction();
            Log.i("", "intentAction =" + intentAction);
            if (TextUtils.equals(intentAction, Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
                if (TextUtils.equals(SYSTEM_DIALOG_REASON_HOME_KEY, reason)) {
                    Log.d("HomeWatcherReceiver","按下home键 HomeWatcherReceiver");
                    YouShuStatistics.getInstance().uploadFileForBackGroundRunning();
                }
            }
        }

    }

    private void registerReceiver() {
        mHomeWatcherReceiver = new HomeWatcherReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        MyApplication.getContext().registerReceiver(mHomeWatcherReceiver, filter);
    }


}
