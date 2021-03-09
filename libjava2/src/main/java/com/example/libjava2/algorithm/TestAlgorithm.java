package com.example.libjava2.algorithm;

import java.util.Arrays;
import java.util.List;

/**
 * 归并排序
 */
public class TestAlgorithm {

    public static void main(String[] args) {
        int[] arr = new int[]{10,9,20,30,8,3,5};
        int[] ints = Arrays.copyOfRange(arr, 7, arr.length);
        System.out.println("ints.length ="+ints.length);
        try {
            int[] sort = sort(arr);
            for(int i:sort){
                System.out.print(i+",");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



     public static int[] sort(int[] sourceArray) throws Exception {
                // 对 arr 进行拷贝，不改变参数内容
               int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
               System.out.println("-----------分界线-----------------");
               printArr(arr,"源文件");
                if (arr.length < 2) {
                        return arr;
                   }
                int middle = (int) Math.floor(arr.length / 2);

                int[] left = Arrays.copyOfRange(arr, 0, middle);
                int[] right = Arrays.copyOfRange(arr, middle, arr.length);
                printArr(left,"left=");
                printArr(right,"right=");
               return merge(sort(left), sort(right));
            }

            protected static int[] merge(int[] left, int[] right) {
               printArr(left,"merge left =");
               printArr(right,"merge right =");
               int[] result = new int[left.length + right.length];
                int i = 0;
               while (left.length > 0 && right.length > 0) {
                        if (left[0] <= right[0]) {
                               result[i++] = left[0];
                                left = Arrays.copyOfRange(left, 1, left.length);
                            } else {
                                result[i++] = right[0];
                                right = Arrays.copyOfRange(right, 1, right.length);
                            }
                   }

                while (left.length > 0) {
                        result[i++] = left[0];
                        left = Arrays.copyOfRange(left, 1, left.length);
                   }

                while (right.length > 0) {
                        result[i++] = right[0];
                        right = Arrays.copyOfRange(right, 1, right.length);
                    }

               return result;
            }


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

}
