package com.xwtiger.devrescollect.study.sort;

/**
 * Created by xwadmin on 2018/1/7.
 *
 * 堆排序 --
 * 从算法描述来看，堆排序需要两个过程，
 * 一是建立堆，二是堆顶与堆的最后一个元素交换位置。
 * 所以堆排序有两个函数组成。一是建堆的渗透函数，二是反复调用渗透函数实现排序的函数
 */

public class HeapSort {

    public static void main(String[] args) {
        int a[] = {3,1,5,7,2,4,9,6,10,8};
        HeapSort  obj=new HeapSort();
        System.out.println("初始值：");
        obj.print(a);

        for(int i=0;i<a.length;i++){
            obj.createLittleHeap(a,a.length-1-i);//创建堆,创建的是小顶堆。每次循环完，二叉树的根节点都是最小值，所以与此时的未排好部分最后一个值交换位置
            obj.swap(a, 0, a.length - 1 - i);//与最后一个值交换位置，最小值找到了位置
            obj.print(a);
            System.out.println();

        }

        System.out.println("\n排序后：");
        obj.print(a);

    }
    /*
     * 创建小顶堆：双亲节点小于子节点的值。从叶子节点开始，直到根节点。这样建立的堆定位最小值
     */
    private void createLittleHeap(int[] data, int last) {
        for (int i = (last- 1) / 2; i >= 0; i--) {  //找到最后一个叶子节点的双亲节点
            // 保存当前正在判断的节点
            int parent = i;
            // 若当前节点的左子节点存在，即子节点存在
            while (2 * parent + 1 <= last) {
                // biggerIndex总是记录较大节点的值,先赋值为当前判断节点的左子节点
                int bigger = 2 * parent + 1;//bigger指向左子节点
                if (bigger < last) { //说明存在右子节点

                    if (data[bigger] > data[bigger+ 1]) { //右子节点>左子节点时

                        bigger=bigger+1;
                    }
                }
                if (data[parent] > data[bigger]) {  //若双亲节点值大于子节点中最大的
                    // 若当前节点值比子节点最大值小，则交换2者得值，交换后将biggerIndex值赋值给k
                    swap(data, parent, bigger);
                    parent = bigger;
                } else {
                    break;
                }
            }
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
