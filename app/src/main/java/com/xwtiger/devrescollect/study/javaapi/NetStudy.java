package com.xwtiger.devrescollect.study.javaapi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Busap-112 on 2017/11/6.
 */

public class NetStudy {

    public static void main(String[] args){
        //connectNet2();
        //connectNetByOkhttp();
        connectNetWithssl();
    }

    /**
     * http +ssl
     *
     */
    public static void connectNetWithssl() {
        String url = "https://www.baidu.com/";
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

            //初始化信任工厂
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509");
            tmf.init(keyStore);

            //初始化密钥工厂 这个暂时不用
//            KeyManagerFactory kmf =  KeyManagerFactory.getInstance("X509");
//            kmf.init(keyStore,"123456".toCharArray());

            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);

            HttpsURLConnection urlConnection = (HttpsURLConnection) new URL(url).openConnection();
            urlConnection.setSSLSocketFactory(context.getSocketFactory());
            urlConnection.connect();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

    }

    /**
     * 用第三方请求网络
     */
    public static void connectNetByOkhttp(){

        String url = "http://top.baidu.com/buzz?b=1&fr=tph_right";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        Request request = builder.build();
        try {
            Response execute = okHttpClient.newCall(request).execute();
            System.out.println("body ="+execute.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void connectNet2(){
        String url = "http://top.baidu.com/buzz?b=1&fr=tph_right";
        File file = new File("");
        File filedest = new File(file.getAbsolutePath(),"\\2017110602.html");
        BufferedInputStream bis;
        BufferedOutputStream bos;
        PrintWriter printWriter;
        try {
            /**
             *  通过在 URL 上调用 openConnection 方法创建连接对象。
             处理设置参数和一般请求属性。
             使用 connect 方法建立到远程对象的实际连接。
             远程对象变为可用。远程对象的头字段和内容变为可访问。
             */
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
            urlConnection.setAllowUserInteraction(true);
            urlConnection.setConnectTimeout(5000);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            urlConnection.connect();

            int responseCode = urlConnection.getResponseCode();
            String responseMessage = urlConnection.getResponseMessage();
            Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
            System.out.println("headerFields ="+headerFields.toString());
            System.out.println("headerFields ="+headerFields.size());

            System.out.println("responseCode ="+responseCode);
            System.out.println("responseMessage ="+responseMessage);




            bis = new BufferedInputStream(urlConnection.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(filedest));


            byte[] bytes = new byte[1024];
            int length =0;
            while((length =bis.read(bytes,0,bytes.length))!= -1){
                bos.write(bytes,0,length);
                bos.flush();
            }
            bis.close();
            bos.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
