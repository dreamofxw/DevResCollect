package com.xwtiger.devrescollect.study.designpattern.proxy;

/**
 * Created by xwadmin on 2018/4/9.
 */

public class ProxyUser implements IShop {
    private IShop mRealUser;
    public ProxyUser(IShop realUser){
        this.mRealUser = realUser;
    }

    @Override
    public void buy() {
        mRealUser.buy();
    }
}
