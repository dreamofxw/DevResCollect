package com.xwtiger.devrescollect;

import android.provider.Settings;
import android.util.Log;

import com.xwtiger.devrescollect.study.javaapi.LogUtils;
import com.xwtiger.devrescollect.study.javaapi.PatternStudy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * Created by Busap-112 on 2017/11/1.
 *
 */
public class TestJava {



    public static void main(String[] args){
//        testReg1(PatternStudy.REG_GRE);
//        testReg1(PatternStudy.REG_REL);
//        testReg1(PatternStudy.REG_POS);

//        Thread[] allThreads = findAllThreads();
//        for (int i=0;i<allThreads.length;i++){
//
//            System.out.println("threadName ="+allThreads[i].getName());
//        }
        System.out.println(Double.MIN_NORMAL);
        testRateForHouse();

    }

    public static void test(){

        double tp = 510000;
        double mr = 0.0465/12;
        double x = 3267.95;
        double result = tp*(1+mr)-x;
        System.out.println(String.valueOf(result));

    }


    public static  void testRateForHouse(){
        double x = 3267.95;
        double value = computeA() -x*computeB();
        System.out.println("value "+value);
    }



    public static double computeA(){
        double mr = 0.0465/12;
        double result =1;
        double tp = 510000;
        for(double i = 1;i<241;i++){
            result +=Math.pow(mr,i);
            System.out.println("result i="+result);
        }
        System.out.println("computeA ="+result);
        return tp*result;
    }

    public static double computeB(){
        double mr = 0.0465/12;
        double result = 1;
        for(double i = 1;i<240;i++){
            result += Math.pow(mr,i);
        }
        System.out.println("computeB ="+result);
        return result;
    }


    public static void testReg1(String reg){
        long startTime = System.currentTimeMillis();
        PatternStudy.regex3(reg,"123456f005f00");
        long enttime = System.currentTimeMillis() -startTime;
        System.out.println("reg = "+reg);
        System.out.println("enttime = "+enttime);
        System.out.println("------分割线--------");
    }



}
