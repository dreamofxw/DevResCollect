package com.xwtiger.devrescollect.study.javaapi;

import android.content.SyncStatusObserver;

import com.xwtiger.devrescollect.MyException;

import java.io.File;
import java.io.IOException;

/**
 * Created by Busap-112 on 2017/11/3.
 *
 *
 *
 * . Context.getFilesDir()，该方法返回/data/data/youPackageName/files的File对象
 *
 *通过Context.getExternalFilesDir()方法可以获取到 SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
 通过Context.getExternalCacheDir()方法可以获取到 SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据

 如果使用上面的方法，当你的应用在被用户卸载后，SDCard/Android/data/你的应用的包名/ 这个目录下的所有文件都会被删除，不会留下垃圾信息。

 通过Context.getFilesDir()方法可以获取到/data/data/youPackageName/files这个目录，一般放一些短时间保存的数据，同样引用卸载后，这个目录下的数据也会删除，不会留下垃圾信息。
 Context.getCacheDir()，该方法返回   /data/data/youPackageName/cache的File对象，这个文件里面的数据在设备内存不足的时候，会被系统删除数据。注意：你不能依赖系统帮你删除这些文件，当这个文件夹里面的数据超过1MB的时候，系统会删除这个文件夹里面的数据。

 而且上面二个目录分别对应 设置->应用->应用详情里面的”清除数据“与”清除缓存“选项
 *
 *
 */

public class FileStudy {

    public static void main(String[] args){
        //deleteDirAndFile(new File("d:\\test20171201"));
        //makeFile();
        //printFilePath();
        //getCurrentProjectDir();
        //test();
        //makeFile();
        String path = "/Users/xuww/DevResCollect/testfile";

        File file = new File(path);

        deleteDirAndFile(file,false);

    }

    public static void test(){
        File file = new File("");
        try {
            makeNewFile(file.getAbsolutePath(),"createFiletest22.txt");
        } catch (Exception e) {
            MyException.printStr(e);
        }

    }


    public static void printFilePath(File filesrc){
        if(filesrc == null){
            return;
        }
        try {
            LogUtils.printString("getPath","getPath",filesrc.getPath());
            LogUtils.printString("getCanonicalPath","getCanonicalPath",filesrc.getCanonicalPath());
            LogUtils.printString("getAbsolutePath","getAbsolutePath",filesrc.getAbsolutePath());
            LogUtils.printString("getName","getName",filesrc.getName());
            LogUtils.printString("getParent","getParent",filesrc.getParent());
        } catch (IOException e) {
            MyException.printStr(e);
        }
    }


    public static void printFilePath(){
        File filenew = makeFile();
        try {
            LogUtils.printString("getPath","getPath",filenew.getPath());
            LogUtils.printString("getCanonicalPath","getCanonicalPath",filenew.getCanonicalPath());
            LogUtils.printString("getAbsolutePath","getAbsolutePath",filenew.getAbsolutePath());
            LogUtils.printString("getName","getName",filenew.getName());
            LogUtils.printString("getParent","getParent",filenew.getParent());
        } catch (IOException e) {
            MyException.printStr(e);
        }
    }

    /**
     * 当前项目的目录
     */
    public static void getCurrentProjectDir(){
        File file = new File("");
        printFilePath(file);
    }

    public static File makeFile(){
        //String path = "d:\\test20171103\\hahah\\张三\\";
        String path = "/Users/xuww/DevResCollect/testfile/123/";
        String filename = "1003.txt";
        return makeNewFile(path,filename);
    }

    /**
     *  File file = new File("d:\\test20171102");
     * @param path
     */
    public static File makeNewFile(String path,String filename)throws NullPointerException{
        File filedir = new File(path);
        LogUtils.printString("makeNewFile","before",filedir.exists()+"");
        if(!filedir.exists()){
            filedir.mkdirs();
        }
        File fileNames = new File(path,filename);
        System.out.println(fileNames.getPath());
        try {
            if(!fileNames.exists()){
                boolean newFile = fileNames.createNewFile();
                System.out.println("newFile ="+"重新创建");
            }else{
                System.out.println("newFile ="+"已经创建过了");
            }
            return fileNames;
        } catch (IOException e) {
            MyException.printStr(e);
        }
        return null;
    }


    /**
     * 删除指定的目录和文件
     * @param file
     */
    public static void deleteDirAndFile(File file,boolean isdeleteRoot) {
        if (file.isDirectory()) {
            System.out.println("dir ="+file.getPath());
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File file_temp = files[i];
                deleteDirAndFile(file_temp,true);
            }
            if(isdeleteRoot){
                file.delete();
            }
        } else if (file.isFile()) {
            file.delete();
            System.out.println("file ="+file.getName());
        }
    }

}
