package com.test.xuww.libjava.testannotation;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import sun.rmi.runtime.Log;

/**
 * @author : 胥文
 * @date : 2020/10/11 20:58
 * @desc :
 */

public class LogUtils implements IListener{

    public boolean isDebug = false;

    public void logd(String tag){
        System.out.println("logd");
    }

    public  boolean check(Method method, Object[] objects)  {
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i <parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (parameter.isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation annotation = parameter.getAnnotation(MyAnnotation.class);
                if (isDebug) {

                    return false;
                }

            }
        }
        return true;
    }


    public void test(String args){
        if(isDebug){
            Log.d(args);
        }
    }

}
