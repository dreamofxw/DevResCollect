package com.xwtiger.devrescollect.study.javaapi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Busap-112 on 2017/11/3.
 */

public class IOStudy {

    public void main(String[] args){

    }


    public static void ioofbuffer(){
        File filesrc = new File(".\\app\\src\\main\\java\\com\\xwtiger\\devrescollect\\study\\javaapi\\PatternStudy.java");//D:\privateproject\DevResCollect\app\src\main\java\com\xwtiger\devrescollect\study\javaapi\PatternStudy.java
        File filedest = FileStudy.makeFile();
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filesrc));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filedest));
            byte[] bytes = new byte[1024];
            int readbyte =0;
            while((readbyte = bis.read(bytes)) != -1){
                LogUtils.printString("BufferedOutputStream","byte[]",bytes.length+"");
                bos.write(bytes,0,readbyte);
                bos.flush();
            }
            bos.close();
            bis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
