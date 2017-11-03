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
