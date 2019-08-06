package com.xwtiger.devrescollect;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.ListView;

import com.xwtiger.devrescollect.statistics.MD5Util;
import com.xwtiger.devrescollect.study.javaapi.PatternStudy;
import com.xwtiger.devrescollect.utils.TimeUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.sql.SQLTransientException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
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
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import okhttp3.internal.Util;
import retrofit2.http.Url;

/**
 *
 *
 * Created by Busap-112 on 2017/11/1.
 *
 */
public class TestJava extends TestUapte{

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

    public static void main(String[] args) throws IOException {
//        String test ="\uD83C\uDF37\uD83C\uDF38\uD83C\uDF39\uD83D\uDC2D\uD83C\uDF43\uD83D\uDDFB\uD83D\uDCA8\uD83D\uDCA6\uD83C\uDF0A\uD83C\uDF08";
//        String source="\uD83C\uDF38\uD83C\uDF39\uD83D\uDC2D\uD83C\uDF43";//🌸🌹🐭🍃


       //data:image/png;base64

        /*java.net.URL  url = null;
        try {
            url = new  java.net.URL("https://i.cnblogs.com/EditPosts.aspx?postid=9007907");
        } catch (MalformedURLException e) {
            MyException.printStr(e);
        }
        List<String> list = addList();


        List<List<String>> result = spliteData(list);
        
        System.out.println(result);*/

        //testThread1212();
        //testqueue();
        //testSynqueue();
//        try {
//            synchronousQueue.put("abc");
//        } catch (InterruptedException e) {
//            MyException.printStr(e);
//        }

//        int a = 1<<29;
//        a--;
//        System.out.println("a ="+a);
        
        
//        int a = -1 <<29;
//        int b = 0 <<29;
//        int c = 1 <<29;
//        int d = 2 <<29;
//        int e = 3 <<29;
        
//        System.out.println("a ="+a);
//        System.out.println("b ="+b);
//        System.out.println("c ="+c);
//        System.out.println("d ="+d);
//        System.out.println("e ="+e);

        
        
        //
        // testValue();
        //testBean();

//        TestBean99 testBean99 = new TestBean99();
//        testBean99.name = "hah";
//        testBean99.address = "beijing";
//        testBean99.setAge(5);

//        TestBean99 testBean992 = testBean99;
//        testBean99 =null;
//
//        System.out.println(testBean99);
//        System.out.println(testBean992);


//        System.out.println("---first--");
//        System.out.println("name ="+testBean99.name);
//        System.out.println("address ="+testBean99.address);
//        System.out.println("age ="+TestBean99.age);
//        System.out.println("---second--");
//        System.out.println("name ="+testBean992.name);
//        System.out.println("address ="+testBean992.address);
//        System.out.println("age ="+TestBean99.age);
        
//        Map<String,String> map = new HashMap<>();
//        map.put("123","hah");
//        System.out.println(map.put("123","abc"));
//        System.out.println(map.containsValue("abc"));
        
//        if(Object.class.isAssignableFrom(TestJava.class)){
//            System.out.println("true");
//        }else{
//
//        }
        
//        String str = "smb://nas.youshu.cc/1.临时共享/ebook/ebook/origin/dl/book-cover_5a9911c89a08fzhunbeihaoliaoma_kaishigongzuoba_m.epub";
//
//        try {
//            SmbFile smbFile = new SmbFile(str);
//            System.out.println(smbFile.getPath());
//            System.out.println(smbFile.getName());
//            System.out.println("-------");
//
//            if(smbFile.isDirectory()){
//                SmbFile[] smbFiles = smbFile.listFiles();
//                System.out.println(smbFile.getPath());
//            }
//
//
//        } catch (MalformedURLException e) {
//            MyException.printStr(e);
//        } catch (SmbException e) {
//            MyException.printStr(e);
//        }


//        String result = "10.2;";
//
//
//        if(testReg11(result)){
//            float f_1 = Float.parseFloat(result);
//            int i_1 = (int) Float.parseFloat(result);
//
//
//            if((f_1-i_1)>0){
//                System.out.println("大于"+(f_1-i_1));
//            }
//            System.out.println("f_1="+f_1);
//            System.out.println("i_1="+i_1);
//        }else{
//            System.out.println("false result ="+result);
//        }

//        List list = new ArrayList();
//        list.add("str"+1);
//        list.add("str"+2);
//        list.add("str"+3);
//        list.add("str"+4);
//        System.out.println(list.toString());
//
//        Iterator<String> it = list.iterator();
//        while(it.hasNext()){
//            String x = it.next();
//            if(x.equals("str2")|x.equals("str3")){
//                it.remove();
//            }
//        }
//        System.out.println("after :"+list.toString());

//        String phone = "14567891345";
//
//        System.out.println(testReg22(phone));

//            File file = new File("./plan.txt");
//            byte[] b =new byte[1024];
//            int length =0;
//            BufferedInputStream  bis = new BufferedInputStream(new FileInputStream(file));
//
//            while((length =bis.read(b,0,b.length)) !=-1){
//                System.out.println(new String(b,0,length));
//            }

            

//            System.out.println(file.getPath());



        //testException();

//        String test ="123.0";
//        System.out.println("------------");
//        
//        System.out.println();

        //removeByIterator();



        //System.out.println(TestJava.class.getName());

//        long time = System.currentTimeMillis();
//        time = time -24*60*60*1000;
//        Date date = new Date();
//        date.setTime(time);
//
//        System.out.println(isToday(date));

//        List<String> list = new ArrayList<>();
//        list.add("str1");
//        list.add("str2");
//        list.add("str3");
//
//        List<String> list1 = new ArrayList<>();
//        list1.add("str3");
//        list1.add("str2");
//        list1.add("str4");
//
//
//        System.out.println(list.containsAll(list1));

        String url = "http://www.baidu.com";
        String url1 = "http://apidev.laidan.com:81/m/page/getIndex?page_id=10010013&&uid=123";
        replaceHostForOldNet(url1);
    }


    public static void replaceHostForOldNet(String urlpath){
        try {
            URL url = new URL(urlpath);
            System.out.println("host ="+url.getHost());
            System.out.println("path ="+url.getPath());
            System.out.println("参数 ="+url.getQuery());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public static void testDate12(){
        SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date old_date = new Date(0);

        // System.out.println(fmt.format(old_date).toString());

        Date customer = new Date(2019-1900,2,1);
        System.out.println("customer ="+fmt.format(customer));


        Date date = new Date();
        long time = date.getTime();
        System.out.println("time ="+time);
        System.out.println("currentTimeMillis="+System.currentTimeMillis());
        System.out.println(fmt.format(new Date(time)));
        System.out.println("---------------");
        time = customer.getTime();
        for(long i=1;i<30;i++){
            long result = time -24*60*60*1000*(i);
            Date date1 = new Date(result);
            System.out.println(fmt.format(date1)+"星期--"+getWeek(date1));
        }
    }


    public static String getWeek(Date date){
        String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(week_index<0){
            week_index = 0;
        }
        //System.out.println(weeks[week_index]);
        return weeks[week_index];
    }


    public static boolean isToday(Date date){
        SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("old ="+fmt.format(date));
        System.out.println("new ="+fmt.format(new Date()));
        if(fmt.format(date).toString().equals(fmt.format(new Date()).toString())){//格式化为相同格式
            return true;
        }else {
            return false;
        }
    }


        public static void removeByIterator(){//正确的删除方式
            HashMap<Integer, String> map = new HashMap<Integer, String>();
            map.put(1, "one");
            map.put(2, "two");
            map.put(3, "three");
            System.out.println(map);
            Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<Integer, String> entry = it.next();
                if(entry.getKey() != 2)
                    it.remove();//使用迭代器的remove()方法删除元素
            }
            System.out.println(map);
        }


//    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//
//    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//
//        System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//
//    }


    public static String readableFileSize(long size) {
        if (size <= 0) return "0B";
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + "" + units[digitGroups];
    }



    public static void testException(){

        System.out.println("test exception");
        
        String str =null;
        try{
            System.out.println(str.charAt(0));
        }catch (Exception e){
            MyException.printStr(e);
        }

    }






    public static boolean testReg22(String num){
        String telRegex = "^1[0-9]{10}+$";
        return num.matches(telRegex);
    }


    public static boolean testReg11(String num){
        String telRegex = "^[0-9]?+[0-9]*+\\.?+[0-9]*+$";
        return num.matches(telRegex);
    }


    public static String getHostFromUrl(String str_url){
        java.net.URL  url = null;
        try {
            url = new  java.net.URL(str_url);
            String host = url.getHost();// 获取主机名
            int i = host.indexOf(".");//获取第一个点的位置
            return host.substring(i+1,host.length());
        } catch (MalformedURLException e) {
            MyException.printStr(e);
        }
        return "";
    }

    public static class TestBean99{
        public static int age = 1;
        public String name;
        public String address;

        public TestBean99(){};

        public void setAge(int a)
        {
            age= a;
        }
    }


    public static void testValue(){
        int a = 1;
        int b = a;
        a++;
        System.out.println("a ="+a);
        System.out.println("b ="+b);

    }

//    public static BlockingQueue blockingQueue = new SynchronousQueue();



    public static List<String> addList(){
        List<String> list = new ArrayList<>();
        for(int i=0;i<1;i++){
            list.add("string"+i);
        }
        return list;
    }


    public static List<List<String>> spliteData(List<String> bean){
        int fact = 3;
        List<List<String>> list_result = null;
        if(bean != null){
            List<String> list = bean;
            if(list != null && list.size()>0){
                list_result = new ArrayList<>();
                List<String> temp = new ArrayList<>();
                for(int i =0;i<list.size();i++){
                    temp.add(list.get(i));
                    if(temp.size()==3||i==list.size()-1){
                        List<String> result = new ArrayList<>(temp);
                        temp.clear();
                        list_result.add(result);
                    }
                }
            }
        }
        return list_result;

    }



    public static void tesSwitch(){
        int a = 0;
        switch (a){
            case 0:
                System.out.println("0");
            case 1:
                System.out.println("1");
            case 2:
                System.out.println("2");
            case 3:
                System.out.println("3");
            case 4:
                System.out.println("4");
            case 5:
                System.out.println("5");

        }
    }



    public static void testThreads(){
        //SynchronousQueue<Runnable>()
        //Integer.MAX_VALUE
        ThreadPoolExecutor tpe =  new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(1), new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable r) {
                Thread t = new Thread(r);
                return t;
            }
        });
        for(int i =0;i<100;i++){
            tpe.execute(new TestThreads());
        }
    }


    static class TestThreads implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                System.out.println("threadName="+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                MyException.printStr(e);
            }

        }
    }




    public static String testchain(){

        List<TestInteceptor> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(new TestInteceptor(i));
        }

        TestChain testChain = new TestChain(list,0);
        return testChain.process();

    }




    
    
    public static void testQueue(){
        int size = 30;
        ArrayDeque arrayDeque = new ArrayDeque();
        for(int i =0;i<size;i++){
            arrayDeque.add("string"+i);
        }
        System.out.println(arrayDeque);
    }

    public class hh{

    }

    public class gg{

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
            MyException.printStr(e);
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
            MyException.printStr(e);
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
                    MyException.printStr(e);
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
//                        MyException.printStr(e);
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
            MyException.printStr(e);
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
            MyException.printStr(e);
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
                MyException.printStr(e);
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


    public static void testBean(){
        Test test1 = new Test();
        test1.age = 3;
        test1.addList("test1");
        Test test2 = new Test();
        test2.age = 29;
        test2.addList("test2");

        System.out.println("test1 :age ="+test1.age+",name ="+test1.getName()+",list.size ="+test1.list);
        System.out.println("test2 :age ="+test2.age+",name ="+test2.getName()+",list.size ="+test2.list);

    }

    public static class Test{
        public static String name ="dadafd";
        public int age;

        public List<String> list = new ArrayList();


        public String getName(){
            return name;
        }

        public void addList(String str){
            list.add(str);
        }
    }



}
