package com.xwtiger.devrescollect.bean;

/**
 * Created by xwadmin on 2018/3/5.
 */

public class TestFinalize {

    public boolean ischeck;

    public TestFinalize(boolean ischeck){
        this.ischeck = ischeck;
    }

    @Override
    protected void finalize() throws Throwable {
        //super.finalize();
        if(ischeck){
            System.out.println("ischeck:"+ischeck);
        }
    }
    
    
    protected void testAdd(){
        System.out.println("test add");
    }
    
    private void testpri(){
        System.out.println("testpri");
    }

    public void testPub(){
        System.out.println("testpub");
    }

}


