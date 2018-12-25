package com.xwtiger.devrescollect;

import java.util.List;

/**
 * author:xw
 * Date:2018-10-27 18:13
 * Description:
 * //<editor-fold desc="">
 * //</editor-fold>
 */
public class TestChain {

    public List<TestInteceptor> list;
    public int index;
    public TestChain(List<TestInteceptor> list,int index){
        this.list = list;
        this.index = index;
    }

    public String process(){
        System.out.println("process");
        if(index >9){
            return "";
        }

        TestChain next = new TestChain(list,index+1);
        TestInteceptor testInteceptor = list.get(index);

        return testInteceptor.interceptor(next);
    }
}
