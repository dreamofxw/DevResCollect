package com.example.libjava2;

import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Pattern;


public class MainClass {


    public static void main(String[] args) {




        //test1();

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String next = scanner.next();
            System.out.println("next ="+next);
            if("eof".equals(next)){
                break;
            }
        }

    }


    public static void test1(){
//        for (int binCount = 0; testboolean(binCount); ++binCount) {
//            System.out.println("bitcount="+binCount);
//            if(binCount >10){
//                break;
//            }
//        }
//        int temp =10;
//        String[] str = new String[temp];
//        for(int i =0;i<temp;i++){
//            str[i] = "str"+i;
//        }
//
//        int a = 1;
//        System.out.printlnrintln(str[(a=a+2)]);

//        TestBean testBean1 =null;TestBean testBean2 =null;
//        if((testBean1 =testBean2) ==null){
//            System.out.println("==null 111");
//            testBean2 = new TestBean("name2","desc2");
//        }
//        System.out.println("result ="+(testBean1 ==null));


    }

    public static boolean testboolean(int binCount){
        System.out.println("判断条件binCount ="+binCount);
        return true;
    }

    public static void testContentProvider(){
        Pattern PATH_SPLIT_PATTERN = Pattern.compile("/");
        String regex = "user";
        String[] split = PATH_SPLIT_PATTERN.split(regex);
        System.out.println(split.length);
        for(String str:split){
            System.out.println("str="+str);
        }
    }

    public static void testString(){
        int str = 0x4e2d;
        int str2 = 20013;

        double temp = 4*Math.pow(16,3)+14*Math.pow(16,2)+2*Math.pow(16,1)+13*Math.pow(16,0);
        System.out.println("temp ="+temp);
        System.out.println(str);
        System.out.println(Integer.toHexString(str2));


    }


    public static void testHashMap(){
        HashMap<String,String> map = new HashMap<>();
        map.put("abc","123");
        map.put(null,null);
        map.put(null,null);

        System.out.println(map);

        System.out.println("------------------");
        HashSet hashSet = new HashSet();
        hashSet.add("abc");
        hashSet.add(null);
        hashSet.add(null);
        try {
            Field map1 = hashSet.getClass().getDeclaredField("map");
            map1.setAccessible(true);
            HashMap temp = (HashMap) map1.get(hashSet);
            System.out.println("temp ="+temp);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        System.out.println(hashSet);

        System.out.println("hashtable -------------");
        Hashtable hashtable =new Hashtable();
        hashtable.put("hhh1","111");
        hashtable.put(null,null);
        System.out.println(hashtable);
    }


}
