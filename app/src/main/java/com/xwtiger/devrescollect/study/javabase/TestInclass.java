package com.xwtiger.devrescollect.study.javabase;

/**
 * Created by xwadmin on 2018/3/20.
 *
 * 成员内部类
 静态内部类
 局部内部类
 匿名内部类
 */

public class TestInclass {

    public int num2 = 10;
    private String str = "hahaha";

     class InClass{
        public int num = 20;
        public void test(){
            int num = 30;
            System.out.println("test.num ="+num);
            System.out.println("inclass.num = "+this.num);
            System.out.println("outclass.num ="+num2);
            System.out.println(str);
        }


    }



}
