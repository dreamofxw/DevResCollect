package com.xwtiger.devrescollect.study.sort;

/**
 * Created by xwadmin on 2018/1/6.
 * 选择排序 --简单选择排序
 *
 */

public class SimpleSelectSort {

    public static void main(String[] args) {
        int a[] = {3,1,5,150,7,2,11,23,200,4,9,6,12,10,8,1000};
        SimpleSelectSort  obj=new SimpleSelectSort();
        System.out.println("初始值：");
        obj.print(a);
        /*obj.selectSort(a);
        System.out.println("\n排序后：");
        obj.print(a);*/

        System.out.println("\n新选择排序：");
        obj.SelectSort(a,a.length);
        obj.print(a);
    }


    private void selectSort(int[] a) {
        for(int i=0;i<a.length;i++){
            int k=i;//k存放最小值下标。每次循环最小值下标+1
            for(int j=i+1;j<a.length;j++){//找到最小值下标
                if(a[k]>a[j])
                    k=j;
            }
            swap(a,k,i);//把最小值放到它该放的位置上
        }
    }

    /**
     *
     * 简单选择排序的改进——二元选择排序
     *简单选择排序，每趟循环只能确定一个元素排序后的定位。
     * 我们可以考虑改进为每趟循环确定两个元素（当前趟最大和最小记录）的位置,
     * 从而减少排序所需的循环次数。改进后对n个数据进行排序
     * ，最多只需进行[n/2]趟循环即可
     * @param r
     * @param n
     */
    void SelectSort(int r[],int n) {
        int i ,j , min ,max, tmp;
        for (i=1 ;i <= n/2;i++) {
            // 做不超过n/2趟选择排序
            min = i; max = i ; //分别记录最大和最小关键字记录位置
            for (j= i+1; j<= n-i; j++) {
                if (r[j] > r[max]) {
                    max = j ; continue ;
                }
                if (r[j]< r[min]) {
                    min = j ;
                }
            }
            //该交换操作还可分情况讨论以提高效率
            if(min == max){
                if(r[min] <r[i-1]){
                    tmp = r[i-1];
                    r[i-1] = r[min];
                    r[min]= tmp;
                }
                if(r[max] >r[i+1]){
                    tmp = r[i+1];
                    r[i+1] = r[max];
                    r[max] = tmp;
                }
            }else{
                tmp = r[i-1]; r[i-1] = r[min]; r[min] = tmp;
                tmp = r[n-i]; r[n-i] = r[max]; r[max] = tmp;
            }

            System.out.println("\n互换之后：");
            print(r);
        }
    }

    public void print(int a[]){
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }
    }
    public  void swap(int[] data, int i, int j) {
        if (i == j) {
            return;
        }
        data[i] = data[i] + data[j];
        data[j] = data[i] - data[j];
        data[i] = data[i] - data[j];
    }

}
