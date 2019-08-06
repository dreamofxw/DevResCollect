package com.xwtiger.devrescollect.study.javaapi;

import com.xwtiger.devrescollect.MyException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by Busap-112 on 2017/11/3.
 */

public class IOStudy {

    public static void main(String[] args){

        //io_BufferedInputStream();
        //io_BufferedReader();
        //io_FileReader();
        //io_StringReader();
        io_RandomAccessFile();
    }

    public static void testString(){


    }




    public static void io_RandomAccessFile(){
        File filesrc = new File("local.properties");
        File file = new File("");
        File filedest = new File(file.getAbsolutePath(),"\\2017002.txt");
        try {
            RandomAccessFile randomAccessFile_read = new RandomAccessFile(filesrc,"rw");
            RandomAccessFile randomAccessFile_write = new RandomAccessFile(filedest,"rw");
            byte[] bytes = new byte[1024];
            int length;
            while(-1 != (length =randomAccessFile_read.read(bytes,0,bytes.length))){
                randomAccessFile_write.write(bytes,0,length);
            }
            randomAccessFile_read.close();
            randomAccessFile_write.close();
        }  catch (Exception e) {
            MyException.printStr(e);
        }
    }


    /**
     * StringWriter 里面封装了一个StringBuffer
     */
    public static void io_StringReader(){
        File filesrc = new File("local.properties");
        File file = new File("");
        File filedest = new File(file.getAbsolutePath(),"\\2017002.txt");
        try {
            StringReader stringReader = new StringReader("adasdfasd");
            StringWriter stringWriter = new StringWriter();
            char[] chars = new char[1024];
            int length;
            while(-1 !=(length =stringReader.read(chars,0,chars.length))){
                stringWriter.write(chars,0,length);
                stringWriter.flush();
            }
            StringBuffer buffer = stringWriter.getBuffer();
            System.out.println("buffer ="+buffer.toString());
            stringWriter.close();
            stringReader.close();
        }  catch (Exception e) {
            MyException.printStr(e);
        }
    }

    public static void io_FileReader(){
        File filesrc = new File("local.properties");
        File file = new File("");
        File filedest = new File(file.getAbsolutePath(),"\\2017002.txt");
        try {
            FileReader fileReader = new FileReader(filesrc);
            FileWriter fileWriter = new FileWriter(filedest);
            char[] chars = new char[1024];
            int charnum =0;
            while (-1 !=(charnum = fileReader.read(chars,0,chars.length))){
                fileWriter.write(chars,0,charnum);
                fileWriter.flush();
            }
        } catch (FileNotFoundException e) {
            MyException.printStr(e);
        } catch (IOException e) {
            MyException.printStr(e);
        }
    }

    public static void io_BufferedReader(){
        File filesrc = new File("local.properties");
        File file = new File("");
        File filedest = new File(file.getAbsolutePath(),"\\2017001.txt");
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filesrc)));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filedest)));
            String line;
            while(null !=(line = bufferedReader.readLine())){
                bufferedWriter.write(line);
                bufferedWriter.write("\r\n");
                bufferedWriter.flush();
            }
            bufferedWriter.close();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            MyException.printStr(e);
        } catch (IOException e) {
            MyException.printStr(e);
        }

    }


    public static void io_BufferedInputStream(){
        File filesrc = new File(".\\app\\src\\main\\java\\com\\xwtiger\\devrescollect\\study\\javaapi\\PatternStudy.java");//D:\privateproject\DevResCollect\app\src\main\java\com\xwtiger\devrescollect\study\javaapi\PatternStudy.java
        //File filesrc = new File("MainActivity.java");
//        String absolutePath = filesrc1.getAbsolutePath();
//        System.out.println("absolutePath ="+absolutePath);
//        File filesrc = new File(absolutePath,"README.md");
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
            MyException.printStr(e);
        } catch (IOException e) {
            MyException.printStr(e);
        }
    }




}
