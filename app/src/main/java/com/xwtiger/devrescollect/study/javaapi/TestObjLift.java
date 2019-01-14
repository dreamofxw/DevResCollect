package com.xwtiger.devrescollect.study.javaapi;

/**
 * author:xw
 * Date:2019-01-02 17:43
 * Description:
 * //<editor-fold desc="">
 * //</editor-fold>
 */
public class TestObjLift {



    static{
        System.out.println("静态代码块");
    }

    public String filed2 = getfiled2();

    public TestLifeBean testLifeBean = new TestLifeBean();

    {
        System.out.println("普通代码块");
    }
    public static String filed1 = getfiled1();



    TestObjLift(){
        System.out.println("构造方法");
    }


    public static void main(String[] args){
        new TestObjLift();
        System.out.println("----------------");
        new TestObjLift();
    }

    public static String getfiled1(){
        System.out.println("初始化静态成员");
        return "hahh";
    }
    public  String getfiled2(){
        System.out.println("初始化非静态成员");
        return "hahh";
    }



}
