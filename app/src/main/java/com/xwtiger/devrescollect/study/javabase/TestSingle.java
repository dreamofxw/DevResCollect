package com.xwtiger.devrescollect.study.javabase;

/**
 * Created by xwadmin on 2018/3/21.
 */

public class TestSingle {

    public static TestBean testBean = new TestBean();

    private TestSingle(){
        System.out.println("执行构造方法....");
    }
    
    public static TestSingle getInstance(){
        return Test.testSingle;
    }
    
    private static class Test{
        private static TestSingle testSingle = new TestSingle();

    }
    
}
