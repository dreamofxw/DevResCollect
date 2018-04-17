package com.xwtiger.devrescollect.study.designpattern.singleton;

/**
 * Created by xwadmin on 2018/4/9.
 * 双重检查
 */

public class SingletonDcl {

    private static volatile SingletonDcl instance;
    private SingletonDcl(){}

    public static SingletonDcl getInstance(){
        if(instance == null){
            synchronized (SingletonDcl.class){
                if(instance == null){
                    instance = new SingletonDcl();
                }
            }
        }
        return instance;
    }

}
