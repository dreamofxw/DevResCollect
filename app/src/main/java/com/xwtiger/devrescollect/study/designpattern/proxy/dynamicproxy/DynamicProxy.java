package com.xwtiger.devrescollect.study.designpattern.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by xwadmin on 2018/4/9.
 */

public class DynamicProxy implements InvocationHandler {

    Object object;
    public DynamicProxy(Object obj){
        this.object = obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(object, args);
        return invoke;
    }
}
