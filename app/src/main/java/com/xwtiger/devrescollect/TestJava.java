package com.xwtiger.devrescollect;

import android.text.TextUtils;

import com.xwtiger.devrescollect.statistics.MD5Util;
import com.xwtiger.devrescollect.study.javaapi.PatternStudy;
import com.xwtiger.devrescollect.utils.TimeUtils;

import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 *
 * Created by Busap-112 on 2017/11/1.
 *
 */
public class TestJava {

    public static boolean isfinished = true;

    public static String REG_POS = ".*+f00";
    public static String REG_REL = ".*?f00";
    public static String REG_GRE = ".*f00";

//    public static String Reg_gretest=".*[\\u4e00-\\u9fa5]+.*";
//    public static String Reg_reltest=".*?[\\u4e00-\\u9fa5]+?.*?";
//    public static String Reg_postest=".*+[\\u4e00-\\u9fa5]++.*+";

//    public static String Reg_gretest=".*[\\[\\([a-z]\\)\\]]+.*";
//    public static String Reg_reltest=".*?[\\[\\([a-z]\\)\\]]+?.*?";
//    public static String Reg_postest=".*+[\\[\\([a-z]\\)\\]]++.*+";

    public static String Reg_gretest=".*[a-z]+[a-z]+.*";
    public static String Reg_reltest=".*?[a-z]+?[a-z]+?.*?";
    public static String Reg_postest=".*+[a-z]++[a-z]++.*+";


//    public static String test = "[\\u[a-fA-F0-9]{4}]";//
//    public static String test1 = "[\\\\u[a-fA-F0-9]{4}]+";//
    public static String test3 = "[\\uD83C\\uDF00-\\uD83D\\uDDFF]+";//
    public static String test2 = "[\uD83C\uDF00-\uD83D\uDDFF]+|[\uD83E\uDD00-\uD83E\uDDFF]+|[\uD83D\uDE00-\uD83D\uDE4F]+|[\uD83D\uDE80-\uD83D\uDEFF]+";




    public static final String ee_1 = "[(a)]";

    public static void main(String[] args){
//        String test ="\uD83C\uDF37\uD83C\uDF38\uD83C\uDF39\uD83D\uDC2D\uD83C\uDF43\uD83D\uDDFB\uD83D\uDCA8\uD83D\uDCA6\uD83C\uDF0A\uD83C\uDF08";
//        String source="\uD83C\uDF38\uD83C\uDF39\uD83D\uDC2D\uD83C\uDF43";//🌸🌹🐭🍃

        String fact = "fact";
        String s = test32(test31(fact), fact);
        System.out.println("result ="+s);
    }


    public static String test31(String str){

        return "test31"+str;
    }

    public static String test32(String str,String fact){
        System.out.println("test32");
        fact = "123";
        return str;
    }



    static class Parent{

        public void test(){
            System.out.println("parent.test");
        }
    }

    static class Child extends Parent{
        @Override
        public void test() {
            //super.test();
            System.out.println("child test");
        }
    }


    
    public static void testWeak(){
        WeakReference<TestBean> weakReference = new WeakReference<>(new TestBean("zhangsan"));
        weakReference.clear();
        System.out.println(weakReference.get());
        System.gc();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(weakReference);
        weakReference = new WeakReference<>(new TestBean("lisi"));
        System.out.println(weakReference);
        

    }
    static class TestBean{
        public TestBean(String name){
            this.name = name;
        }
        public String name;
    }

    public static boolean isContainerChinese(String source,String reg){
        //String reg =".*[\\u4e00-\\u9fa5]+.*";//Greedy


        return source.matches(reg);
    }


    public static boolean isContainerEnglish(String source){
        String reg= ".*[a-zA-Z]+.*";
        if(TextUtils.isEmpty(source)){
            return false;
        }
        return source.matches(reg);
    }

    /**
     * 是否包含数字
     * @param source
     * @return
     */
    public static boolean isContainerNum(String source){
        String reg= ".*[0-9]+.*";
        if(TextUtils.isEmpty(source)){
            return false;
        }
        return source.matches(reg);
    }

    public static boolean regex3(String reg,String input){
        Pattern p = Pattern.compile(reg);
        Matcher matcher = p.matcher(input);
        while (matcher.find()) {
            System.out.println("matched form " + matcher.start() + " to " + matcher.end());
            return true;
        }
        return false;
    }



    public static void testReg(String source,String reg){
        System.out.println(source.matches(reg));
    }
    


    public static void testSign(){

        Map<String,String> map = new HashMap<>();
        map.put("c","e");
        map.put("a","abc");
        map.put("b","aa");

        Set<String> strings = map.keySet();
        ArrayList<String> list = new ArrayList<>(strings);
        System.out.println("keyset ="+list.toString());
        Collections.sort(list);
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<list.size();i++){

            sb.append("|");
            sb.append(map.get(list.get(i)));

        }
        System.out.println("stringbuffer ="+sb.toString());
        String secret ="76440b15b8d6907fd3df95933945be47a7e98c42";
        String result = secret +sb.toString();
        System.out.println("result = "+result);
        String sign = testMD5(result);
        String sign2 = MD5Util.encodeBy32BitMD5(result);
        System.out.println("sign ="+sign);
        System.out.println("sign2 ="+sign2);
        System.out.println(sign.equals(sign2));

    }

    public static String testMD5(String source) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] b = digest.digest(source.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte bt : b) {
                int ibt = bt & 0xff;//抹掉高位
                if (ibt < 16) {
                    sb.append(0);
                }
                sb.append(Integer.toHexString(ibt));
            }
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public static void testMap(){

        Map<String,String> map = new HashMap<String,String>();

        map.put("id",UUID.randomUUID().toString());
        map.put("et","et");
        map.put("ed","ed");
        map.put("uid","12345");
        map.put("v","Android/7.0/FRD-AL10/4.2.0");
        map.put("ts",String.valueOf(System.currentTimeMillis()));

        Set<String> strings = map.keySet();
        List<String> list = new ArrayList(strings);
        Collections.sort(list);
        System.out.println(list.toString());

        StringBuffer sb = new StringBuffer();
        for(String s:list){
            sb.append(map.get(s));
        }
        String sign = MD5Util.encodeBy32BitMD5(sb.toString());
        map.put("sign",sign);

        System.out.println(map.toString());
    }


    public static boolean testReg(String num){
        String telRegex = "^[0-9][0-9]*+\\.?+[0-9]*+[kw]?+$";
        return num.matches(telRegex);
    }


    public static void test121(){
        System.out.println("befor thread count ="+Thread.activeCount());
        Thread.currentThread().getThreadGroup().list();

        Runnable r =  new Task();

        for(int i =0;i<5;i++){
            switch (i){
                case 0:
                    new Thread(r).start();
                    break;
                case 1:
                    new Thread(r).start();
                    break;
                case 2:
                    new Thread(r).start();
                    break;
                case 3:
                    new Thread(r).start();
                    break;
                case 4:
                    new Thread(r).start();
                    break;

            }
        }
        System.out.println("after thread count ="+Thread.activeCount());
        while(isfinished){
            if(Thread.activeCount()<=2){
                isfinished = false;
                for(String str:listtest){
                    System.out.println("str="+str);
                }
                System.out.println("size :"+listtest.size());
            }else{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        listtest.clear();
    }


    public  static List<String> listtest = Collections.synchronizedList(new ArrayList<String>());
    //public  static List<String> listtest = new ArrayList<String>();

    static class Task implements Runnable{

        public String prifix ;
        public Task(){

        }

        @Override
        public void run() {
            for(int i =0;i<125;i++){
//                if(i ==3){
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
                listtest.add(Thread.currentThread().getName()+"-"+i);
            }
        }
    }


    static class DeleteTask implements Runnable{

        @Override
        public void run() {
            if(listtest != null&& listtest.size()>0){
                Iterator iterator = listtest.iterator();
                while(iterator.hasNext()){
                    String next = (String) iterator.next();
                    if(next.contains("0-0")){
                        iterator.remove();
                    }
                }
            }
        }
    }


    public static void testDate(){

        String time = "2018-05-17 12:09:02";
        String time6 = "2018-05-17";
        String s = TimeUtils.formatDate(time);
        System.out.println("s = "+s);

        Date date = new Date();
        long time1 = date.getTime();
        System.out.println("time1 = "+time1);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String format = sd.format(date);
        try {
            Date parse = sd.parse(time);
            System.out.println("parse "+parse.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("format = "+format);

    }


    public static String formatDate(String long_time) {

        // "2016-08-01 16:23"
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat time_year = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time2 = new SimpleDateFormat("HH:mm");
        SimpleDateFormat time3 = new SimpleDateFormat("MM-dd HH:mm");

        String result = "";
        Date date = null;
        try {
            date = time.parse(long_time);
        } catch (ParseException e) {
            e.printStackTrace();
            return long_time;
        }
        Date currentDate = new Date();

        Calendar instance = Calendar.getInstance();
        Calendar instance_current = Calendar.getInstance();
        instance.setTime(date);
        instance_current.setTime(currentDate);

        if (instance.get(Calendar.YEAR) < instance_current.get(Calendar.YEAR)) {
            // 去年
            return result = time_year.format(date);
        }
        if (instance.get(Calendar.MONTH) < instance_current.get(Calendar.MONTH)) {
            // 今年
            return result = time3.format(date);

        } else if (instance.get(Calendar.MONTH) == instance_current.get(Calendar.MONTH)) {

            if (instance.get(Calendar.DAY_OF_MONTH) == instance_current.get(Calendar.DAY_OF_MONTH)) {
                // 今天
                long diff = currentDate.getTime() - date.getTime();// 这样得到的差值是微秒级别
                long days = diff / (1000 * 60 * 60 * 24);
                long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
                if (hours < 6) {
                    if (hours < 1 && minutes < 60) {
                        return result = minutes + "分钟前";
                    } else {
                        return result = hours + "小时前";
                    }
                } else {
                    return result = "今天 " + time2.format(date);
                }
            } else if (instance.get(Calendar.DAY_OF_MONTH) == instance_current.get(Calendar.DAY_OF_MONTH) - 1) {
                // 昨天
                return result = "昨天 " + time2.format(date);
            }
            return result = time3.format(date);
        }
        return result;
    }
    
    
    public static void changeTest(Test test){
        test.age = 2;
        test.name = "name2";
    }

    /**
     * Ascaii 和char 互转
     */
    public void asiicandchar(){
        String str = "还123";
        Integer integer = Integer.valueOf(str.charAt(0));
        System.out.println("result ="+integer);

        System.out.println("-----------");

        String str1="36824";
        char c = (char) Integer.parseInt(str1);
        System.out.println(c);
    }

    public static void testDoWhile(){
        int x =1;
        do{
            System.out.println("x ="+x);
            x++;
        }while (x <1);

    }

    public static boolean isPowerOfThree(int n) {
        if(n<=0){
            return false;
        }
        // double num=Math.log(n)/Math.log(3); log()与log10()与log1p()的区别  
        System.out.println("math.log10(n) ="+n+" ,"+Math.log10(n));
        double num=Math.log10(n)/Math.log10(3);
        if(num%1==0){
            return true;
        }else{
            return false;
        }
    }

    public static void changArray(){
        int[] arr = new int[]{1,2,3,4,5};
        printArray(arr);
        int temp = arr[1];
        arr[1] = arr[3];
        arr[3] = temp;
        System.out.println("---------");
        printArray(arr);
    }
    
    public static void printArray(int[] arr){
        for(int i:arr){
            System.out.println("i ="+i);
        }
    }

    public static boolean isPowerByThree(){
        
        for(int i =0;i<20;i++){
            
            System.out.println("i ="+i+",result ="+i/3);
        }
        
        
        return false;
    }
    

    public static void testThread(){

        int a =0;
        while(a <100){
            try {
                a++;
                TimeUnit.MILLISECONDS.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ad---------");
        }


    }

    public static void testFinally(){
        try{
            System.out.println("try");
        }catch (Exception e){
            System.out.println("catch");
        }finally {
            System.out.println("finally");
        }

    }






    public static void testAnd(){
        int a = 1;//0001
        int b = 3;//0011
                   //0001
        System.out.println("& ="+(a&b));//1
        System.out.println("| ="+(a|b));//3
        System.out.println(Math.pow(2,10)-1);
    }


    public static void testCurrent3(){

        Stack<String> stack = new Stack<String>();
        for(int i =0;i<10;i++){
            stack.add("str"+i);
        }
        /*for(String str:stack){
            if(str.equals("str3")){
                stack.remove(str);
            }
            System.out.println("peek ="+str);
        }*/

        for(int i =0;i<stack.size();i++){
            String peek = stack.get(i);
            if(peek.equals("str3")){
                stack.remove(i);
                break;
            }
            System.out.println("peek ="+peek+"--i ="+i);
        }

        System.out.println("stack ="+stack.toString());
    }




    public static void testCurrent2(){

        ArrayList<String> array = new ArrayList<String>();

        // 创建并添加元素
        array.add("hello");
        array.add("world");
        array.add("java");
        array.add("java2");
        array.add("java3");
        array.add("java4");
        array.add("java5");
        System.out.println("array ="+array.toString());


//        for(String str:array){
//            if("java".equals(str)){
//                array.add(str);
//            }
//        }

        for(int i =0;i<array.size();i++){
            String str = array.get(i);
            if("java".equals(str)){
                array.remove(str);
                array.add("haha");
            }
        }
        System.out.println("after array ="+array.toString());
    }

    public static void testCurrent1(){

        ArrayList<String> array = new ArrayList<String>();

        // 创建并添加元素
        array.add("hello");
        array.add("world");
        array.add("java");
        System.out.println("array ="+array.toString());
        ListIterator it = array.listIterator();
        while (it.hasNext()) {
            String s = (String) it.next();
            if ("world".equals(s)) {
                it.add("javaee");
                //array.add("javaee");
            }
        }
        System.out.println("after array ="+array.toString());
    }



    public static List<String> list = new ArrayList<String>();

    public  synchronized static void addlist(){
        for(int i =0;i<20;i++){
            list.add("string"+i);
            System.out.println("addlist "+list.toString());
        }
    }

    public static void removeList(){
        for(int i =0;i<20;i++){
            if(i<list.size()){
                list.remove(i);
            }
            System.out.println("removeList "+list.toString());
        }
    }



    public static void test(){

        double tp = 510000;
        double mr = 0.0465/12;
        double x = 3267.95;
        double result = tp*(1+mr)-x;
        System.out.println(String.valueOf(result));

    }


    public static  void testRateForHouse(){
        double x = 3267.95;
        double value = computeA() -x*computeB();
        System.out.println("value "+value);
    }



    public static double computeA(){
        double mr = 0.0465/12;
        double result =1;
        double tp = 510000;
        for(double i = 1;i<241;i++){
            result +=Math.pow(mr,i);
            System.out.println("result i="+result);
        }
        System.out.println("computeA ="+result);
        return tp*result;
    }

    public static double computeB(){
        double mr = 0.0465/12;
        double result = 1;
        for(double i = 1;i<240;i++){
            result += Math.pow(mr,i);
        }
        System.out.println("computeB ="+result);
        return result;
    }


    public static void testReg1(String reg){
        long startTime = System.currentTimeMillis();
        PatternStudy.regex3(reg,"123456f005f00");
        long enttime = System.currentTimeMillis() -startTime;
        System.out.println("reg = "+reg);
        System.out.println("enttime = "+enttime);
        System.out.println("------分割线--------");
    }


    public static class Test{
        public String name;
        public int age;

    }



}
