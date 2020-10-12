package com.test.xuww.libjava.testannotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author : 胥文
 * @date : 2020/10/11 20:41
 * @desc :
 */

public class HelloServiceCgLib implements InvocationHandler {
    public boolean isDebug = false;

    private Object object;
    public HelloServiceCgLib(Object object){
        this.object = object;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("Before invoke "+ method.getName());
        method.invoke(object, objects);
        System.out.println("After invoke "+ method.getName());
        return null;
    }
}
