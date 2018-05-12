package com.xwtiger.devrescollect;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.net.OkHttpClientManager;
import com.xwtiger.devrescollect.net.ResultCallBack;
import com.xwtiger.devrescollect.study.androidapi.AnmitorStudy;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.internal.http.HttpEngine;

public class MainActivity extends BaseActivity {

    private TextView textView;
    private Button btn_start;
    private Button btn_reversal;
    private static TestJava testjava = new TestJava();

//    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
//    private static final int COUNT_BITS = Integer.SIZE - 3;
//    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;
//
//    // runState is stored in the high-order bits
//    private static final int RUNNING    = -1 << COUNT_BITS;
//    private static final int SHUTDOWN   =  0 << COUNT_BITS;
//    private static final int STOP       =  1 << COUNT_BITS;
//    private static final int TIDYING    =  2 << COUNT_BITS;
//    private static final int TERMINATED =  3 << COUNT_BITS;
//
//    // Packing and unpacking ctl
//    private static int runStateOf(int c)     { return c & ~CAPACITY; }
//    private static int workerCountOf(int c)  { return c & CAPACITY; }
//    private static int ctlOf(int rs, int wc) { return rs | wc; }


    //static private ThreadLocal mThreadLocal;
    
    //private static int x =0;

    private MyHandler mMyHandler;


    private static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        textView = (TextView) findViewById(R.id.tv);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_reversal = (Button) findViewById(R.id.btn_reversal);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    System.out.println("");
            }
        });
        btn_reversal.setOnClickListener(this);

       // mThreadLocal = new ThreadLocal();

        String url = "http://www.baidu.com";
        OkHttpClientManager.getInstance().getAsynHttp(url, new ResultCallBack() {
            @Override
            public void onError(Request request, Exception e) {
                Log.d("testmainactivity",""+e.getMessage());
            }

            @Override
            public void onRespone(String str) throws IOException {
               Log.d("testmainactivity","get"+str);
            }
        });

        String urlpost = "http://ip.taobao.comm/service/getIpInfo.php";
        Map map = new HashMap();
        map.put("ip","59.108.54.37");
        OkHttpClientManager.getInstance().postAsyHttp(urlpost, new ResultCallBack() {
            @Override
            public void onError(Request request, Exception e) {
                Log.d("testmainactivity","post"+e.getMessage());
                Log.d("testmainactivity","post"+request.headers().toString());
            }
            @Override
            public void onRespone(String str) throws IOException {
                Log.d("testmainactivity","post"+str);
            }
        },map);

        //Glide.with(this)
        mMyHandler = new MyHandler();

        Message msg = Message.obtain();
        msg.what =1;
        msg.obj = "haha";
        mMyHandler.sendMessage(msg);

        mContext = this;
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

    private void test(int t){
        System.out.println("");
    }


    private void test12(){
        Thread t = new Thread(new MyRunnable());
        t.start();
    }

     class MyRunnable implements Runnable{
        int count = 100;
        @Override
        public void run() {
            while(count >1){
               /* try {
                    mThreadLocal.set("haha");
                    Log.d("mThreadLocal",(String)mThreadLocal.get()) ;
                    mThreadLocal.set("zhangshan");
                    Log.d("mThreadLocal",(String)mThreadLocal.get());
                    count --;
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }

        private void add(int location){
            /*try {
                Class<? extends ThreadLocal> aClass = mThreadLocal.getClass();
                Field threadLocalHashCode = aClass.getDeclaredField("threadLocalHashCode");
                threadLocalHashCode.setAccessible(true);
                int o = (int) threadLocalHashCode.get(mThreadLocal);
                Log.d("mThreadLocal","location ="+location+"before threadLocalHashCode = "+o);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }*/
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                AnmitorStudy.test2(textView);
                break;
            case R.id.btn_reversal:

                AnmitorStudy.reversalAnmitor(textView,btn_start.getHeight()+10);
                break;
        }
    }





    public void test(){
        int cpunum = Runtime.getRuntime().availableProcessors();
        Log.d("testAsynTask","cpunum ="+cpunum);
        for(int i=0;i<138;i++){
            new MyAsynTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");
            Log.d("testAsynTask","create task i="+i);
        }
        Log.d("testAsynTask","create finish");
    }

    private static class MyAsynTask extends AsyncTask<String,Integer,String> {

        public MyAsynTask(){
            super();

        }

        @Override
        protected String doInBackground(String... objects) {
            printTime();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String o) {
            printTime();
            Log.d("testAsynTask","-------end");
        }

        public void printTime(){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //System.out.printtestAsynTaskln("threadName ="+Thread.currentThread().getName()+","+sdf.format(new Date()));

            Log.d("testAsynTask","threadName ="+Thread.currentThread().getName()+","+sdf.format(new Date()));
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if(btn_start != null){
//            btn_start.setOnClickListener(null);
//        }
    }
    
    
    private static class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    }
}
