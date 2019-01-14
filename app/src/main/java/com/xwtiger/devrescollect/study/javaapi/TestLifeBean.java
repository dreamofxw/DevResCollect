package com.xwtiger.devrescollect.study.javaapi;

/**
 * author:xw
 * Date:2019-01-02 18:07
 * Description:
 * //<editor-fold desc="">
 * //</editor-fold>
 */
public class TestLifeBean {

    static{
        System.out.println("TestLifeBean 静态成员");
    }

    {
        System.out.println("TestLifeBean 非静态成员");
    }

    public TestLifeBean(){
        System.out.println("TestLifeBean 构造方法");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
