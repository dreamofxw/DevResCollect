package com.xwtiger.devrescollect.study.thread;

/**
 * Created by xwadmin on 2018/3/6.
 */

public class EvenGenerator extends IntGeenerator {

    private int currentEvenValue = 0;
    @Override
    public int next() {
        ++ currentEvenValue;
        Thread.yield();
        ++ currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args){
        EvenChecker.test(new EvenGenerator());
    }
}
