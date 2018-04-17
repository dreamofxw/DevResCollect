package com.xwtiger.devrescollect.study.designpattern.singleton;

/**
 * Created by xwadmin on 2018/4/9.
 *
 * 饿汉模式
 */

public class SingletonHungry {

    private static SingletonHungry instance = new SingletonHungry();
    private SingletonHungry(){

    }
    public static SingletonHungry getInstance(){
        return instance;
    }

}
