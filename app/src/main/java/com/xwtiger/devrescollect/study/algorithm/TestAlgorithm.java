package com.xwtiger.devrescollect.study.algorithm;

/**
 * Created by Busap-112 on 2018/1/2.
 */

public class TestAlgorithm {

    public static void main(String[] args){
        /*int nDisks = 4;
        doTowers(nDisks, 'A', 'B', 'C');*/
        System.out.println(sum(4));
        System.out.println("add a character c from server");
        System.out.println("add a new line");
        float x =0,y =0;
        x ++;
        int server = 0;
        int a=1,b=2;
        System.out.println("chang a and b");
        System.out.println("add a character c from local");

    }

    /**
     * 河内之塔 2^n -1
     */
    public static void doTowers(int topN, char from,char inter, char to){
        if (topN == 1){
            System.out.println("盘子 1 从 "+ from + " 到 " + to);
        }else {
            doTowers(topN - 1, from, to, inter);
            System.out.println("盘子  "+ topN + " 从 " + from + " 到 " + to);
            doTowers(topN - 1, inter, from, to);
        }
    }

    /**
     *
     * @param n
     * @return
     */
    public static int sum(int n){
        if(n<=2){
            return 1;
        }else{
            return sum(n-1)+sum(n-2);
        }
    }

}
