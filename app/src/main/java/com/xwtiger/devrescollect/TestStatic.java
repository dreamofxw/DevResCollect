package com.xwtiger.devrescollect;

/**
 * author:xw
 * Date:2018-12-26 17:09
 * Description:
 * //<editor-fold desc="">
 * //</editor-fold>
 */
public class TestStatic {

    public final static int a = 10;
    static {
        System.out.println("类初始化");
    }
    
    public TestStatic(){
        System.out.println("teststatic 构造方法");
    }
    
    public static void testMethod(){
        System.out.println("testMethod");
    }
}
