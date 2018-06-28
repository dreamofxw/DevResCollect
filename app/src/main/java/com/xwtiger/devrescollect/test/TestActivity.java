package com.xwtiger.devrescollect.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.statistics.ActionLogBean;
import com.xwtiger.devrescollect.statistics.AdditionalBean;
import com.xwtiger.devrescollect.statistics.ExceptionUpLoadUtil;
import com.xwtiger.devrescollect.statistics.TestStatistics;
import com.xwtiger.devrescollect.statistics.UploadEvent;
import com.xwtiger.devrescollect.statistics.YouShuStatistics;
import com.xwtiger.devrescollect.statistics.cache.disc.FileManager;
import com.xwtiger.devrescollect.statistics.cache.memory.MemorySizeCalculator;
import com.xwtiger.devrescollect.study.androidapi.msg.TestHandlerActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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


        handlerThread = new HandlerThread("handlerthread");
        handlerThread.start();

        handlers123 = new Handler(handlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d("handlerThread123","msg ="+msg.what+",threadname"+Thread.currentThread().getName());
            }
        };

        handlers123.sendEmptyMessage(1);


        //test

//        FileManager.saveFile(this,"hahha");

        /**
         * 测试日志
         */

//        TestTask task = new TestTask();
//        for(int i=0;i<5;i++){
//            ExecutorService executorService = Executors.newCachedThreadPool();
//            executorService.execute(task);
//        }


        //isExiteOfDrawable(this,"123");
        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.ic_launcher);


        int resourceByReflect1 = getDrawableResourceByReflect("ic_launcher");
        int resourceByReflect2 = getMipmapResourceByReflect("ic_launcher11222");
        Log.d("resourceByReflect", "onCreate: resourceByReflect1="+resourceByReflect1);
        Log.d("resourceByReflect", "onCreate: resourceByReflect2="+resourceByReflect2);

        int resourceByReflect3 = getMipmapResourceByReflect("icon_fengjin");
        int resourceByReflect4 = getDrawableResourceByReflect("icon_fengjin");
        Log.d("resourceByReflect", "onCreate: resourceByReflect3="+resourceByReflect3);
        Log.d("resourceByReflect", "onCreate: resourceByReflect4="+resourceByReflect4);

        Log.d("actvitylifecycle", "onCreate: MODEL ="+ Build.MODEL+",SDK ="+Build.VERSION.SDK_INT);

        parseJson();


        new MemorySizeCalculator(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("actvitylifecycle", "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("actvitylifecycle", "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("actvitylifecycle", "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("actvitylifecycle", "onStop: ");
    }

    @Override
    protected void onDestroy() {
        isloop = false;
        super.onDestroy();
        handlerThread.quitSafely();
        YouShuStatistics.getInstance().destroy();
        Log.d("actvitylifecycle", "onDestroy: ");
    }


    public int getDrawableResourceByReflect(String drawableName){
        Class<R.drawable> drawableClass = R.drawable.class;
        try{
            Field field = drawableClass.getField(drawableName);
            int resid = field.getInt(field.getName());
            return resid;
        }catch(Exception e){
            return 0;
        }
    }


    public int getMipmapResourceByReflect(String drawableName){
        Class<R.mipmap> mipmapClass = R.mipmap.class;
        try{
            Field field = mipmapClass.getField(drawableName);
            int resid = field.getInt(field.getName());
            return resid;
        }catch(Exception e){
            return 0;
        }
    }


    public boolean isExiteOfDrawable(Context context,String drawablename){

        int drawable = context.getResources().getIdentifier(drawablename, "drawable", context.getPackageName());
        Log.d(TAG, "isExiteOfDrawable: packagename="+context.getPackageName());
        //Log.d(TAG, "isExiteOfDrawable: drawable="+drawable);

//        context.getResources().
//        context.getResources().getDrawable(R.id."",null)


        return true;


    }

    static class TestTask implements Runnable{
        @Override
        public void run() {
//            for(int i =0;i<200;i++){
//                String name = Thread.currentThread().getName();
//                AdditionalBean additionalBean = new AdditionalBean("1234"+i,name);
//                ActionLogBean bean = new ActionLogBean("user_follow"+i,new Gson().toJson(additionalBean),"discover");
//                YouShuStatistics.getInstance().addEvent(bean);
//                try {
//                    Thread.sleep(2600);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }

    private HandlerThread handlerThread;
    private Handler handlers123 ;



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

                UploadEvent.upLoadLog();

                break;
            case R.id.btn_jumpmainact:
                Intent intent = new Intent(this, TestHandlerActivity.class);
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



    public static class TTHandler extends Handler
    {
        public TTHandler(Looper looper){
            super();
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("TTHandler","threadname = "+Thread.currentThread().getName()+",msg="+msg.what);

        }
    }


    public void parseJson(){
        String json = new Gson().toJson(new TestJson("xw","32"));
        try {
            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()){
                String next = keys.next();
                String value = jsonObject.optString(next);
                Log.d("parseJson", "parseJson: Iterator next ="+next);
                Log.d("parseJson", "parseJson: Iterator value ="+value);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    class TestJson{

        public String name;
        public String age;

        public TestJson(String name,String age){
            this.name = name;
            this.age = age;
        }

    }


}
