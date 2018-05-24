package com.xwtiger.devrescollect.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 异常上报
 *
 */

public class ExceptionUpLoadUtil {

    //单例模式 饿汉模式
    private static ExceptionUpLoadUtil exceptionUpLoadUtil = new ExceptionUpLoadUtil();
    //可回收的线程池
    private ExecutorService executorService;
    private OkHttpClient mHttpClient;

    private ExceptionUpLoadUtil() {
        executorService = Executors.newCachedThreadPool();
        mHttpClient = new OkHttpClient();
    }

    public static ExceptionUpLoadUtil getInstance() {
        return exceptionUpLoadUtil;
    }


    //收集异常的集合
    private final List<String> exceptionList = Collections.synchronizedList(new ArrayList<String>());
    //copy出来准备上传的异常集合
    private final List<String> upExceptionList = Collections.synchronizedList(new ArrayList<String>());

    //上传失败最大重试次数
    private static final int All_UPLOAD_NUMBER = 3;
    //当前上传重试次数
    private int upload_number;

    //是否在上传异常中
    private static boolean isUpLoading = false;
    //上传阈值 集合size>=5
    private static final int UPLOAD_THRESHOLD_SIZE = 5;
    //上传阈值 计时器>= 5min
    private static final int UPLOAD_THRESHOLD_TIME = 5 * 60;

    //配置上传阈值为 size
    public static final int UPLOAD_THRESHOLD_TYPE_SIZE = 1;
    //配置上传阈值为 time
    public static final int UPLOAD_THRESHOLD_TYPE_TIME = 2;
    //配置上传阈值为 size and time
    public static final int UPLOAD_THRESHOLD_TYPE_SIZE_AND_TIME = 3;

    //已选择的上传阈值类型 1：size，2：time，3：size_time 默认为size
    private int sChooseUploadThresholdType;

    /**
     * 在application中配置需要的上传阈值类型。
     *
     * @param uploadThresholdType
     */
    public void init(int uploadThresholdType) {


        sChooseUploadThresholdType = uploadThresholdType;

        if (sChooseUploadThresholdType != UPLOAD_THRESHOLD_TYPE_SIZE) {
            //到达时间阈值
            Observable.interval(UPLOAD_THRESHOLD_TIME, TimeUnit.SECONDS).observeOn(Schedulers.immediate()).subscribe(new Action1<Long>() {
                @Override
                public void call(Long aLong) {
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            synchronized (exceptionList) {
                                /*if (exceptionList.size() > 0) {
                                    if (!isUpLoading) {
                                        isUpLoading = true;
                                        upExceptionList.clear();
                                        for (ExceptionBean bean : exceptionList) {
                                            upExceptionList.add(bean);
                                        }
                                        uploadExceptionToS(upExceptionList);
                                    }
                                }*/
                                checkForUpload();
                            }
                        }
                    });
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    //showToast(error.getMessage());
                }
            });
        }
    }


    /**
     * 添加异常到集合中去
     *
     * @param
     */
    public void addException(final String str) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (exceptionList) {
                    exceptionList.add(str);
                    if (sChooseUploadThresholdType != UPLOAD_THRESHOLD_TYPE_TIME) {
                        //到达集合数量阈值
                        if (exceptionList.size() >= UPLOAD_THRESHOLD_SIZE) {
                            checkForUpload();
                        }
                    }
                }
            }


        });
    }

    private void checkForUpload() {

        int current = exceptionList.size();
        if (current <= 0) {
            return;
        }
        if (!isUpLoading) {
            isUpLoading = true;
            upExceptionList.clear();
            int limit = Math.min(current, UPLOAD_THRESHOLD_SIZE);
            for (int i = 0; i < limit; i++) {
                upExceptionList.add(exceptionList.get(i));
            }
            uploadExceptionToS(upExceptionList);
        }
    }


    /**
     * 从异常集合中删除已经上传到服务器的集合
     *
     * @param list
     */
    private void removeExceptionList(final List<String> list) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (exceptionList) {
                    for (String bean : list) {
                        exceptionList.remove(bean);
                    }
                    //上传成功修改上传状态
                    isUpLoading = false;
                    upload_number = 0;
                    checkForUpload();
                }
            }
        });
    }


    /**
     * 上传异常集合到服务器
     *
     * @param list
     */
    private void uploadExceptionToS(List<String> list) {

//        String exceptionJsonString = new Gson().toJson(list);
//
//        UserInfoData userInfoData = SettingPreference.getUserInfoData(Appli.getContext());
//        String uid = userInfoData == null ? "" : userInfoData.id;
//        String token = SettingPreference.getToken(Appli.getContext());
//        Request request = new Request.Builder()
//                .url(NetConfig.LOG_HOST + "/log.gif?json=" + exceptionJsonString)
//                .addHeader(NewApiClient.HEADER_UID, TextUtils.isEmpty(uid) ? "" : uid)
//                .addHeader(NewApiClient.HEADER_LID, TextUtils.isEmpty(token) ? "" : token)
//                .addHeader(NewApiClient.HEADER_DEVICEID, Utils.getIMEI(Appli.getContext()))
//                .addHeader(NewApiClient.HEADER_VER, Utils.getVersionName(Appli.getContext()))
//                .addHeader(NewApiClient.HEADER_OS, "2")
//                .build();
//        mHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                if (upload_number < All_UPLOAD_NUMBER) {
//                    upload_number++;
//                    uploadExceptionToS(list);
//                    Utils.log("ExceptionUpLoadUtil","上传失败，重试次数"+upload_number);
//
//                } else {
//                    //所有重试上传失败修改上传状态
//                    isUpLoading = false;
//                    upload_number = 0;
//                }
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                removeExceptionList(list);
//                Utils.log("ExceptionUpLoadUtil","上传成功");
//            }
//        });
    }
}
