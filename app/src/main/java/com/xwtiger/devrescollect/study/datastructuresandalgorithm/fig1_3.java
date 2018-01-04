package com.xwtiger.devrescollect.study.datastructuresandalgorithm;

/**
 * Created by Busap-112 on 2018/1/4.
 */

public class fig1_3 {
    public static int bad(int n){
        if(n == 0){
            return 0;
        }else{
            return bad(n/3+1)+n-1;
        }
    }
}
