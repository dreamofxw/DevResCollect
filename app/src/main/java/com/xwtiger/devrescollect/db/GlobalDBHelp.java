package com.xwtiger.devrescollect.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class GlobalDBHelp extends SQLiteOpenHelper {


    public static String dbname = "testdata";

    public static String tab_tips="tips";

    public static int dbversion =7;

    public GlobalDBHelp(@Nullable Context context) {

        super(context, dbname, null, dbversion);
        Log.d("testGlobalDB", "构造方法");

    }

    //id INTEGER PRIMARY KEY AUTOINCREMENT
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("testGlobalDB", "onCreate: 创建数据库");

        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + tab_tips
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT,uid TEXT,group_id TEXT,isPop TEXT,"
                + "timestamp TEXT)");

//        db.execSQL("CREATE TABLE IF NOT EXISTS "
//                + tab_tips
//                + "(uid TEXT,group_id TEXT,isPop TEXT,"
//                + "timestamp TEXT)");


//        db.execSQL("CREATE TABLE IF NOT EXISTS "
//                + tab_tips
//                + "(uid TEXT primary key,Time BIGINT,MsgContent TEXT,"
//                + "MsgType TEXT, ReadFlag INTEGER,SendFlag INTEGER,"
//                + "PlayedFlag INTEGER,Direction INTEGER,LocalPath Text,"
//                + "Duration INTEGER,Receive_AgentJid TEXT,created_at TEXT,"
//                + "updated_at TEXT,reply_user TEXT,reply_userurl TEXT,"
//                + "subsessionid TEXT,seqNum INTEGER,fileName TEXT,fileSize TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.d("testGlobalDB", "onUpgrade: oldVersion="+oldVersion+" ,newVersion="+newVersion);

        if(oldVersion < newVersion){

            String tempMessageInfo = "TempMessageInfo";
            db.execSQL("CREATE TABLE IF NOT EXISTS "
                    + tempMessageInfo
                    + "(uid TEXT,group_id TEXT primary key,isPop TEXT,"
                    + "timestamp TEXT)");

            db.execSQL(" INSERT INTO  "+tempMessageInfo
                    + "(uid,group_id,isPop,timestamp) "
                    + "SELECT uid,group_id,isPop,timestamp "
                    + " FROM "+tab_tips);

            db.execSQL("DROP TABLE IF EXISTS " + tab_tips);

            db.execSQL("ALTER TABLE TempMessageInfo RENAME TO " +tab_tips+"2");
        }

    }

    public static int getDbversion() {
        return dbversion;
    }


    //    public interface ColumnOfTips{
//        public String uid = "uid";
//    }
}
