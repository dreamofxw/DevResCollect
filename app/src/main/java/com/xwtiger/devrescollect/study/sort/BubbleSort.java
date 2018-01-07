package com.xwtiger.devrescollect.study.sort;

/**
 * Created by xwadmin on 2018/1/7.
 */

public class BubbleSort {

    public static void main(String[] args){

        //test();
        int a =0;
        System.out.println(a++);
        System.out.println(++a);

    }

    public static void test(){
        int[] arr = new int[]{89,5,6,1,563,4,90,234};
        bubbleSort(arr,arr.length);
        print(arr);
    }

    public static void bubbleSort(int a[], int n){
        for(int i =0 ; i< n-1; ++i) {
            for(int j = 0; j < n-i-1; ++j) {
                if(a[j] > a[j+1])
                {
                    int tmp = a[j] ; a[j] = a[j+1] ;  a[j+1] = tmp;
                }
            }
        }
    }

    public static void print(int a[]){
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }
    }
}
