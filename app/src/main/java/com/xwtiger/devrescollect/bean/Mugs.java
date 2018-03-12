package com.xwtiger.devrescollect.bean;

/**
 * Created by xwadmin on 2018/3/5.
 */

public class Mugs {

    public static void main(String[] args){
        System.out.println("invoke main method");
        Mug.test1();
    }
     public Mug mug = new Mug();

    public Mugs(){
        System.out.println("Mugs init");
    }
}
