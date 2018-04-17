package com.xwtiger.devrescollect.study.designpattern.singleton;

/**
 * Created by xwadmin on 2018/4/9.
 * 静态内部类模式
 */
public class SingletonInner {

    private SingletonInner(){};

    private static class SingletonHolder{
        private static final SingletonInner instance = new SingletonInner();
    }

    public static SingletonInner getInstance(){
        return SingletonHolder.instance;
    }

}
