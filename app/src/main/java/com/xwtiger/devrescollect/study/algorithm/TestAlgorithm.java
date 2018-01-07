package com.xwtiger.devrescollect.study.algorithm;

/**
 * Created by Busap-112 on 2018/1/2.
 */

public class TestAlgorithm {

    public static void main(String[] args){
        /*int nDisks = 4;
        doTowers(nDisks, 'A', 'B', 'C');*/
        /*System.out.println(sum(4));*/
        testNum();

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

    /**
     * 求素数
     * 质数又称素数。一个大于1的自然数，除了1和它自身外，
     * 不能被其他自然数整除的数叫做质数；否则称为合数
     */
    public static void testNum(){
        for(int i=2;i<=200;i++){
            boolean flag=true;
            for(int j=2;j<i;j++){
                if(i%j==0){
                    flag=false;
                    break;
                }
            }
            if(flag==true){
                System.out.print(" "+i);
            }
        }
    }

    /**
     * 所谓 水仙花数 是指一个三位数，其各位数字立方和等于该数本身
     */
    public static boolean testNumer2() {
        for (int i = 100; i < 1000; i++) {
            int a = i / 100;
            int j = (i % 100) / 10;
            int k = i % 10;
            int h = (int) (Math.pow(a, 3) + Math.pow(j, 3) + Math.pow(k, 3));
            if (h == i) {
                return true;
            }
        }
        return false;
    }




}
