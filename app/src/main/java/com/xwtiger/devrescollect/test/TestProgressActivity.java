package com.xwtiger.devrescollect.test;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;

import com.xwtiger.devrescollect.view.progressview.ProgressLayout ;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executors;

/**
 * @author : 胥文
 * @date : 2020/8/29 14:18
 * @desc :
 */

public class TestProgressActivity extends BaseActivity {
    private ProgressLayout progressLayout;
    private String savePath ;
    private ImageView iv_loading;
    private ImageView iv_loading1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_testprogress);
        progressLayout = (ProgressLayout) findViewById(R.id.progressLayout);
        iv_loading = (ImageView) findViewById(R.id.iv_loading);
        iv_loading1 = (ImageView) findViewById(R.id.iv_loading1);


//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.loading);
//        iv_loading.startAnimation(animation);
//
//
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv_loading1,"rotation",0,359);
//        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
//        objectAnimator.setDuration(600);
//        objectAnimator.start();


        progressLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressLayout.start();
            }
        });

        savePath = Environment.getExternalStorageDirectory()+"/aatestguide";


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // 显示给用户的解释
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1111);
            }
        } else {

            copydata();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        copydata();
    }

    public  void copydata(){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                copyAssetAndWrite("defultvideo.mp4");
            }
        });
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

    }

    @Override
    protected void onStop() {
        super.onStop();
        progressLayout.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        progressLayout.cancel();
    }

    public String getCachepath(){
        return savePath;
    }


    private boolean copyAssetAndWrite(String fileName) {
        try {
            String cachepath = getCachepath();
            File cacheDir = new File(cachepath);
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            File outFile = new File(cacheDir, fileName);
            if (!outFile.exists()) {
                boolean res = outFile.createNewFile();
                if (!res) {
                    return false;
                }
            } else {
                if (outFile.length() > 10) {//表示已经写入一次
                    return true;
                }
            }

            InputStream is = getAssets().open(fileName);
            FileOutputStream fos = new FileOutputStream(outFile);
            byte[] buffer = new byte[1024];
            int byteCount;
            while ((byteCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteCount);
            }
            fos.flush();
            is.close();
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void copyFilesFromAssets(Context context, String assetsPath, String savePath) {
        try {
            File file = new File(savePath);
            String fileNames[] = context.getAssets().list(assetsPath);// 获取assets目录下的所有文件及目录名
            if (fileNames.length > 0) {// 如果是目录
                file.mkdirs();// 如果文件夹不存在，则递归
                for (String fileName : fileNames) {
                    copyFilesFromAssets(context, assetsPath + "/" + fileName,
                            savePath + "/" + fileName);
                }
            } else {// 如果是文件
                InputStream is = context.getAssets().open(assetsPath);
                FileOutputStream fos = new FileOutputStream(new File(savePath));
                byte[] buffer = new byte[1024];
                int byteCount = 0;
                while ((byteCount = is.read(buffer)) != -1) {// 循环从输入流读取
                    // buffer字节
                    fos.write(buffer, 0, byteCount);// 将读取的输入流写入到输出流
                }
                fos.flush();// 刷新缓冲区
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
