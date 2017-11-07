package com.xwtiger.devrescollect.study.javaapi;

/**
 * (一)
 * Created by Busap-112 on 2017/11/1.
 * java 基本数据类型
 * 第一类:逻辑型boolean
 * 第二类:文本型char
 * 第三类:整数型(byte、short、int、long)
 * 第四类:浮点型(float、double)
 *
 *            字节
 *boolean
 *byte         1
 *char         2
 *short        2
 *int          4
 *float        4
 *double       8
 *long         8
 *
 *
 * (二 )
 * 在java 1.5的java.util.concurrent.atomic包下提供了一些原子操作类，
 * 即对基本数据类型的 自增（加1操作），自减（减1操作）、以及加法操作（加一个数），
 * 减法操作（减一个数）进行了封装，保证这些操作是原子性操作
 *
 *
 */

public class JiBenShuluLeiXing {

    /**
     *
     * 1111 1111
     * 256 128 32 16  8 4 2 1
     */
    public static void main(String[] args){

        System.out.println("result = "+(comparedByte(15)));
        System.out.println("pow ="+(Math.pow(2,15)-1));

        //2 147 483 647
        /*result = 2147483647
        pow =2.147483647E9*/

    }

    public static void test(){
       // byte b = 128;

    }

    /**
     *
     * 计算字节的和
     * @param bytelenth
     * @return
     *
     */
    public static int comparedByte(int bytelenth){
        int result =0;
        for(int i =0;i<bytelenth;i++){
            int temp = (int) Math.pow(2, i);
            System.out.println("i ="+i+"temp ="+temp);
            result +=temp;
        }
        return result;
    }

}
