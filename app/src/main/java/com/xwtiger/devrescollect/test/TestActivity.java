package com.xwtiger.devrescollect.test;

import android.app.Activity;
import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xwtiger.devrescollect.MainActivity;
import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.study.androidapi.AnmitorStudy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Busap-112 on 2017/11/10.
 *
 */

public class TestActivity extends BaseActivity {

    private LinearLayout container;
    private Button btn_start;
    private TextView tv;
    private Button btnJump;
    private TestHandler mTestHandler;
    private TestHandler mTestHandler2;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("testhandler","main thread name = "+Thread.currentThread().getName()+"handler1 msg =");
        }
    };
    private Handler handler2 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("testhandler","main thread name = "+Thread.currentThread().getName()+"handler2 msg =");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.testlayout);
        container = (LinearLayout) findViewById(R.id.container);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);

        btnJump = (Button) findViewById(R.id.btn_jumpmainact);
        btnJump.setOnClickListener(this);

        tv = new TextView(this);
        //TestView textView = new TestView(this);
        tv.setText("我会飘");
        tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        container.addView(tv);

       /* Observable.just("1","2","3").flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return Observable.just("abc"+s);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        });*/
        mTestHandler = new TestHandler();
        mTestHandler.createHandler();
        mTestHandler2 = new TestHandler();
        mTestHandler2.createHandler();

        /*Observable.just("1").flatMap(new Func1<String, Observable<?>>() {
            @Override
            public Observable<?> call(String s) {
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe()*/
        //IntentService
    }





    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
               /* try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                Log.d("testValueAnmitor","btn_start height ="+(btn_start.getHeight()));
                //AnmitorStudy.testValueAnmitor(tv);
                //addTestThread();
//                mTestHandler.sendMessage(mTestHandler.mHandler,"default");
//                mTestHandler.sendMessage(mTestHandler2.mHandler,"gei two");
//                handler.sendEmptyMessage(0);
//                handler2.sendEmptyMessage(0);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("handler.post","thread name ="+Thread.currentThread().getName());
                    }
                });
                mTestHandler.mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("handler.post","mTestHandler thread name ="+Thread.currentThread().getName());
                    }
                });

                break;
            case R.id.btn_jumpmainact:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }


    public class TestView extends View{


        public TestView(Context context) {
            super(context);

        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.translate(200,200);
            Paint paint = new Paint();
            paint.setColor(Color.parseColor("#ff0000"));
            paint.setTextSize(50);
            paint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText("hahah",0,0,paint);
        }
    }

    public void addTestThread(){

        for (int i = 0; i <30 ; i++) {
            Thread thread1 = new Thread(new TimeThread());
            thread1.start();
        }



    }

    int x = 1;
    boolean isloop = true;
    String msg;
    StringBuffer sb = new StringBuffer();

    class TimeThread implements Runnable{
        @Override
        public void run() {
            while (isloop){
                try {
                    Thread.sleep(100);
                    x++;
                    //sb.append("msg");
                    //sb.append(x);
                    //msg = sb.toString();
                    //sb.delete(0,sb.length());

                   //Log.d("TimeThread",msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    protected void onDestroy() {
        isloop = false;
        super.onDestroy();
    }


    private static class TestHandler{

        private Handler mHandler;

        public void createHandler(){
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Looper.prepare();
                    mHandler = new Handler(){
                        @Override
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg);
                            switch (msg.what){
                                case 1:
                                    Log.d("testhandler","thread name ="+Thread.currentThread().getName()+"handle msg ="+(String)(msg.obj).toString());
                                    break;
                            }
                        }
                    };
                    Looper.loop();
                    //sendMessage();
                }
            });
        }


        public void sendMessage(Handler handler,String msg){
            Message obtain = Message.obtain(handler);
            obtain.what = 1;
            obtain.obj="from thread to thread"+msg;
            mHandler.sendMessage(obtain);
        }

    }



}
