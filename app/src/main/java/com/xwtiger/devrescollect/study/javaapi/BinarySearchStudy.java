package com.xwtiger.devrescollect.study.javaapi;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by xwadmin on 2018/1/6.
 */

public class BinarySearchStudy {
    
    public static void main(String[] args){
        
        testTreeSet();
        
    }
    
    public static void testTreeSet(){

        TreeSet<String> treeSet = new TreeSet<String>();
        treeSet.add("string123");
        treeSet.add("string234");
        treeSet.add("string345");
        treeSet.add("string456");
//        System.out.println(treeSet.first());
//        System.out.println(treeSet.floor("string345"));
        /*Iterator<String> iterator =
                treeSet.iterator();
        while(iterator.hasNext()){
            print(iterator.next());
        }*/
       /* for(String str:treeSet){
            print(str);
        }*/
       for(int i =0;i<treeSet.size();i++){
           print("start :"+i+"size :"+treeSet.size());
           String s = treeSet.pollLast();
           print(s);
       }
       print("end treeset size = "+treeSet.size());
    }
    
    public static void print(String s){
        //System.out.println("------print-----");
        System.out.println(s);
    }
    
    
}
