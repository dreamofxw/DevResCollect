package com.xwtiger.devrescollect.net;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.xwtiger.devrescollect.MyApplication;
import com.xwtiger.devrescollect.MyException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

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
        //同步调用
//        try {
//            call.execute();
//        } catch (IOException e) {
//            MyException.printStr(e);
//        }
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
                    MyException.printStr(e);
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
        call.cancel();
    }


    public void callAllRequest(){
        mOkHttpClient.dispatcher().cancelAll();
    }



    public void supportHttps(InputStream... certificates){


    }

    public void uploadFile(String url,File file){

        //Content-Type MediaType
        //Content-Type	application/octet-stream
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"),file);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {


            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if(response.isSuccessful()){
                    //上传成功
                }else{
                    //上传失败
                }


            }
        });
    }

    public void uploadFileWithParams(String url,Map<String,Object> paramsMap){
        MultipartBody.Builder builder = new MultipartBody.Builder();
        //设置类型
        builder.setType(MultipartBody.FORM);
        //追加参数
        for (String key : paramsMap.keySet()) {
            Object object = paramsMap.get(key);
            if (!(object instanceof File)) {
                builder.addFormDataPart(key, object.toString());
            } else {
                File file = (File) object;
                builder.addFormDataPart(key, file.getName(), createProgressRequestBody(MediaType.parse("application/octet-stream"), file));
            }
        }
        //创建RequestBody
        RequestBody body = builder.build();
        //创建Request
        final Request request = new Request.Builder().url(url).post(body).build();
        final Call call = mOkHttpClient.newBuilder().writeTimeout(50, TimeUnit.SECONDS).build().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, e.toString());
                //failedCallBack("上传失败", callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String string = response.body().string();
                    Log.e(TAG, "response ----->" + string);
                    //successCallBack((T) string, callBack);
                } else {
                    //failedCallBack("上传失败", callBack);
                }
            }
        });
    }




    public <T> RequestBody createProgressRequestBody(final MediaType contentType, final File file) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return contentType;
            }

            @Override
            public long contentLength() {
                return file.length();
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                Source source;
                try {
                    source = Okio.source(file);
                    Buffer buf = new Buffer();
                    long remaining = contentLength();
                    long current = 0;
                    for (long readCount; (readCount = source.read(buf, 2048)) != -1; ) {
                        sink.write(buf, readCount);
                        current += readCount;
                        Log.e(TAG, "current------>" + current);
                        //progressCallBack(remaining, current, callBack);
                    }
                } catch (Exception e) {
                    MyException.printStr(e);
                }
            }
        };
    }


    public static Map<String,Call> cache = new HashMap<String,Call>();

    public void downloadFile(final String url, final File file){
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Call call = mOkHttpClient.newCall(request);
            synchronized (OkHttpClientManager.class){
                cache.put(url,call);
            }

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    synchronized (OkHttpClientManager.class){
                        cache.remove(url);
                    }

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    synchronized (OkHttpClientManager.class){
                        cache.remove(url);
                    }
                    if(call.isCanceled()){
                        return;
                    }
                    InputStream is = null;
                    byte[] buf = new byte[2048];
                    int len = 0;
                    FileOutputStream fos = null;
                    try {
                        long total = response.body().contentLength();
                        Log.e(TAG, "total------>" + total);
                        long current = 0;
                        is = response.body().byteStream();
                        fos = new FileOutputStream(file);
                        while ((len = is.read(buf)) != -1) {
                            current += len;
                            fos.write(buf, 0, len);
                            Log.e(TAG, "current------>" + current);
                        }
                        fos.flush();
                    } catch (IOException e) {
                        Log.e(TAG, e.toString());
                    } finally {
                        try {
                            if (is != null) {
                                is.close();
                            }
                            if (fos != null) {
                                fos.close();
                            }
                        } catch (IOException e) {
                            Log.e(TAG, e.toString());
                        }
                    }
                }
            });
        }catch (Exception e){
            MyException.printStr(e);
        }
    }

}
