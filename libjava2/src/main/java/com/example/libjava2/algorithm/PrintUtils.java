package com.example.libjava2.algorithm;

public class PrintUtils {
    public static void printArr(int[] arr,String des){
        StringBuilder sb = new StringBuilder();
        sb.append(des);
        for(int i=0;i<arr.length;i++){
            if(i == arr.length-1){
                sb.append(arr[i]);
            }else{
                sb.append(arr[i]+",");
            }
        }
        System.out.println(sb.toString());
    }

    public static void printArrs(int[][] arr,String des){
        StringBuilder sb = new StringBuilder();
        sb.append(des);
        sb.append("[");
        for(int i=0;i<arr.length;i++){
            if(arr[i] !=null){
                for(int j =0;j<arr[i].length;j++){
                    if(j==0){
                        sb.append("[");
                    }
                    if(j != arr[i].length-1){
                        sb.append(arr[i][j]+",");
                    }else{
                        sb.append(arr[i][j]+"]");
                    }
                }
            }
            if(i !=arr.length -1){
                sb.append(" ,");
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}
