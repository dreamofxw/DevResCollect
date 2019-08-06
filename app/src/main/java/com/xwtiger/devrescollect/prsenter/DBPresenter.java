package com.xwtiger.devrescollect.prsenter;

import android.content.Context;
import android.util.Log;

import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;
import com.xwtiger.devrescollect.MyApplication;
import com.xwtiger.devrescollect.MyException;

import java.io.File;
import java.io.Serializable;

/**
 * author:xw
 * Date:2018-07-02 10:15
 * Description:
 */
public class DBPresenter {

    public static String key = "key111";
    public static String tag = "DBPresenter";

    public static void testPut(TestBean bean){
        DB snappydb =null;
        try {
            snappydb = DBFactory.open(MyApplication.getContext()); //create or open an existing database using the default name
            snappydb.put(key,bean);
            Log.d(tag,"put success");
        } catch (SnappydbException e) {

        }finally {
            try {
                snappydb.close();
            } catch (SnappydbException e) {
                MyException.printStr(e);
            }
        }
    }


    public static TestBean get(){
        DB snappydb =null;
        try{
            snappydb = DBFactory.open(MyApplication.getContext());
            if(snappydb.exists(key)){
                TestBean bean = snappydb.getObject(key, TestBean.class);
                Log.d(tag, "get: key1 =");
                return bean;
            }
            Log.d(tag, "get: null ");
        }catch (SnappydbException e){
            MyException.printStr(e);
        }finally {
            try {
                snappydb.close();
            } catch (SnappydbException e) {
                MyException.printStr(e);
            }
        }
        return null;
    }


    public static void deleteObj(){
        DB snappydb =null;
        try{
           snappydb = DBFactory.open(MyApplication.getContext());
            if(snappydb.exists(key)){
                snappydb.del(key);
                Log.d(tag,"deleteobj success");
            }

        }catch(Exception e){

        }finally {
            try {
                snappydb.close();
            } catch (SnappydbException e) {
                MyException.printStr(e);
            }
        }

    }


    public static class TestBean implements Serializable{

        public String key1;
        public String key2;

        public TestBean(){};

        public TestBean(String key1,String key2){
            this.key1 = key1;
            this.key2 = key2;
        }
    }



}
