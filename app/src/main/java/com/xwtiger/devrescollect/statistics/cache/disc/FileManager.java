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
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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
    public static String testpath = "D:\\xwwworkspace\\code\\DevResCollect\\log";

    public static int filestate_normal = 0;
    public static int filestate_uploading = 1;
    public static int filestate_uploadsuccess = 2;
    public static int filestate_uploadfailure = 3;

    private static final List<String> pendingUploadList = Collections.synchronizedList(new LinkedList<String>());


    private static ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();


    public static void main(String[] args){
//        String json="abcdefg"+"\r\n"+"123444"+"\r\n"+"咋呼或或哈或多哈多发多付";
//        saveFile(null,json);
//        File file = new File(testpath);
//        File filesrc = new File(file.getAbsolutePath(), "test.txt");
//        String json1 = readFile(filesrc);
//        System.out.println("json ="+json1);
//
//        File file3 = new File(".\\");
//        File filesrc1= new File(file3.getAbsolutePath(), "build.gradle");
//        int filesize = readAllSize(filesrc1);
//        System.out.println("filesize ="+filesize);

        //删除目录
//        File file4 = new File(".\\log");
//        deleteDirAndFileeteDirAndFile(file4);


        File file = new File(".//log");
        //int filesize = FileManager.readAllSize(file);
        //System.out.println("filesize ="+filesize);
        final List<String> list = new ArrayList<>();
        getUploadFileList(file,list);
        System.out.println("list ="+list.toString());
//        try {
//            Thread.sleep(1500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                for(String str:list){
//                    deleteDirAndFile(new File(str));
//                }
//            }
//        });

        UploadEvent.simulationUpload(new IUploadCallBack() {
            @Override
            public void uploadSuccess() {
                for(String str:list){
                    deleteDirAndFile(new File(str));
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


    ///storage/emulated/0/Android/data/com.xwtiger.devrescollect/cache
    public static  void saveFile(Context context,String json){
        File file = MyApplication.context.getExternalCacheDir();
        //String statistics = externalCacheDir.getAbsolutePath()+path;
        //File file = new File();
        if(!file.exists()){
            file.mkdir();
        }
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
            StringBuffer sb = new StringBuffer();
            byte[] bytes = new byte[1024];
            int length=0;
            while((length =bis.read(bytes,0,bytes.length))!=-1){
                sb.append(new String(bytes,"utf-8"));
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
            totalsize =getFileSize(file);
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
    public static List<String> getUploadFileList(File file,List<String> result){
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File f :files){
                getUploadFileList(f,result);
            }
        }else if (file.isFile()){
            String absolutePath = file.getAbsolutePath();
            result.add(absolutePath);
        }
        return result;
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





}
