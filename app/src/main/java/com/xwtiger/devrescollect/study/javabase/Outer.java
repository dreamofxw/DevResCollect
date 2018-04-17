package com.xwtiger.devrescollect.study.javabase;

/**
 * Created by xwadmin on 2018/3/20.
 */

public class Outer {

    public static Inter method(){
        return new InterImpl();
    }
    public static class InterImpl implements Inter{
        @Override
        public void show() {
            System.out.println("Hello World");
        }
    }
}
