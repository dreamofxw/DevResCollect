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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

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
//        System.out.println(Double.MIN_NORMAL);
//        testRateForHouse();

        //testCurrent1();
        testAnd();
    }


    public static void testAnd(){
        int a = 1;//0001
        int b = 3;//0011
                   //0001
        System.out.println("& ="+(a&b));//1
        System.out.println("| ="+(a|b));//3
        System.out.println(Math.pow(2,10)-1);
    }


    public static void testCurrent3(){

        Stack<String> stack = new Stack<String>();
        for(int i =0;i<10;i++){
            stack.add("str"+i);
        }
        /*for(String str:stack){
            if(str.equals("str3")){
                stack.remove(str);
            }
            System.out.println("peek ="+str);
        }*/

        for(int i =0;i<stack.size();i++){
            String peek = stack.get(i);
            if(peek.equals("str3")){
                stack.remove(i);
                break;
            }
            System.out.println("peek ="+peek+"--i ="+i);
        }

        System.out.println("stack ="+stack.toString());
    }




    public static void testCurrent2(){

        ArrayList<String> array = new ArrayList<String>();

        // 创建并添加元素
        array.add("hello");
        array.add("world");
        array.add("java");
        array.add("java2");
        array.add("java3");
        array.add("java4");
        array.add("java5");
        System.out.println("array ="+array.toString());


//        for(String str:array){
//            if("java".equals(str)){
//                array.add(str);
//            }
//        }

        for(int i =0;i<array.size();i++){
            String str = array.get(i);
            if("java".equals(str)){
                array.remove(str);
                array.add("haha");
            }
        }
        System.out.println("after array ="+array.toString());
    }

    public static void testCurrent1(){

        ArrayList<String> array = new ArrayList<String>();

        // 创建并添加元素
        array.add("hello");
        array.add("world");
        array.add("java");
        System.out.println("array ="+array.toString());
        ListIterator it = array.listIterator();
        while (it.hasNext()) {
            String s = (String) it.next();
            if ("world".equals(s)) {
                it.add("javaee");
                //array.add("javaee");
            }
        }
        System.out.println("after array ="+array.toString());
    }



    private static List<String> list = new ArrayList<String>();

    public  synchronized static void addlist(){
        for(int i =0;i<20;i++){
            list.add("string"+i);
            System.out.println("addlist "+list.toString());
        }
    }

    public static void removeList(){
        for(int i =0;i<20;i++){
            if(i<list.size()){
                list.remove(i);
            }
            System.out.println("removeList "+list.toString());
        }
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
