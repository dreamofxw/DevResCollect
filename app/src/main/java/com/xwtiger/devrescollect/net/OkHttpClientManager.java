package com.xwtiger.devrescollect.net;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.xwtiger.devrescollect.MyApplication;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by xwadmin on 2018/3/30.
 */

public class OkHttpClientManager {

    private static volatile OkHttpClientManager instance;
    private Context mContext;
    private OkHttpClient mOkHttpClient;
    private static final String TAG = "OkHttpClientManager";
    private Handler mHandler;

    public static OkHttpClientManager getInstance(){
        if(instance == null){
            synchronized (OkHttpClientManager.class){
                if(instance == null){
                    instance = new OkHttpClientManager();
                }
            }
        }
        return instance;
    }

    private OkHttpClientManager(){
        mContext = MyApplication.context;
        File file = mContext.getExternalCacheDir();
        Log.d(TAG,"file path = "+file.getAbsolutePath());
        int maxCacheSize = 10*1024*1024;

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS);
                //.cache(new Cache(file.getAbsoluteFile(),maxCacheSize));
        mOkHttpClient = builder.build();
        mHandler = new Handler(Looper.getMainLooper());
    }


    public void getAsynHttp(String url ,ResultCallBack callBack){
        final Request request = new Request.Builder().url(url).build();
        Call call = mOkHttpClient.newCall(request);
        dealResult(call,callBack);
    }

    public void postAsyHttp(String url,ResultCallBack callback,Map<String,String> map){
        RequestBody formBody ;
        FormBody.Builder builder = new FormBody.Builder();
        Set<String> set = map.keySet();
        Iterator<String> iterator = set.iterator();
        while(iterator.hasNext()) {
            String key = iterator.next();
            String value = map.get(key);
            builder.add(key,value);
        }
        formBody = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .addHeader("hhhh","123")
                .addHeader("ang","987")
                .build();
        Call call = mOkHttpClient.newCall(request);
        dealResult(call,callback);
    }


    private void dealResult(Call call,final ResultCallBack callBack){
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedCallBack(call,callBack,e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                sendSuccessCallBack(response.body().string(),callBack);
            }
        });
    }

    private void sendSuccessCallBack(final String str,final ResultCallBack callBack) {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    callBack.onRespone(str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void sendFailedCallBack(final Call call,final ResultCallBack callBack,final Exception e){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onError(call.request(),e);
            }
        });
    }


    public void postAsyHttp1(String url, final UploadLogCallBack callback, String json, final String uploadkey){
        MediaType type = MediaType.parse("application/json;charset=utf-8");
        RequestBody formBody  = RequestBody.create(type,json);

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type","application/json;charset=utf-8")
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //sendFailedCallBack(call,callBack,e);
                callback.onError(call.request(),e,uploadkey);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.onRespone(response.body().string(),uploadkey);
            }
        });
    }


}
