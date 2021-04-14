package com.example.libjava2.leetcode;
import com.example.libjava2.algorithm.PrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


class TestMain {


    public static void main(String[] args) {

        //System.out.println("result = "+integerBreak(58));
        //System.out.println("result = "+subsets(new int[]{2,3,5}));
//        List<List<Integer>> permute = permute1(new int[]{2, 3, 5});
//        System.out.println("result = "+(permute !=null?permute:"null"));

        //printarr();
        gameOfLife(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
    }


    /**
     * 实现一种算法，删除单向链表中间的某个节点（即不是第一个或最后一个节点），假定你只能访问该节点。
     *
     * 示例：
     *
     * 输入：单向链表a->b->c->d->e->f中的节点c
     * 结果：不返回任何数据，但该链表变为a->b->d->e->f
     */
    public static void test1(){


    }

    /**
     * 整数拆解
     * @param n
     * @return
     */
    public static int integerBreak(int n) {
        int[] dp = new int[n + 1];
        System.out.println("start === ,length ="+dp.length+",dp[0]="+dp[0]);
        for (int i = 2; i <= n; i++) {
            int curMax = 0;
            for (int j = 1; j < i; j++) {
                curMax = Math.max(curMax, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = curMax;
            System.out.println("dp["+i+"] ="+curMax);
        }
        return dp[n];
    }

    /**
     * 子集
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList();
        output.add(new ArrayList<Integer>());
        System.out.println("初始化："+output);
        for (int num : nums) {
            List<List<Integer>> newSubsets = new ArrayList();
            for (List<Integer> curr : output) {
                newSubsets.add(new ArrayList<Integer>(curr){{add(num);
                }});
            }
            for (List<Integer> curr : newSubsets) {
                output.add(curr);
            }
        }
        return output;
    }


    /**
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        if (nums.length <= 0) {
            return result;
        }
        if (nums.length == 1) {
            List<Integer> t = new LinkedList<>();
            t.add(nums[0]);
            result.add(t);
            return result;
        }
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> remainder = permute1(remove(i,nums));
            System.out.println("remainder ="+remainder);
            for (List<Integer> integers : remainder) {
                integers.add(0, nums[i]);
                result.add(integers);
            }
        }

        return result;
    }


    private static int[] remove(int index, int[] src) {
        int[] result = new int[src.length - 1];
        for (int i = 0,j = 0; i < src.length; i++) {
            if (i != index) {
                result[j] = src[i];
                j++;
            }
        }
        return result;
    }


    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        StringBuilder sb = new StringBuilder();
        for(int i=n;i>0;i--){


                for(int j=0;j<n;j++){
                    sb.append("(");
                }
                for(int j=0;j<n;j++){
                    sb.append(")");
                }

        }
        return null;
    }


//    public static void back_tracking(int row)    //算法函数，从第0行开始遍历
//    {
//        if (row==n)
//            t ++;               //判断若遍历完成，就进行计数
//        for (int col=0;col<n;col++)     //遍历棋盘每一列
//        {
//            queen[row] = col;           //将皇后的位置记录在数组
//            if (is_ok(row))             //判断皇后的位置是否有冲突
//                back_tracking(row+1);   //递归，计算下一个皇后的位置
//        }
//    }
//
//    public static boolean is_ok(int row){            //判断设置的皇后是否在同一行，同一列，或者同一斜线上
//        for (int j=0;j<row;j++)
//        {
//            if (queen[row]==queen[j]||row-queen[row]==j-queen[j]||row+queen[row]==j+queen[j])
//                return false;
//        }
//        return true;
//    }


     public static void printarr(){
        String[][] str = new String[][]{{"a","b","c","d"},{"1","2"},{"A","B","C","D"}};
        String[][] str2 = str;
        System.out.println("length ="+str.length);
        System.out.println("第二个 = "+str2.length);
        for(int i=0;i<str2.length;i++){
            System.out.println( Arrays.asList(str2[i]));
        }
     }

    /**
     *生命游戏，简称为生命
     * 输入：
     * [
     *   [0,1,0],
     *   [0,0,1],
     *   [1,1,1],
     *   [0,0,0]
     * ]
     * 输出：
     * [
     *   [0,0,0],
     *   [1,0,1],
     *   [0,1,1],
     *   [0,1,0]
     * ]
     *
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param board
     */
    public static void gameOfLife(int[][] board) {
        PrintUtils.printArrs(board,"test:");
        if(board ==null || board.length==0){
            return;
        }
        int[][] newArr = new int[board.length][board[0].length];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                int top,leftTop,rightTop,bottom,leftBottom,rightBottom,left,right;
                int num =0;
                if(i-1>0){
                    //上面的行，左上，右上
                    //if()
                }
                if(i+1<board.length){
                    //下面的行，左下，右下

                }

                if(num<2){
                    //newArr[i][j]= board[i][j];
                    newArr[i][j]= 0;
                }else if(num ==2 || num ==3){
                    if(board[i][j] ==1){
                        newArr[i][j]= 1;
                    }else{
                        if(num ==3){
                            newArr[i][j]= 1;
                        }else{
                            newArr[i][j]= 0;
                        }
                    }
                }else{
                    newArr[i][j]= 0;
                }

            }
        }

        PrintUtils.printArrs(newArr,"新生成的数组：");
    }


    /**
     *
     * 计算周围
     *
     */
    public static void computerAround(int[][] arr,int position){

    }




}




