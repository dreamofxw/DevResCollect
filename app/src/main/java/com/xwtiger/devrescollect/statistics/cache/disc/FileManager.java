package com.xwtiger.devrescollect.statistics.cache.disc;

import android.content.Context;

import com.xwtiger.devrescollect.MyApplication;
import com.xwtiger.devrescollect.statistics.IUploadCallBack;
import com.xwtiger.devrescollect.statistics.UploadEvent;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * author:xw
 * Date:2018-05-28 17:25
 * Description:
 */
public class FileManager {

//    externalCacheDir =/storage/emulated/0/Android/data/com.xwtiger.devrescollect/cache
//    cacheDir =/data/user/0/com.xwtiger.devrescollect/cache
//    externalStorageDirectory =/storage/emulated/0
    public static String path ="/statistics";

    public static final Map<String,ArrayList<String>> pendingUploadMap = Collections.synchronizedMap(new HashMap<String,ArrayList<String>>());

    public static final List<String> pendingUpLoadKey = Collections.synchronizedList(new LinkedList<String>());

    private static ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();


    ///storage/emulated/0/Android/data/com.xwtiger.devrescollect/cache
    public static  void saveFile(Context context,String json){
        /*File file = MyApplication.context.getExternalCacheDir();
        if(!file.exists()){
            file.mkdir();
        }*/
        final File file = getCacheFile();
        long currentTime = System.currentTimeMillis();
        System.out.println("start write file------------------");
        File destfile = new File(file.getAbsolutePath(),currentTime+".txt");
        if(!destfile.exists()){
            try {
                destfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destfile,true));
            bos.write(json.getBytes());
            bos.write("\r\n".getBytes());
            bos.flush();
            bos.close();
            //保存文件名到对应的目录
            String absolutePath_dest = destfile.getAbsolutePath();

            System.out.println("ent write file------------------");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String readFile(File file){
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            StringWriter sw = new StringWriter();
            StringBuffer sb = new StringBuffer();
            byte[] bytes = new byte[1024];
            int length=0;
            while((length =bis.read(bytes,0,bytes.length))!=-1){
                sb.append(new String(bytes,0,length,"utf-8"));
            }
            bis.close();
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }



    public static int getFileSize(File file) {
        FileInputStream fis = null;
        if(file.exists()){
            try {
                fis = new FileInputStream(file);
                int length = fis.available();
                fis.close();
                return length;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(fis != null){
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return -1;
    }


    /**
     * 获取目录下总的文件大小
     * @param file
     */
    public static int readAllSize(File file) {
        int totalsize =0;
        if (file.isDirectory()) {
            //System.out.println("dir ="+file.getPath());
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File file_temp = files[i];
                totalsize +=readAllSize(file_temp);
            }

        } else if (file.isFile()) {
            boolean isContainer = false;
            for(String key:pendingUpLoadKey){
                ArrayList<String> pendinglist = pendingUploadMap.get(key);
                if(pendinglist != null&&pendinglist.contains(file.getAbsolutePath())){
                    isContainer =true;
                    break;
                }
            }
            if(!isContainer){
                totalsize =getFileSize(file);
            }
            //System.out.println("file ="+file.getName());
        }
        return totalsize;
    }

    /**
     * 获取目录下的所有文件路径
     * @param file
     * @param result
     * @return
     */
    public static String getUploadFileList(File file,ArrayList<String> result){
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File f :files){
                getUploadFileList(f,result);
            }
        }else if (file.isFile()){
            String absolutePath = file.getAbsolutePath();
            boolean isContainer= false;
            for(String key:pendingUpLoadKey){
                ArrayList<String> pendinglist = pendingUploadMap.get(key);
                if(pendinglist != null&&pendinglist.contains(absolutePath)){
                    isContainer =true;
                    break;
                }
            }
            if(!isContainer){
                result.add(absolutePath);
            }
        }
        String time = System.currentTimeMillis()+"";
        pendingUpLoadKey.add(time);
        pendingUploadMap.put(time,result);
        return time;
    }
    


    /**
     * 删除指定的目录和文件
     * @param file
     */
    public static void deleteDirAndFile(File file) {
        if (file.isDirectory()) {
            System.out.println("dir ="+file.getPath());
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File file_temp = files[i];
                deleteDirAndFile(file_temp);
            }
            file.delete();
        } else if (file.isFile()) {
            boolean delete = file.delete();
            System.out.println("file ="+file.getName()+"delete ="+delete);
        }
    }



    public static File getCacheFile(){
        File file = MyApplication.context.getExternalCacheDir();
        String statistics = file.getAbsolutePath()+path;
        File filedest = new File(statistics);
        if(!filedest.exists()){
            filedest.mkdir();
        }
        return filedest;
    }


}
