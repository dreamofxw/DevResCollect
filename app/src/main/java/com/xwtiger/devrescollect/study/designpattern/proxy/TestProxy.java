package com.xwtiger.devrescollect.study.designpattern.proxy;

import com.xwtiger.devrescollect.study.designpattern.proxy.dynamicproxy.DynamicProxy;

import java.lang.reflect.Proxy;

/**
 * Created by xwadmin on 2018/4/9.
 */

public class TestProxy {

    public static void main(String[] args){
        /*IShop iShop = new RealUser();
        IShop proxyUser = new ProxyUser(iShop);
        proxyUser.buy();*/
        IShop shop = new RealUser();
        DynamicProxy proxy = new DynamicProxy(shop);
        ClassLoader classLoader = shop.getClass().getClassLoader();
        IShop proxyobj = (IShop) Proxy.newProxyInstance(classLoader, new Class[]{IShop.class}, proxy);
        proxyobj.buy();

    }

}
