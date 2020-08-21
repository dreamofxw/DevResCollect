package com.xwtiger.devrescollect.test;

import android.Manifest;
import android.accessibilityservice.AccessibilityService;
import android.accounts.Account;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.snappydb.DBFactory;
import com.xwtiger.devrescollect.MainActivity;
import com.xwtiger.devrescollect.MyException;
import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.dialog.TestDialog1;
import com.xwtiger.devrescollect.net.OkHttpClientManager;
import com.xwtiger.devrescollect.net.ResultCallBack;
import com.xwtiger.devrescollect.net.RetrofitTest;
import com.xwtiger.devrescollect.net.SimpleMockService;
import com.xwtiger.devrescollect.prsenter.DBPresenter;
import com.xwtiger.devrescollect.statistics.ActionLogBean;
import com.xwtiger.devrescollect.statistics.AdditionalBean;
import com.xwtiger.devrescollect.statistics.ExceptionUpLoadUtil;
import com.xwtiger.devrescollect.statistics.TestStatistics;
import com.xwtiger.devrescollect.statistics.UploadEvent;
import com.xwtiger.devrescollect.statistics.YouShuStatistics;
import com.xwtiger.devrescollect.statistics.cache.disc.FileManager;
import com.xwtiger.devrescollect.statistics.cache.memory.MemorySizeCalculator;
import com.xwtiger.devrescollect.study.androidapi.AnmitorStudy;
import com.xwtiger.devrescollect.study.androidapi.msg.TestHandlerActivity;
import com.xwtiger.devrescollect.study.androidapi.service.TestService;
import com.xwtiger.devrescollect.utils.CopressImageUtil;
import com.xwtiger.devrescollect.utils.PermissionChecker;
import com.xwtiger.devrescollect.view.TestDialog;
import com.xwtiger.devrescollect.view.TestPopuwindow;

import junit.framework.Test;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Request;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Busap-112 on 2017/11/10.
 *
 */

public class TestActivity extends BaseActivity {

    private RelativeLayout container;
    private Button btn_start;
    private TextView tv;
    private Button btnJump;
    private TestHandler mTestHandler;
    private TestHandler mTestHandler2;
    private TextView tv_null;
    private TextView tv_get;
    private TextView tv_pack;
    private TextView tv_put;
    private ImageView iv_progress;

    private TextView tv_bind;
    private TextView tv_unbind;
    private TextView tv_start;
    private TextView tv_stop;

    private TextView tv_26;

    private ImageView iv_background;

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
        super.setTheme(R.style.AppThemeReading);
        //getWindow().setBackgroundDrawable(new ColorDrawable(0xffffffff));
        //super.setTheme(R.style.AppThemeReading);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testlayout);
        container = (RelativeLayout) findViewById(R.id.container);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);

        tv_null = findViewById(R.id.tv_null);
        tv_get = findViewById(R.id.tv_get);
        iv_progress = findViewById(R.id.iv_progress);

        tv_bind = findViewById(R.id.tv_bind);
        tv_unbind = findViewById(R.id.tv_unbind);
        tv_bind.setOnClickListener(this);
        tv_unbind.setOnClickListener(this);

        tv_start = findViewById(R.id.tv_startservice);
        tv_stop = findViewById(R.id.tv_stopservice);
        tv_start.setOnClickListener(this);
        tv_stop.setOnClickListener(this);

        iv_background = findViewById(R.id.iv_background);

        iv_progress.setImageResource(R.drawable.ebook_ani);
        AnimationDrawable animationDrawable = (AnimationDrawable) iv_progress.getDrawable();
        animationDrawable.start();

        tv_null.setOnClickListener(this);
        tv_get.setOnClickListener(this);

        btnJump = (Button) findViewById(R.id.btn_jumpmainact);
        btnJump.setOnClickListener(this);

        tv_pack = findViewById(R.id.tv_pack);
        tv_put = findViewById(R.id.tv_put);
        tv_put.setOnClickListener(this);

        tv_pack.setText(getPackageName());

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

        ListView list = new ListView(this);
        //list.setAdapter();
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




        String str= "abc";
        Log.d("isGsonJson","isgsonjson = "+isGsonJson(str));
        //DBPresenter.testPut(new DBPresenter.TestBean("aaa","bbb"));



        TextView tv_26 = findViewById(R.id.tv_26);
        TextView tv_90 = findViewById(R.id.tv_90);
        TextView tv_18 = findViewById(R.id.tv_18);

        Log.d("testpadding","tv_26.getPaddingTop "+tv_26.getPaddingTop());
        Log.d("testpadding","v_26.getPaddingBottom "+tv_26.getPaddingBottom());
        Log.d("testpadding","tv_90.getPaddingBottom "+tv_90.getPaddingTop());
        Log.d("testpadding","tv_90.getPaddingBottom "+tv_90.getPaddingBottom());
        Log.d("testpadding","tv_18.getPaddingBottom "+tv_18.getPaddingTop());
        Log.d("testpadding","tv_18.getPaddingBottom "+tv_18.getPaddingBottom());

        
        testGson();


        Log.d("testempty", "onCreate: start 数据转换");
        Observable.empty().observeOn(Schedulers.io()).flatMap(new Func1<Object, Observable<?>>() {

            @Override
            public Observable<?> call(Object o) {
                Log.d("testempty", "onCreate: start flatmap="+Thread.currentThread().getName());

                return Observable.just("");
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                Log.d("testempty", "onCreate: start onnext=" + Thread.currentThread().getName());

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d("testempty", "onCreate: start onerror=" + Thread.currentThread().getName());

            }
        }, new Action0() {
            @Override
            public void call() {
                Log.d("testempty", "onCreate: start oncomplete=" + Thread.currentThread().getName());

            }
        });




        String str1 = "             ";
        String str2 = "     hhh";
        String str3 = "dd    ";

        if(!TextUtils.isEmpty(str1)&&!TextUtils.isEmpty(str1.trim())){
            Log.d("pringstr", "onCreate: 非空 str1="+str1.length()+",trim ="+str1.trim().length());
        }else{
            Log.d("pringstr", "onCreate:空 str1="+str1);
        }

        if(!TextUtils.isEmpty(str2)&&!TextUtils.isEmpty(str2.trim())){
            Log.d("pringstr", "onCreate: 非空 str2="+str2.length()+",trim="+str2.trim().length());
        }else{
            Log.d("pringstr", "onCreate:空 str2="+str2);
        }

        if(!TextUtils.isEmpty(str3)&&!TextUtils.isEmpty(str3.trim())){
            Log.d("pringstr", "onCreate: 非空 str3="+str3.length()+",trim ="+str3.trim().length());
        }else{
            Log.d("pringstr", "onCreate:空 str3="+str3);
        }



        String data = "#在抖音，记录美好生活#这大概就是冰http://jira.youshu.cc:9080/secure/Dashboard.jspa 复制此链接，打开【抖音短视频】，直接观看视频！";
        Log.d("testurl", "onCreate: data="+data);
//        Matcher matcher = TestRegexForUrl.WEB_URL.matcher(data);
//        if (matcher.find()){
//            Log.d("testurl", "onCreate: url="+matcher.group());
//        }


        Pattern pattern = Pattern.compile("https?://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]");
        Matcher matcher = pattern.matcher(data);
        if (matcher.find()) {
            Log.d("testurl", "onCreate: 位置="+"matched form " + matcher.start() + " to " + matcher.end());
            Log.d("testurl", "onCreate:substring "+data.substring(matcher.start(),matcher.end()));

            String group = matcher.group();
            Log.d("testurl", "onCreate: group="+group);
        }





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
        //YouShuStatistics.getInstance().destroy();
        Log.d("actvitylifecycle", "onDestroy: ");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PHOTO_REQUEST_GALLERY:
                if (data != null && data.getData() != null) {
                    Uri selectedImage = data.getData();
                    String picturePath = null;
                    if (selectedImage.toString().contains("content")) {
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};

                        Cursor cursor = this.getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);

                        if (cursor != null) {
                            cursor.moveToFirst();
                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            picturePath = cursor.getString(columnIndex);
                            cursor.close();
                        }
                    } else {
                        picturePath = selectedImage.getPath();
                    }

                    if (picturePath == null) {
                        return;
                    }
                    File f = new File(picturePath);

                    Log.d("testcompressUtils", "onActivityResult: "+f.getAbsolutePath());
                    Log.d("testcompressUtils", "exists=: "+f.exists());
                    //CopressImageUtil.getimage1(f.getAbsolutePath());


                    Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
//                    Bitmap bitmap1 = CopressImageUtil.zoomImg(bitmap, 768, 1024);
//
//                    Bitmap bitmap2 = CopressImageUtil.compressImage1(bitmap1);
                    Log.d("testcompressUtils", "onActivityResult: 压缩之前getWidth ="+bitmap.getWidth());
                    Log.d("testcompressUtils", "onActivityResult: 压缩之前 ="+bitmap.getHeight());

                    new AsyncTask<String,String,Bitmap>(){

                        @Override
                        protected Bitmap doInBackground(String[] objects) {
                            return CopressImageUtil.compressImage1(bitmap);
                        }

                        @Override
                        protected void onPostExecute(Bitmap o) {
                            super.onPostExecute(o);
                            iv_background.setImageBitmap(o);
                        }
                    }.execute(new String[]{});

                    break;
                }

        }
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
//                    MyException.printStr(e);
//                }
//            }
        }
    }

    private HandlerThread handlerThread;
    private Handler handlers123 ;
    private PermissionChecker mPermissionChecker = new PermissionChecker(this);


    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }

    private TestDialog1 testDialog;

    private static final int PHOTO_REQUEST_GALLERY = 3021;// 从相册中选择


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
               /* try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    MyException.printStr(e);
                }*/
                //Log.d("testValueAnmitor","btn_start height ="+(btn_start.getHeight()));
                //AnmitorStudy.testNoValue(tv);
//                try {
//                    RetrofitTest.test();
//                } catch (IOException e) {
//                    MyException.printStr(e);
//                }
                //addTestThread();
//                mTestHandler.sendMessage(mTestHandler.mHandler,"default");
//                mTestHandler.sendMessage(mTestHandler2.mHandler,"gei two");
//                handler.sendEmptyMessage(0);
//                handler2.sendEmptyMessage(0);
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.d("handler.post","thread name ="+Thread.currentThread().getName());
//                    }
//                });
//                mTestHandler.mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.d("handler.post","mTestHandler thread name ="+Thread.currentThread().getName());
//                    }
//                });
//
//                UploadEvent.upLoadLog();

//                try {
//                    SimpleMockService.test();
//                } catch (IOException e) {
//                    MyException.printStr(e);
//                }
//                    new Thread(){
//                        @Override
//                        public void run() {
//                            super.run();
//                            try {
//
//                            } catch (IOException e) {
//                                MyException.printStr(e);
//                            }
//                        }
//                    }.start();

//                if(mPermissionChecker.checkPermission()){
//                    Intent intent_live = new Intent(this,SWCameraStreamingActivity.class);
//                    startActivity(intent_live);
//                }
//                Intent intent_live = new Intent(this,SWCameraStreamingActivity.class);
//                startActivity(intent_live);
//                Intent intent_live = new Intent(this,TestMyListViewActivity.class);
//                startActivity(intent_live);
//                String url = "https://kyfw.12306.cn/otn/";
//                OkHttpClientManager.getInstance().getAsynHttp(url, new ResultCallBack() {
//                    @Override
//                    public void onError(Request request, Exception e) {
//                      Log.d("testtsl",e.getMessage());
//                      Log.d("testtsl","error");
//                    }
//
//                    @Override
//                    public void onRespone(String str) throws IOException {
//                        Log.d("testtsl",str);
//                        Log.d("testtsl","success");
//                    }
//                });

                /*Uri uri = Uri.parse("https://i.cnblogs.com/EditPosts.aspx?postid=9007907&uid=123&username=xww");

                System.out.println("getAuthority="+uri.getAuthority());
                System.out.println("getHost="+uri.getHost());
                System.out.println("getPath="+uri.getPath());
                System.out.println("getQuery="+uri.getQuery());
                System.out.println("getQueryParameterNames="+uri.getQueryParameterNames());
                System.out.println("getScheme="+uri.getScheme());
                System.out.println("getQueryParameters="+uri.getQueryParameters("postid"));*/


               /* TestDialog1 testDialog = new TestDialog1(this);
                testDialog.show();*/

               /* TestPopuwindow testPopuwindow = new TestPopuwindow(this);
                testPopuwindow.showAsDropDown(container);*/
                if(testDialog ==null){
                    testDialog = new TestDialog1(this);
                }
                testDialog.show();
                break;
            case R.id.btn_jumpmainact:
                //Intent intent = new Intent(this, TestHandlerActivity.class);
                //Intent intent = new Intent(this, MainActivity.class);
//                Intent intent = new Intent(this, TestAnimationActivity.class);
//                startActivity(intent);

                //压缩大小

                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        // 显示给用户的解释
                    } else {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1111);
                    }
                } else {

                    Intent intent = new Intent(Intent.ACTION_PICK, null);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                }
                break;
            case R.id.tv_null:
                //DBPresenter.deleteObj();
                break;
            case R.id.tv_get:
                DBPresenter.get();
                break;
            case R.id.tv_put:
//                DBPresenter.TestBean testBean = new DBPresenter.TestBean("123","345");
//                DBPresenter.testPut(testBean);
                String json= "abcahdadshfadsfads";
                Log.d("isGsonJson","isgsonjson = "+isGsonJson(json));
                break;
            case R.id.tv_bind:
                Log.d(TestService.tag,"click bind");
                Intent bs = new Intent(this, TestService.class);
                this.bindService(bs,serviceConnection,Context.BIND_AUTO_CREATE);
                break;
            case R.id.tv_unbind:
                Log.d(TestService.tag,"click unbind");
                this.unbindService(serviceConnection);
                break;
            case R.id.tv_startservice:
                Log.d(TestService.tag,"click start service");
                Intent ss = new Intent(this, TestService.class);
                startService(ss);
                break;
            case R.id.tv_stopservice:
                Log.d(TestService.tag,"click stop service");
                Intent ss1 = new Intent(this, TestService.class);
                stopService(ss1);
                break;

        }
    }


    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TestService.tag,"onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TestService.tag,"onServiceDisconnected");
        }
    };




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
                    MyException.printStr(e);
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
            MyException.printStr(e);
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

    public static boolean isGsonJson(String json) {
        if (TextUtils.isEmpty(json)) {
            return false;
        }
        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonSyntaxException e) {
            return false;
        } catch (JsonParseException e) {
            return false;
        }
    }


    public void testGson(){

        GsonBean bean= new GsonBean("aa","bb");

        String s = new Gson().toJson(bean);

        Log.d("testgson", "testGson: s="+s);

        Log.d("testgson", "=============================== ");

        GsonBean gsonBean = new Gson().fromJson(s, GsonBean.class);
        Log.d("testgson", "testGson: gsonBean.a="+gsonBean.a);
        Log.d("testgson", "testGson: gsonBean.b="+gsonBean.b);



    }


    class GsonBean{

        @SerializedName(value = "key1",alternate = {"key111","key11"})
        public String a;

        @SerializedName("key2")
        public String b;


        public GsonBean(String a,String b){
            this.a=a;
            this.b=b;
        }
    }


    class GsonBean1{

        @SerializedName("key111")
        public String a;

        @SerializedName("key2")
        public String b;


        public GsonBean1(String a,String b){
            this.a=a;
            this.b=b;
        }
    }


}
