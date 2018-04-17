package com.xwtiger.devrescollect.study.designpattern.proxy;

/**
 * Created by xwadmin on 2018/4/9.
 */

public class RealUser implements IShop {
    
    @Override
    public void buy() {
        System.out.println("real user buy");
    }
}
