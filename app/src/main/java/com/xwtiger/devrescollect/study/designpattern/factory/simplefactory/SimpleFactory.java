package com.xwtiger.devrescollect.study.designpattern.factory.simplefactory;

/**
 * Created by xwadmin on 2018/4/9.
 */

public class SimpleFactory {

    public static Computer createComputer(String type){
        Computer computer = null;
        switch (type){
            case "lenovo":
                computer = new LenovoComputer();
                break;
            case "hp":
                computer = new HpComputer();
                break;
            case "asus":
                computer = new AsusCompute();
                break;
        }

        return computer;
    }
}
