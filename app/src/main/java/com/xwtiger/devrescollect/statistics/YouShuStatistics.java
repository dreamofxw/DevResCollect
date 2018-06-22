package com.xwtiger.devrescollect.statistics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.xwtiger.devrescollect.MyApplication;
import com.xwtiger.devrescollect.net.OkHttpClientManager;
import com.xwtiger.devrescollect.net.UploadLogCallBack;
import com.xwtiger.devrescollect.statistics.cache.disc.FileManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.Request;

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

    private static int uploadfile_threshold= 4*1024;


    private static final List<ActionLogBean> eventList = Collections.synchronizedList(new LinkedList<ActionLogBean>());

    private static List<ActionLogBean> cacheList = Collections.synchronizedList(new LinkedList<ActionLogBean>());

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    private static ScheduledExecutorService timerService = Executors.newScheduledThreadPool(1);

    private static ExecutorService executorFile = Executors.newSingleThreadExecutor();
    private static ScheduledExecutorService executorCheckFile = Executors.newScheduledThreadPool(1);

    private HomeWatcherReceiver mHomeWatcherReceiver = null;

    private static YouShuStatistics instance = null;

    private static volatile boolean isCheck = false;


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
    public void addEvent(final ActionLogBean event){
        if(executorService != null){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    synchronized (YouShuStatistics.class){
                        eventList.add(event);
                        //新添加
                        checkCacheListSize();
                    }
                }
            });
        }
    }

    /**
     * 在application 中开启
     */
    public void startCheck(){
        System.out.println("startCheck-------------------------");
//        startCheckCacheSize();
//        checkCacheFileSize();
        registerReceiver();
    }


    /**
     * 开启定时器检查内存缓存大小
     */
    private void startCheckCacheSize(){
        if(timerService != null ){
            timerService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    synchronized (YouShuStatistics.class){
                        checkCacheListSize();
                    }
                }
            },1000, looptime,TimeUnit.MILLISECONDS);
        }
    }

    private  void checkCacheListSize(){
        if(eventList.size()>EVENTTHRESHOLD){
            //将数据写入缓存文件
            for (int i=EVENTTHRESHOLD;i>0;i--){
                ActionLogBean removeobj = eventList.remove(i);
                cacheList.add(0,removeobj);
            }
            saveJsonToFile();
        }else{
            //未达到条件什么不做
            System.out.println("checkCacheListSize =未达到条件什么不做");
        }
    }

    private void checkCacheFileSize(){
        if(executorCheckFile != null ){
            executorCheckFile.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    synchronized (FileManager.class){
                        uploadFileByCondition();
                    }
                }
            },1000,checkFiletime,TimeUnit.MILLISECONDS);
        }

    }


    private  void saveJsonToFile(){
        if(cacheList != null && cacheList.size()>0){
            final String result = new Gson().toJson(cacheList);
            System.out.println("saveJsonToFile result ="+result);
            cacheList.clear();
            if(executorFile != null && !executorFile.isShutdown()){
                executorFile.execute(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (FileManager.class){
                            FileManager.saveFile(null,result);
                            //新加
                            uploadFileByCondition();
                        }
                    }
                });
            }

        }

    }

    /**
     * 手动上传文件  需要重新修改
     */
    public void manualUploadFile(){
        if(executorFile != null ){
            executorFile.execute(new Runnable() {
                @Override
                public void run() {
                    synchronized (FileManager.class){
                        uploadFileByNoCondition();
                    }
                }
            });
        }
    }

    /**
     * app退出或压后台 开始上传文件
     */
    public void uploadFileForBackGroundRunning(){
        //先保存内存缓存到文件
        //saveJsonToFile();
        synchronized (YouShuStatistics.class){
            if(eventList.size()>0){
                //将数据写入缓存文件
                cacheList.addAll(eventList);
                eventList.clear();
                saveJsonToFile();
            }
        }
        //然后上传文件日志到后台
        if(executorFile != null ){
            executorFile.execute(new Runnable() {
                @Override
                public void run() {
                    synchronized (FileManager.class){
                        uploadFileByNoCondition();
                    }
                }
            });
        }
    }


    private void uploadFileByCondition(){
        System.out.println("开始检查文件的大小  uploadFileByCondition");
        /*final File file = MyApplication.context.getExternalCacheDir();
        if(!file.exists()){
            file.mkdir();
        }*/

        File file = FileManager.getCacheFile();
        int filesize = FileManager.readAllSize(file);
        System.out.println("filesize ="+filesize);
        if(filesize >uploadfile_threshold){
            //上传文件
            uploadFileByNoCondition();
        }
    }

    /**
     * 直接上传文件
     */
    private void uploadFileByNoCondition(){
        File file = FileManager.getCacheFile();
        final ArrayList<String> list = new ArrayList<String>();
        String key = FileManager.getUploadFileList(file, list);
        System.out.println("上传文件的路径是"+FileManager.pendingUploadMap.get(key).toString());

        String url = UploadEvent.LogUrl+UploadEvent.log;
        ArrayList<String> upload_path = FileManager.pendingUploadMap.get(key);
        for(String json:upload_path){
            String log = FileManager.readFile(new File(json));
            System.out.println("thread name start upload ="+Thread.currentThread().getName());
            OkHttpClientManager.getInstance().postAsyHttp1(url, new UploadLogCallBack() {
                @Override
                public void onError(Request request, Exception e,String uploadkey) {
                    System.out.println("upLoadLog  onError" );
                    //出错了，清除锁定的文件 但是没有删文件
                    synchronized (FileManager.class){
                        FileManager.pendingUpLoadKey.remove(uploadkey);
                        ArrayList<String> remove = FileManager.pendingUploadMap.remove(uploadkey);
                    }
                }

                @Override
                public void onRespone(String str,String logkey) throws IOException {
                    System.out.println("thread name end upload ="+Thread.currentThread().getName());
                    System.out.println("upLoadLog   onRespone str=" +str.toString());
                    synchronized (FileManager.class){
                        ResultBean resultBean =new Gson().fromJson(str,ResultBean.class);
                        FileManager.pendingUpLoadKey.remove(logkey);
                        ArrayList<String> remove = FileManager.pendingUploadMap.remove(logkey);
                        if(resultBean !=null && Integer.parseInt(resultBean.data.count)>0){
                            if(remove != null && remove.size()>0){
                                for(String s:remove){
                                    FileManager.deleteDirAndFile(new File(s));
                                }
                            }
                        }
                    }
                }
            },log,key);
        }
    }


    public void destroy(){
        stopCheck();
        MyApplication.getContext().unregisterReceiver(mHomeWatcherReceiver);
    }

    public void stopCheck(){
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
                    //YouShuStatistics.getInstance().stopCheck();
                }
            }
        }
    }

    private void registerReceiver() {
        mHomeWatcherReceiver = new HomeWatcherReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        MyApplication.getContext().registerReceiver(mHomeWatcherReceiver, filter);
    }

    public static boolean isGoodJson(String json) {
        if (TextUtils.isEmpty(json)) {
            return false;
        }

        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonSyntaxException e) {
            return false;
        } catch (JsonParseException e) {
            return false;
        }
    }

}
