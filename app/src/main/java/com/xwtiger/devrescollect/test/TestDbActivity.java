package com.xwtiger.devrescollect.test;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xwtiger.devrescollect.MainActivity;
import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.db.TestDaoManage;
import com.xwtiger.devrescollect.db.TestModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TestDbActivity extends BaseActivity {


    private TextView tv_add;
    private TextView tv_remove;
    private TextView tv_updata;

    private TextView tv_start;
    private TextView tv_pause;
    private TextView tv_change;

    private HandlerThread handlerThread;
    private Handler mhandler ;
    private long num=0;
    private long total = 20;
    private boolean isChange= false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_testdb);
        init();
        //TestDaoManage.getInstance().init(this);


        String path = "./app/src/main/assets/";
        File file = new File(path);
//        Log.d("testassetmanager", "onCreate: path="+file.getAbsolutePath());
//        Log.d("testassetmanager", "onCreate: isDirectory="+file.isDirectory());

        AssetManager assetManager = this.getAssets();

        List<H5bean> h5cache = getfilesFromAssets(assetManager, "h5cache", new H5bean(), new ArrayList<H5bean>());

        Log.d("testassetmanager", "onCreate: "+new Gson().toJson(h5cache));

//        Log.d("testassetmanager", "onCreate: readjson()="+test());
//        Log.d("testassetmanager", "onCreate: getFromAssets="+getFromAssets("h5cache/manifest.json"));



        handlerThread = new HandlerThread("computertime");
        handlerThread.start();
        mhandler = new Handler(handlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                try {
                    num++;
                    Log.d("testhandthread", "handleMessage: num="+num+",threadname="+Thread.currentThread().getName());
                    Thread.sleep(1000);
                    if(num <total&&!isChange){
                        mhandler.sendEmptyMessage(0);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };




    }


    public String getFromAssets(String fileName){
        try {
            InputStreamReader inputReader = new InputStreamReader( getResources().getAssets().open(fileName) );
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";
            String Result="";
            while((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String test(String path){
        AssetManager manager = getResources().getAssets();
        try {
            InputStream inputStream = manager.open(path);
            InputStreamReader isr = new InputStreamReader(inputStream,
                    "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String length;
            while ((length = br.readLine()) != null) {
                sb.append(length + "\n");
            }
            //关流
            br.close();
            isr.close();
            inputStream.close();

            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("", "test: ");
        }
        return "null";
    }


//    private void requestMyPermissions() {
//
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            //没有授权，编写申请权限代码
//            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
//        } else {
//            Log.d(TAG, "requestMyPermissions: 有写SD权限");
//        }
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            //没有授权，编写申请权限代码
//            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
//        } else {
//            Log.d(TAG, "requestMyPermissions: 有读SD权限");
//        }
//    }






//    public static void io_StringReader(){
//
//        try {
//            StringReader stringReader = new StringReader();
//            StringWriter stringWriter = new StringWriter();
//            char[] chars = new char[1024];
//            int length;
//            while(-1 !=(length =stringReader.read(chars,0,chars.length))){
//                stringWriter.write(chars,0,length);
//                stringWriter.flush();
//            }
//            StringBuffer buffer = stringWriter.getBuffer();
//            System.out.println("buffer ="+buffer.toString());
//            stringWriter.close();
//            stringReader.close();
//        }  catch (Exception e) {
//            MyException.printStr(e);
//        }
//    }



    //升级状态改回来
    public  List<H5bean> getfilesFromAssets(AssetManager assetManager, String path,H5bean h5bean,List<H5bean> list) {
        Log.d("testassetmanager", "getfilesFromAssets: path="+path);
        try {
            String[] files = assetManager.list(path);
            Log.d("testassetmanager", "getfilesFromAssets: files ="+files.length);
//            H5bean h5bean = new H5bean();
//            list.add(h5bean);
            for(int i=0;i<files.length;i++){
                String temppath = path + "/"+files[i];
                if(files[i].contains(".zip")){
                    //是文件
                    h5bean.zippath = temppath;
                    if(!list.contains(h5bean)){
                        list.add(h5bean);
                    }
                }else if(files[i].contains(".json")){
                    //文件夹
                    h5bean.jsonpath = temppath;
                    if(!list.contains(h5bean)){
                        list.add(h5bean);
                    }
                }else{
                    //文件夹
                    getfilesFromAssets(assetManager,temppath,new H5bean(),list);
                }
                Log.d("testassetmanager", "getfilesFromAssets: "+files[i]);
            }
           ;

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("testassetmanager", "出现错误");

        }

        return list;
    }

    @Override
    public void initView() {
        tv_add = findViewById(R.id.tv_add);
        tv_remove = findViewById(R.id.tv_remove);
        tv_updata = findViewById(R.id.tv_updata);
        tv_start = findViewById(R.id.tv_start);
        tv_pause = findViewById(R.id.tv_pause);
        tv_change = findViewById(R.id.tv_change);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {
        tv_add.setOnClickListener(this);
        tv_remove.setOnClickListener(this);
        tv_updata.setOnClickListener(this);
        tv_start.setOnClickListener(this);
        tv_pause.setOnClickListener(this);
        tv_change.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_add:
                Log.d("testGlobalDB", "添加数据");

                TestDaoManage.getInstance().addDataForList(addList());
                break;
            case R.id.tv_remove:
                //TestDaoManage.getInstance().delData(makeData1());
                break;
            case R.id.tv_updata:
                //TestDaoManage.getInstance().updataData(makeData1());

                break;
            case R.id.tv_start:
                isChange = false;
                mhandler.sendEmptyMessage(0);
                break;
            case R.id.tv_pause:
                isChange = true;
                Log.d("testhandthread", "onClick: pause ");
                mhandler.removeCallbacksAndMessages(null);
                break;
            case R.id.tv_change:
                isChange = false;
                mhandler.removeCallbacksAndMessages(null);
                num =0;
                mhandler.sendEmptyMessage(0);
                break;
        }
    }

    public TestModel makeData(){
        TestModel testModel = new TestModel();
        testModel.uid= "111";
        testModel.group_id = "abc";
        testModel.isPop ="0";
        testModel.timestamp = "tsabddddd";
        return testModel;
    }


    public TestModel makeData1(){
        TestModel testModel = new TestModel();
        testModel.uid= "111";
        testModel.group_id = "abc";
        testModel.isPop ="1";
        testModel.timestamp = "tsabddddd";
        return testModel;
    }

    public List<TestModel> addList(){
        List<TestModel> list = new ArrayList<>();
        for(int i=0;i<8;i++){
            TestModel testModel = new TestModel();
            testModel.uid= "111==+"+i;
            testModel.group_id = "abc"+i;
            testModel.isPop ="0";
            testModel.timestamp = "tsabddddd";
            list.add(testModel);
        }
        return list;
    }

}
