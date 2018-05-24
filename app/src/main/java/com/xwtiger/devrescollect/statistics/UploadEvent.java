package com.xwtiger.devrescollect.statistics;

import android.text.TextUtils;
import android.util.Base64;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import retrofit2.http.Url;

/**
 * author:xw
 * Date:2018-05-24 18:09
 * Description:
 */
public class UploadEvent {


    public static void main(String[] args){
        getDataFromNet();
    }

    public static void getDataFromNet(){
        URL url = null;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL("http://www.baidu.com/");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(".\\test123.txt")));
            if(urlConnection.getResponseCode()==200){
                byte[] b = new byte[1024];
                int length;
                while( (length =in.read(b,0,b.length))!=-1){
                    bos.write(b,0,length);
                    System.out.println(new String(b,"utf-8"));
                }
            }
            bos.close();
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            urlConnection.disconnect();
        }

    }


    public static void uploadDate(){

        URL url = null;
        HttpURLConnection urlConnection =null;
        try {
        url = new URL("");
        urlConnection =(HttpURLConnection) url.openConnection();
        urlConnection.setDoOutput(true);
        urlConnection.setChunkedStreamingMode(0);

        StringBuffer sb = new StringBuffer();
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(".\\test123.txt")));
        byte[] b = new byte[1024];

        OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
        int length;
        while((length =in.read(b))!= -1){
            System.out.println("----------");
            System.out.println(new String(b,"utf-8"));
            out.write(b,0,length);
        }
        in.close();
        out.close();

    } catch (Exception e){

    }finally {
      urlConnection.disconnect();
    }

    }

    public static String compressForGzip(String unGzipStr) {

        if (TextUtils.isEmpty(unGzipStr)) {
            return null;
        }
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(baos);
            gzip.write(unGzipStr.getBytes());
            gzip.close();
            byte[] encode = baos.toByteArray();
            baos.flush();
            baos.close();
            return new String(Base64.encode(encode,Base64.DEFAULT));
            //return Base64Encoder.encode(encode);
            //return "";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * Gzip解压数据
     *
     * @param gzipStr
     * @return
     */
    public static String decompressForGzip(String gzipStr) {
        if (TextUtils.isEmpty(gzipStr)) {
            return null;
        }
        byte[] t = Base64.decode(gzipStr,Base64.DEFAULT);
        //byte[] t = Base64Decoder.decodeToBytes(gzipStr);
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteArrayInputStream in = new ByteArrayInputStream(t);
            GZIPInputStream gzip = new GZIPInputStream(in);
            byte[] buffer = new byte[2048];
            int n = 0;
            while ((n = gzip.read(buffer, 0, buffer.length)) > 0) {
                out.write(buffer, 0, n);
            }
            gzip.close();
            in.close();
            out.close();
            return out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Zip 压缩数据
     *
     * @param unZipStr
     * @return
     */
    public static String compressForZip(String unZipStr) {
        if (TextUtils.isEmpty(unZipStr)) {
            return null;
        }
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ZipOutputStream zip = new ZipOutputStream(baos);
            zip.putNextEntry(new ZipEntry("0"));
            zip.write(unZipStr.getBytes());
            zip.closeEntry();
            zip.close();
            byte[] encode = baos.toByteArray();
            baos.flush();
            baos.close();
            return new String(Base64.encode(encode,Base64.DEFAULT));
            //return Base64Encoder.encode(encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Zip解压数据
     *
     * @param zipStr
     * @return
     */
    public static String decompressForZip(String zipStr) {

        if (TextUtils.isEmpty(zipStr)) {
            return null;
        }
        byte[] t = Base64.decode(zipStr,Base64.DEFAULT);
        //byte[] t = Base64Decoder.decodeToBytes(zipStr);
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteArrayInputStream in = new ByteArrayInputStream(t);
            ZipInputStream zip = new ZipInputStream(in);
            zip.getNextEntry();
            byte[] buffer = new byte[2048];
            int n = 0;
            while ((n = zip.read(buffer, 0, buffer.length)) > 0) {
                out.write(buffer, 0, n);
            }
            zip.close();
            in.close();
            out.close();
            return out.toString("UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void testzip(){

    }
}
