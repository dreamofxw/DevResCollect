package com.xwtiger.devrescollect.study.javabase;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * 位运算相关的
 *
 * 1，当减一的时候
 *   eg:8(1000) ,减一之后7（0111）
 *     16（10000）,减一之后15（1111）
 *
 * 2，～表示取反eg:~8(0111) (注释：取反和减一效果一样)
 *
 *
 * 3，计算机对有符号数（包括浮点数）的表示有三种方法：原码、反码和补码， 补码=反码+1。
 * 在 二进制里，是用 0 和 1 来表示正负的，最高位为符号位，最高位为 1 代表负数，最高位为 0 代表正数
 *
 * 以负数-5为例：
   a.先将-5的绝对值转换成二进制，即为0000 0101；
   b.然后求该二进制的反码，即为 1111 1010；
   c.最后将反码加1，即为：1111 1011
 *
 * 
 *
 *
 */
public class BitOperation {

//    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private final AtomicInteger ctl = new AtomicInteger(0);

    private static final int CAPACITY   = (1 << 29) - 1;


    private static final int RUNNING    = -1 << 29;


    private static int ctlOf(int rs, int wc) { return rs | wc; }



    public static void main(String[] args){


        BitOperation bitOperation = new BitOperation();
        bitOperation.testThread();

    }

    public void testThread(){

//        System.out.println("start ="+ctl.get());
//        int i = ctl.get();
//        System.out.println("二进 "+Integer.toBinaryString(i));
//        System.out.println("二进 capacity"+Integer.toBinaryString(BitOperation.CAPACITY));
//
//        System.out.println("&"+(i&BitOperation.CAPACITY));
//        System.out.println("~&"+(i&(~(BitOperation.CAPACITY))));
//
//        System.out.println("---------");
//        ctl.compareAndSet(i,i+1);
//        System.out.println("after = "+ctl.get());
//        System.out.println("二进 "+Integer.toBinaryString(ctl.get()));
//        System.out.println("&"+(ctl.get()&(BitOperation.CAPACITY)));
//        System.out.println("~&"+(ctl.get()&(~(BitOperation.CAPACITY))));



        System.out.println(ctl.compareAndSet(0,2));
        System.out.println(ctl.compareAndSet(3,4));
        System.out.println(ctl.get());

    }

}
