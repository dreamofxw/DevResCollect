package com.xwtiger.devrescollect.study.exception;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * author:xw
 * Date:2018-10-26 9:58
 * Description:
 * //<editor-fold desc="">
 * //</editor-fold>
 */
public class TestConcurrentModificaiton {


    public static void main(String[] args){

        TestBean ta= new TestBean("a123","b234");
        TestBean tb = ta;
        System.out.println("a ="+ta);
        System.out.println("b ="+tb);
        System.out.println("=="+(ta==tb));
        System.out.println("equals"+ta.equals(tb));
        System.out.println("------change------");
        ta = new TestBean("abc","bdc");
        System.out.println("a ="+ta);
        System.out.println("b ="+tb);


        List<String> strings = addList();
        Iterator<String> iterator = strings.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            if(next.equals("string3")){
                iterator.remove();
            }
        }
       /* for(int i=0;i<strings.size();i++){
            if(i ==2){
                strings.remove(i);
            }
        }*/
        System.out.println(strings);
    }

    public static List<String> addList(){
        List<String> list = new ArrayList<>();
        for(int i=0;i<20;i++){
            list.add("string"+i);
        }
        return list;
    }


    static class TestBean{
        public String a;
        public String b;

        public TestBean(String a,String b){
            this.a = a;
            this.b = b;
        }

    }

}
