package com.test.xuww.libjava;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;

public class MyClass {

    public static void main(String[] args) {
        try {
            TestBean testBean = new TestBean();
            testBean.setAge(12);
            Field age = TestBean.class.getDeclaredField("age");
            age.setAccessible(true);
            int anInt = age.getInt(testBean);
            System.out.println("anint ="+anInt);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


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
