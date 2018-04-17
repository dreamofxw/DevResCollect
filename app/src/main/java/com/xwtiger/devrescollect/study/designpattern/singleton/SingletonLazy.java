package com.xwtiger.devrescollect.study.designpattern.singleton;

/**
 * Created by xwadmin on 2018/4/9.
 * 懒汉模式
 */

public class SingletonLazy {

    private static SingletonLazy instance;
    private SingletonLazy(){};
    public static synchronized SingletonLazy getInstance(){
        if(instance != null){
            instance = new SingletonLazy();
        }
        return instance;
    }
}
