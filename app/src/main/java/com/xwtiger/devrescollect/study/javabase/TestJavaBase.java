package com.xwtiger.devrescollect.study.javabase;

/**
 * Created by xwadmin on 2018/3/20.
 * 测试java 基础知识点
 */

import android.os.AsyncTask;
import android.os.HandlerThread;
import android.support.annotation.NonNull;

import com.xwtiger.devrescollect.net.OkHttpClientManager;
import com.xwtiger.devrescollect.net.ResultCallBack;
import com.xwtiger.devrescollect.study.javabase.TestSingle;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import okhttp3.Request;


public class TestJavaBase {
    private static final int CAPACITY   = (1 << (Integer.SIZE-3)) - 1;

    public int sex = 0;

    public static TestBean mTestBean = new TestBean();

    public static void main(String[] args) throws ClassNotFoundException {

        /*TestJavaBase t = new TestJavaBase();
        System.out.println("before t的地址 ="+t);
        t.testT(t);
        System.out.println("sex ="+t.sex);*/
       /* String[] arr ={"abc","dbd","cde"};
        System.out.println("before arr的地址 ="+arr);
        replacearr(arr);
        System.out.println("arr[0] ="+arr[0]);*/
       /* String a = "hello2";
        final String b = "hello";
        String d = "hello";
        String c = b + 2;
        String e = d + 2;
        System.out.println("a ="+a+",e ="+e+",c ="+c);
        System.out.println((a == c));
        System.out.println((a == e));
        System.out.println("--------");
        System.out.println(a.equals(e));*/
       
      /* final int a = 10;
       int b = 2;
       int c = 8;
       int d = b+c;
       System.out.println(a == d);*/

      /*String abc = "abc";
      String bcd  = "ab";
      String cde = "c";
      String result = bcd+cde;
      System.out.println(abc == result);
      System.out.println(abc == result.intern());*/

        //getData(addHashMap());
        printMemInfo();

        LinkedHashMap linkedHashMap = new LinkedHashMap();


    }

    public static void printMemInfo(){
        TestNode<String> testNodePre = new TestNode<>("abc",null,null);
        TestNode<String> testNode = new TestNode<>("abc",testNodePre,null);
        TestNode nodetest = testNode.pre;
        testNode.pre = null;
        testNode.item = null;
        //System.gc();

        System.out.println(testNodePre);
        System.out.println("testNode ="+testNode);
        System.out.println("testNode.pre ="+testNode.pre);
        System.out.println("nodetest ="+nodetest);


    }

    private static void getData(Map<String,String> map ){
        if(map != null){
            Set<Map.Entry<String, String>> entries = map.entrySet();
            Iterator<Map.Entry<String, String>> iterator = entries.iterator();
            while(iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                System.out.println("key = "+key+",value ="+value);
            }
        }
    }

    private static void forEach(Map<String,String> map){
        Set<String> strings = map.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            String value = map.get(key);
            System.out.println("key = "+key+",value ="+value);
        }


    }

    private static Map<String,String> addHashMap(){
        Map<String,String> map = new HashMap();
        for (int i=0;i<5;i++){
            map.put("key"+i,"value"+i);
        }
        return map;
    }

    public static void replacearr(String[] i){
        System.out.println("method arr的地址 ="+i);
        i[0] = "123";
    }
    
    public static void testBaseDataType(String t){
        System.out.println("method before t的地址 ="+t);
        t = new String("123");
        System.out.println("method after t的地址 ="+t);
    }
    

    public void testT(TestJavaBase t){
        System.out.println("method before t的地址 ="+t);
        t.sex = 5;
        TestJavaBase t1 = new TestJavaBase();
        t1.sex = 3;
        System.out.println("method after t的地址 ="+t);
        t = t1;

    }


    
    
    private static void test(Test a){
        a.change("haha",2);
    }

     class Test{
        private String name;
        private int age;

        public Test(String name,int age){
            this.name = name;
            this.age = age;
        }

        public void change(String name,int age){
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append("name =");
            sb.append(name);
            sb.append(",age =");
            sb.append(age);
            return sb.toString();
        }
    }

    private static void test3261(){
        for(int i=0;i<5;i++){
            new MyAsynTask().execute("");
        }
    }
    
    private static class MyAsynTask extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... objects) {
            printTime();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String o) {
            printTime();
            System.out.println("-----------");
        }
        
        
        public void printTime(){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("threadName ="+Thread.currentThread().getName()+","+sdf.format(new Date()));
        }
    }

   static class Test12{

       public Test12(String str){}

       public void show(){
           System.out.println("show");

       }

    }

    static class MyRunnable2 implements Runnable{

        Object lock = new Object();
        private int x = 0;
        private volatile  boolean isRunnable = true;

        public MyRunnable2(){};

        @Override
        public void run() {
            while(isRunnable){
//                synchronized (lock){

                    x++;
                    System.out.println("threadName ="+Thread.currentThread().getName()+",x ="+x);
                    if(Thread.currentThread().getName().equals("thread1")){
                        try {
//                            lock.wait();
//                            lock.notify();
                            System.out.println("threadName ="+Thread.currentThread().getName()+",1释放执行权");
                            Thread.sleep(5000);
//                            lock.wait();
                            //Thread.yield();
                            System.out.println("threadName ="+Thread.currentThread().getName()+",sleep1 finish");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else{
                        try {
                            //lock.notify();
                            System.out.println("threadName ="+Thread.currentThread().getName()+",2释放执行权");
                            Thread.sleep(5000);
                            //lock.wait();
                            //Thread.yield();
                            System.out.println("threadName ="+Thread.currentThread().getName()+",sleep2 finish");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    //
                   //lock
//                }



            }
        }

        public void cancel(){
            this.isRunnable = false;
        }
    }

    static class MyThread implements ThreadFactory{

        private String threadName;

        public MyThread(String threadname ){
            this.threadName = threadname;
        }

        @Override
        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r,this.threadName);
        }
    }


    public static void test7(){

        Test1 t2  = new Test1("angsan");
        String ne = t2.name;
        t2.name = "nihao";
        System.out.println(ne);

        //AsyncTask
    }


    static class Test1{
        public String name;
        public Test1(String name){
            this.name = name;
        }
    }

    public static void test6()  {
        HashMap hashMap = new HashMap<String,String>();
        hashMap.put("89","hahah");
        String put = (String) hashMap.put("89", "4444");

        Set<Map.Entry<String,String>> set = hashMap.entrySet();
        for(Map.Entry entry :set){
            int i = entry.hashCode();
            System.out.println("i ="+i);
        }

    }

    public static void test5() throws InterruptedException {
        TestRunnable testRunnable = new TestRunnable();

        Thread thread1 = new Thread(testRunnable);
        thread1.start();
        Thread thread2 = new Thread(testRunnable);
        thread2.start();
        TimeUnit.MILLISECONDS.sleep(5);
        testRunnable.cancle();
    }
    
    static class TestRunnable implements Runnable{

        private boolean iscancel = false;
        private  int i;
        @Override
        public void run() {
            while(!iscancel){
                i++;
                System.out.println("threadname ="+Thread.currentThread().getName()+" i="+i);
            }
            System.out.println("stop threadname"+Thread.currentThread().getName());
        }

        public void cancle(){
            this.iscancel = true;
        }
    }

    public static void test4(){
        MyRunnable1 myRunnable = new MyRunnable1();
        Thread thread = new Thread(myRunnable);
        thread.start();

        Thread thread2 = new Thread(myRunnable);
        thread2.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        thread2.interrupt();
    }

    private  static class  MyRunnable1 implements Runnable{

        private  int i;
        private Object lock = new Object();
        @Override
        public void run() {
                while(! Thread.currentThread().isInterrupted()){
                    synchronized (lock){
                        i++;
                        System.out.println("Thread Name ="+Thread.currentThread().getName()+" i="+i);
                        System.out.println("------------");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupted();
                        }
                    }
                }
            System.out.println("thread interrupt end");
        }
    }


    public static void test3(){

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        final Future<String> submit = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "hahahah";
            }
        });
        System.out.println("我来了");

        new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println(submit.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        System.out.println("执行完了。。。。。");

    }

    public static void test2(){
        Outer.method().show();
    }

    public static void test1(){
        TestInclass.InClass inClass = new TestInclass().new InClass();
        inClass.test();
    }

    class MyRunable implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
