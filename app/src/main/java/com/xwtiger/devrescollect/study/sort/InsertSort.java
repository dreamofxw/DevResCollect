package com.xwtiger.devrescollect.study.sort;

import java.util.Arrays;

/**
 * Created by xwadmin on 2018/1/6.
 * 直接插入排序
 *
 */

public class InsertSort {

    public static void main(String[] args){
        int a[] = {3,1,5,7,2,4,9,6,10,8};
        InsertSort  obj=new InsertSort();
        System.out.println("初始值：");
        obj.print(a);
        obj.insertSort(a);
        System.out.println("\n排序后：");
        obj.print(a);

        System.out.println("\n--------分割线-------");
        //test();
        // testSort();

    }

    public static void testSort(){
        int[] test = new int[]{3,6,1,9,56,89,123,4,754};

        print(test);

        for(int i = 1;i<test.length;i++){
            int j;
            int temp =test[i];
            for(j = i;j>0&&temp<test[j-1];j--){
                test[j] = test[j-1];
            }
            test[j]=temp;
        }
        print(test);
    }

    public void insertSort(int[] a) {
        for(int i=1;i<a.length;i++){//从头部第一个当做已经排好序的，把后面的一个一个的插到已经排好的列表中去。
            int j;
            int x=a[i];//x为待插入元素  {3,1,5,7,2,4,9,6,10,8}
            for( j=i;  j>0 && x<a[j-1];j--){//通过循环，逐个后移一位找到要插入的位置。
                a[j]=a[j-1];
            }
            a[j]=x;//插入
        }

    }






    public static void test(){
        int x = 2;
        for(int j =8;j<6;j++){
            System.out.println("j ="+j);
        }
    }

    public static void print(int a[]){
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }
    }

}
