package com.xwtiger.devrescollect.study.datastructuresandalgorithm;

/**
 * Created by Busap-112 on 2018/1/4.
 */

public class fig1_4 {



    /**
     * 打印整数的递归
     * @param n
     */
    public static void printout(int n){

        if(n>=10){
            printout(n/10);
        }else{
            printDigit(n%10);
        }
    }

    public static void printDigit(int n){
        System.out.println("n ="+n);
    }
}
