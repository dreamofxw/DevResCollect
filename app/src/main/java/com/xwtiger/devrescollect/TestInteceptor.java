package com.xwtiger.devrescollect;

/**
 * author:xw
 * Date:2018-10-27 18:18
 * Description:
 * //<editor-fold desc="">
 * //</editor-fold>
 */
public class TestInteceptor {

    private  int position;

    public TestInteceptor(int position){
        this.position = position;
    }


    public String interceptor(TestChain chain){
        System.out.println("interceptor ="+position);
        return chain.process();
    }
}
