package com.xwtiger.devrescollect.study.javaapi;

import com.xwtiger.devrescollect.study.designpattern.proxy.dynamicproxy.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * author:xw
 * Date:2018-12-27 16:37
 * Description:
 * //<editor-fold desc="">
 * //</editor-fold>
 */
public class DynamicProxyTest {


    public String test= "hahah";

    public static void main(String[] args){

        ISubject subject = new RealSubject();
        InvocationHandler handler = new DynamicProxy(subject);
        ISubject o = (ISubject) Proxy.newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(), handler);

        o.add();//调用动态代理的方法
        o.delete();

    }



    static class DynamicProxy implements InvocationHandler{

        private ISubject subject;
        public DynamicProxy(ISubject subject){
            this.subject = subject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            System.out.println("before");
            method.invoke(subject, args);
            System.out.println("after");

            System.out.println("-----------------");
            return  null;
        }
    }
    
    interface ISubject{
        void add();
        void delete();
    }
    
    static public class RealSubject implements ISubject{

        @Override
        public void add() {
            System.out.println("add");
        }

        @Override
        public void delete() {
            System.out.println("delete");
        }
    }
    
    class Test{

        public void query(){
            System.out.println(test);
        };
    }

}
