package com.xwtiger.devrescollect.mvp.utils;

import com.xwtiger.devrescollect.MyException;

import java.lang.reflect.ParameterizedType;

public class TUtil {
    public static <T> T getT(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            MyException.printStr(e);
        } catch (IllegalAccessException e) {
            MyException.printStr(e);
        } catch (ClassCastException e) {
            MyException.printStr(e);
        }
        return null;
    }
 
    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            MyException.printStr(e);
        }
        return null;
    }
}
