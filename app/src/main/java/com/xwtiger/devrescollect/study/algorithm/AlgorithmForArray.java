package com.xwtiger.devrescollect.study.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * author:xw
 * Date:2018-06-28 12:48
 * Description: 数组相关的算法题
 */
public class AlgorithmForArray {

    public static void main(String[] args){

//        int[][] a ={{1,1,0,0,1,1},{1,0,1,1,0,1},{0,0,0,1,0,1}};
//        flippingImg(a);



    }

    /**
     *Input: [[1,1,0],[1,0,1],[0,0,0]]
     Output: [[1,0,0],[0,1,0],[1,1,1]]
     Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
     Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]
     * @param i
     */
    public static void flippingImg(int[][] i){
        if(i != null && i.length>0){
            for(int j=0;j<i.length;j++){
                int length = i[j].length;
                int fact = length%2==0?length/2:length/2+1;
                for(int k=0;k<fact;k++){
                    if(k !=(length-1-k)){
                        int temp = i[j][k];
                        i[j][k] = i[j][length-1-k];
                        i[j][length-1-k] = temp;
                        i[j][k] =i[j][k]>0?0:1;
                        i[j][length-1-k] =i[j][length-1-k]>0?0:1;
                    }else{
                        //中间相等的第一步不用替换
                        //invert
                        i[j][k] = i[j][k]>0?0:1;
                    }
                }
            }
        }
        printArray(i);
    }

    public static void printArray(int[][] i){
        if(i != null&&i.length>0){
            for(int j=0;j<i.length;j++){
                for(int k=0;k<i[j].length;k++){
                    System.out.print(""+i[j][k]);
                }
                System.out.println("-------------");
            }
        }
    }




}
