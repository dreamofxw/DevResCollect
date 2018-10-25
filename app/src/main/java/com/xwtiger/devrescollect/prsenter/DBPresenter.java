package com.xwtiger.devrescollect.prsenter;

import android.content.Context;
import android.util.Log;

import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;
import com.xwtiger.devrescollect.MyApplication;

import java.io.File;
import java.io.Serializable;

/**
 * author:xw
 * Date:2018-07-02 10:15
 * Description:
 */
public class DBPresenter {


    public static void testPut(TestBean bean){
//        DB snappydb =null;
//        try {
//            snappydb = DBFactory.open(MyApplication.getContext(),null); //create or open an existing database using the default name
//            snappydb.put("key111",bean);
//        } catch (SnappydbException e) {
//
//        }finally {
//            try {
//                snappydb.close();
//            } catch (SnappydbException e) {
//                e.printStackTrace();
//            }
//        }
    }


    public static void get(){
//        DB snappydb =null;
//        try{
//            snappydb = DBFactory.open(MyApplication.getContext(),null);
//            if(snappydb.exists("key111")){
//                TestBean bean = snappydb.getObject("key111", TestBean.class);
//                Log.d("DBPresenter", "get: key1 =");
//            }
//        }catch (SnappydbException e){
//            e.printStackTrace();
//        }finally {
//            try {
//                snappydb.close();
//            } catch (SnappydbException e) {
//                e.printStackTrace();
//            }
//        }

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
