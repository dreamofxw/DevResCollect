package com.test.xuww.libjava;

import com.test.xuww.libjava.testannotation.HelloServiceCgLib;
import com.test.xuww.libjava.testannotation.IListener;
import com.test.xuww.libjava.testannotation.LogUtils;

import java.lang.reflect.Proxy;
import java.text.DecimalFormat;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyClass {

    public static void main(String[] args) {
//        try {
//            TestBean testBean = new TestBean();
//            testBean.setAge(12);
//            Field age = TestBean.class.getDeclaredField("age");
//            age.setAccessible(true);
//            int anInt = age.getInt(testBean);
//            System.out.println("anint ="+anInt);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
            //testYu();

//        Map<String,Object> map = new HashMap<>();
//        map.put("str1",true);
//        map.put("2",null);
//        System.out.println(map);
//        System.out.println("result="+map.get("str1"));
//        System.out.println("result="+map.get("str2"));

//        String src = "   ad     \r\n  \t  asdfasdf      123      0000      v ";
//        String s = removeBlanks(src);
//        System.out.println("s="+s);
//        System.out.println("src="+src);

//        List<String>  list = new ArrayList<>();
//        list.add("str1");
//        list.add("str2");
//        System.out.println(list);
//        list.set(list.size()-1,"strnew");
//        System.out.println(list);
//        test1(null);
        LogUtils logUtils = new LogUtils();
        HelloServiceCgLib  helloServiceCgLib = new HelloServiceCgLib(logUtils);

        IListener proxyHello = (IListener) Proxy.newProxyInstance(LogUtils.class.getClassLoader(), LogUtils.class.getInterfaces(), helloServiceCgLib);
        proxyHello.logd(test42());

    }


    public static void test1( String params){
        System.out.println("test1");
    }

    public static String test42(){
        System.out.println("test2");
        return "test2";
    }


    public static String removeBlanks(String content) {
        if (content == null) {
            return null;
        }
        StringBuilder buff = new StringBuilder();
        buff.append(content);
        for (int i = buff.length() - 1; i >= 0; i--) {
            if (' ' == buff.charAt(i) || ('\n' == buff.charAt(i)) || ('\t' == buff.charAt(i))
                    || ('\r' == buff.charAt(i))) {
                buff.deleteCharAt(i);
            }
        }
        return buff.toString();
    }


    public static String replaceBlank(String src) {
        String dest = "";
        if (src != null) {
            Pattern pattern = Pattern.compile("\t|\r|\n|\\s*");
            //Pattern pattern = Pattern.compile("\t|\r|\n\\\\s*");
            Matcher matcher = pattern.matcher(src);
            dest = matcher.replaceAll("");
        }
        return dest;
    }
    
    
    public static void testYu(){
        
        if(test1()&&test2()){
            System.out.println("&&");
        }
        System.out.println("-----------------------");
        if(test1()||test2()){
            System.out.println("||");
        }
        
    }
    
    public static boolean test1(){
        TestBean testBean =null;
        System.out.println("test1");
        return (testBean !=null&&testBean.age==10);
    }

    public static boolean test2(){
        System.out.println("test2");
        String str = "EA9891F9DD87CCA1363900BFF03E5B5467D7E8FCFE121E91639546C5C74E4C5B";

        System.out.println(str.length());

        System.out.print("hah");

        return true;
    }





    @Override
    public String toString() {
        return super.toString();
    }

    public static LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<String>();
    public static String intToStringForJoinNums(int join_nums){
        if(join_nums >=10000){
            float newvlaue = join_nums / 10000f;
            DecimalFormat df = new DecimalFormat("0.0");
            String format = df.format(newvlaue);
            if(format.endsWith(".0")){
                return ((int)(Float.parseFloat(format))+"w");
            }else{
                return format+"w";
            }
        }
        return String.valueOf(join_nums);
    }










}
