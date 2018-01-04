package com.xwtiger.devrescollect.study.datastructuresandalgorithm;

/**
 * Created by Busap-112 on 2018/1/4.
 */

public class fig1_2 {

    public static int f(int x){
        if(x ==0){
            return 0;
        }else{
            return 2*f(x-1)+x*x;
        }
    }

}
