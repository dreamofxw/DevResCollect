package com.example.libjava2.algorithm;

import java.util.Arrays;

public class TestShellSort {

    public static void main(String[] args) throws Exception {
        int[] arr = new int[]{2,5,7,8,4,1,9,3,6,10};
        int[] sort = sort(arr);
        PrintUtils.printArr(sort,"result = ");
    }

    public static int[] sort(int[] sourceArray) throws Exception {
                // 对 arr 进行拷贝，不改变参数内容
                int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
                System.out.println("arr.length ="+arr.length);
                int gap = 1;
                while (gap < arr.length) {
                        gap = gap * 3 + 1;
                    }
                System.out.println("gap="+gap);
                while (gap > 0) {
                    ///2,5,7,8, 4,1,9,3,6,10
                      System.out.println("gap = "+gap+"-------------分界线");
                        for (int i = gap; i < arr.length; i++) {
                                int tmp = arr[i];//1
                                int j = i - gap;//1
                                while (j >= 0 && arr[j] > tmp) {
                                        arr[j + gap] = arr[j];
                                        j -= gap;
                                    }
                                arr[j + gap] = tmp;
                                PrintUtils.printArr(arr,"i="+i+":情况");
                            }
                        gap = (int) Math.floor(gap / 3);
                    }

               return arr;
            }

}
