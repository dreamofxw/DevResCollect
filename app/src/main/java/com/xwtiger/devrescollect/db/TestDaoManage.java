package com.xwtiger.devrescollect.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

public class TestDaoManage {


    private GlobalDBHelp globalDBHelp;

    private Context mContext;

    private TestDaoManage(){

    };

    private static TestDaoManage mIntance;

    public static TestDaoManage getInstance(){
        if(mIntance ==null){
            synchronized (TestDaoManage.class){
                if(mIntance ==null){
                    mIntance = new TestDaoManage();
                }
            }
        }
        return mIntance;
    }

    SQLiteDatabase writableDatabase ;
    public void init(Context context){
        if(globalDBHelp ==null){
            globalDBHelp = new GlobalDBHelp(context);
            writableDatabase = globalDBHelp.getWritableDatabase();
            Log.d("testGlobalDB", "init: version"+writableDatabase.getVersion());

        }
    }


    public void addDataForList(List<TestModel> list){
        Log.d("testGlobalDB", "addDataForList enter");

        Log.d("testGlobalDB", "addDataForList enter 111");

        writableDatabase.beginTransaction();
        for(TestModel data:list){
            addData(data);
        }
        writableDatabase.setTransactionSuccessful();

        writableDatabase.endTransaction();
        Log.d("testGlobalDB", "addDataForList ender");

    }


    //uid,group_id,isPop,timestamp,
    public void addData(TestModel data){
        /**
         * INSERT INTO TABLE_NAME [(column1, column2, column3,...columnN)]
         * VALUES (value1, value2, value3,...valueN);
         */
        if(getData(data) !=null){
            updataData(data);
        }else{
            String sql ="INSERT INTO "+GlobalDBHelp.tab_tips+" (uid, group_id, isPop,timestamp) VALUES (?, ?, ?,?)";
            writableDatabase.execSQL(sql,new Object[]{data.uid,data.group_id,data.isPop,data.timestamp});
        }

    }

    public void delData(TestModel data){

        //DELETE FROM table_name
        //WHERE [condition];
        String sql ="DELETE FROM "+GlobalDBHelp.tab_tips+" where group_id = ?";
        writableDatabase.execSQL(sql,new String[]{data.group_id});

    }

    public void updataData(TestModel data){
        //UPDATE table_name
        //SET column1 = value1, column2 = value2...., columnN = valueN
        //WHERE [condition];
        String sql = "update "+GlobalDBHelp.tab_tips+" set isPop = "+data.isPop+" where uid = ? and group_id = ?";
        writableDatabase.execSQL(sql,new String[]{data.uid,data.group_id});
    }

    public TestModel getData(TestModel data){
        String sql1 = "select * from "+GlobalDBHelp.tab_tips+" where uid =? and group_id=?";
        Cursor cursor = writableDatabase.rawQuery(sql1, new String[]{data.uid, data.group_id});
        TestModel testModel = null;
        if (cursor.moveToFirst()){
            String uid = cursor.getString(cursor.getColumnIndex("uid"));
            String group_id = cursor.getString(cursor.getColumnIndex("group_id"));
            String isPop = cursor.getString(cursor.getColumnIndex("isPop"));
            String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
            if(!TextUtils.isEmpty(uid)&&!TextUtils.isEmpty(group_id)){
                testModel = new TestModel();
                testModel.uid = uid;
                testModel.group_id = group_id;
                testModel.isPop = isPop;
                testModel.timestamp = timestamp;
            }
        }
        cursor.close();
        return testModel;
    }



}
