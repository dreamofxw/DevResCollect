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

        int[][] a ={{0,1,0,0,1,1},
                    {0,0,1,0,0,1},
                    {0,0,0,1,0,0},
                    {0,0,0,0,1,0}};
//        flippingImg(a);

        //怎么分组
        //取每组的最小值 相加得到一个最大的数
//        int result = arrayPairSum(input);
//        System.out.println("result ="+result);

        System.out.println("boolean is "+isToeplitzMatrix(a));

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


    public static int arrayPairSum(int[] nums) {
        int[] exist = new int[20001];
        for (int i = 0; i < nums.length; i++) {
            exist[nums[i] + 10000]++;
        }
        int sum = 0;
        boolean odd = true;
        for (int i = 0; i < exist.length; i++) {
            while (exist[i] > 0) {
                if (odd) {
                    sum += i - 10000;
                }
                odd = !odd;
                exist[i]--;
            }
        }
        return sum;

    }


    /**
     *
     * 1 1 1 1
     * 2 2 2 2
     * 3 3 3 3
     * 4 4 4 4
     *
     * @param matrix
     * @return
     */
    public static boolean isToeplitzMatrix(int[][] matrix) {
        boolean result = true;
        a:for(int i=matrix.length-1;i<matrix.length&&i>0;i--){
            if(i==matrix.length-1){
                b:for(int j=0;j<matrix[i].length;j++){
                    int temp1 =matrix[i][j];
                    int column = j;
                    int row = i;
                    while(row-1>=0&&column-1>=0){
                        int temp2 = matrix[row-1][column-1];
                        if(temp2 !=temp1){
                            result = false;
                            return result;
                        }
                        row--;
                        column--;
                    }
                }
            }else{
                int column =matrix[i].length-1;
                int temp1 =matrix[i][column];
                int row = i;
                while(row-1>=0&&column-1>=0){
                    int temp2 = matrix[row-1][column-1];
                    if(temp2 != temp1){
                        result = false;
                        return result;
                    }
                    row--;
                    column--;
                }

            }
        }
        return result;
    }


    /**
     *
     * @param nums
     * @param r
     * @param c
     * @return
     */
    public int[][] matrixReshape(int[][] nums, int r, int c) {

        if(nums != null && nums.length>0){
            int temp_r = nums.length;
            int temp_c = nums[0].length;



        }

        return null;
    }




}
