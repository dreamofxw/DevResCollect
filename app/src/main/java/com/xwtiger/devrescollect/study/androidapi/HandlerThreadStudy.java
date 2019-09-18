package com.xwtiger.devrescollect.study.androidapi;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

/**
 * author:xw
 * Date:2019-01-03 10:01
 * Description:
 * //<editor-fold desc="">
 * //</editor-fold>
 */
public class HandlerThreadStudy {

    public final static int code_1 = 1;
    public final static int code_2 = 2;
    public final static int code_3 = 3;

    public static String tag = "testhandlerthread";


    private Handler handler ;
    private HandlerThread handlerThread ;


    public HandlerThreadStudy(){
        handlerThread = new HandlerThread("test_handthread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case code_1:
                        Log.d(tag,"code1="+code_1);
                        break;
                    case code_2:
                        Log.d(tag,"code2="+code_2);
                        break;
                    case code_3:
                        Log.d(tag,"code3="+code_3);
                        break;
                }
            }
        };
    }

    public void destroy(){
        if(handler !=null){

        }
    }

}
