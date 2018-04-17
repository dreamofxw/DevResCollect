package com.xwtiger.devrescollect.study.javabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xwadmin on 2018/4/8.
 */

public class TestGenericParadigm<T> {

    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public void show(){
        if(t instanceof String){
            System.out.println("我是字符串");
        }else{
            System.out.println("我是其他类型");
        }
        System.out.println("T 的类型 是 "+t.getClass().getName());
    }

    public static  void test(List<?> k){
        System.out.println("k的类型"+k.getClass().getName()+","+k.toString());
        for(int i=0;i<k.size();i++){
            //k.get(i);
            System.out.println(k.get(i).getClass().getName());
        }
       /* for(U u:k){
            System.out.println("for循环"+u.getClass().getName());
        }*/
        /*if(k instanceof String){
            System.out.println("k是 字符串");
        }else{
            System.out.println("k是其他类型");
        }*/

    }

    
    public static void main(String[] args){
        TestGenericParadigm testGenericParadigm = new TestGenericParadigm();
        testGenericParadigm.setT(20);
        testGenericParadigm.show();
        Integer t = (Integer) testGenericParadigm.getT();
        System.out.println("t = "+t);

        List list = new ArrayList();
        list.add(253);
        TestGenericParadigm.test(list);

        System.out.println("--------");
        TestGenericParadigm<String> testGenericParadigm1 = new TestGenericParadigm<String>();
        testGenericParadigm1.setT("haha");
        testGenericParadigm1.show();
        String t1 = testGenericParadigm1.getT();
        System.out.println("t = "+t1);
        List list1 = new ArrayList();
        list1.add("abc");
        TestGenericParadigm.test(list1);
    }

}
