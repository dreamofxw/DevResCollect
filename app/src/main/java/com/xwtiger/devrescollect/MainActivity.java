package com.xwtiger.devrescollect;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.net.OkHttpClientManager;
import com.xwtiger.devrescollect.net.ResultCallBack;
import com.xwtiger.devrescollect.study.androidapi.AnmitorStudy;
import com.xwtiger.devrescollect.study.androidapi.service.TestService;
import com.xwtiger.devrescollect.test.TestActivity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

public class MainActivity extends BaseActivity {

    private TextView textView;
    private Button btn_start;
    private Button btn_reversal;
    private static TestJava testjava = new TestJava();

    private TextView nextpage;

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


    private  Context mContext;

    private TextView tv_bind;
    private TextView tv_unbind;
    private TextView tv_start;
    private TextView tv_stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        textView = (TextView) findViewById(R.id.tv);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_reversal = (Button) findViewById(R.id.btn_reversal);
        nextpage = (TextView) findViewById(R.id.nextpage);



        //test
        tv_bind = findViewById(R.id.tv_bind);
        tv_unbind = findViewById(R.id.tv_unbind);
        tv_bind.setOnClickListener(this);
        tv_unbind.setOnClickListener(this);

        tv_start = findViewById(R.id.tv_startservice);
        tv_stop = findViewById(R.id.tv_stopservice);
        tv_start.setOnClickListener(this);
        tv_stop.setOnClickListener(this);

//        btn_start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    System.out.println("");
//            }
//        });
        btn_reversal.setOnClickListener(this);
        btn_start.setOnClickListener(this);
        nextpage.setOnClickListener(this);

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

        //test

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
                //AnmitorStudy.test2(textView);
                //AnmitorStudy.testNoValue(textView);
                AnmitorStudy.testNoValue(btn_start);
                break;
            case R.id.btn_reversal:
                AnmitorStudy.reversalAnmitor(textView,btn_start.getHeight()+10);
                break;
            case R.id.nextpage:
                Intent intent = new Intent(this, TestActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_bind:
                Log.d(TestService.tag,"mainact click bind");
                Intent bs = new Intent(this, TestService.class);
                this.bindService(bs,serviceConnection,Context.BIND_AUTO_CREATE);
                break;
            case R.id.tv_unbind:
                Log.d(TestService.tag,"mainact click unbind");
                this.unbindService(serviceConnection);
                break;
            case R.id.tv_startservice:
                Log.d(TestService.tag,"mainact click start service");
                Intent ss = new Intent(this, TestService.class);
                startService(ss);
                break;
            case R.id.tv_stopservice:
                Log.d(TestService.tag,"maniact click stop service");
                Intent ss1 = new Intent(this, TestService.class);
                stopService(ss1);
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

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TestService.tag,"mainactivity onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TestService.tag," mainactivity onServiceDisconnected");
        }
    };
}


