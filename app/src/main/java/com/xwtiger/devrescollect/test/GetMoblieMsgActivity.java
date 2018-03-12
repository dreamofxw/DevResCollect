package com.xwtiger.devrescollect.test;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;

import java.util.Properties;

/**
 * Created by xwadmin on 2018/3/12.
 */

public class GetMoblieMsgActivity extends BaseActivity {


    private TextView tvGetSysCpu;
    private TextView tvShowCpuInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_getmobilemsg);

    }

    @Override
    public void initView() {
        tvGetSysCpu = (TextView) findViewById(R.id.tv_getcpuinfo);
        tvShowCpuInfo = (TextView) findViewById(R.id.tv_cpucontent);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {
        tvGetSysCpu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_getcpuinfo:
                getCpuInfo();
                getInfoBySystem();
                break;

        }
    }

    private void getCpuInfo(){
        String[] supportedAbis = Build.SUPPORTED_ABIS;
        StringBuilder sb = new StringBuilder();
        if(supportedAbis != null){
            for(String str:supportedAbis){
                sb.append(str);
                sb.append("##");
            }
        }
        tvShowCpuInfo.setText(sb.toString());
    }

    private void getInfoBySystem(){
        Properties properties = System.getProperties();
        Log.d("getInfoBySystem",properties.toString());
        String osVersion = System.getProperty("os.version");
        Log.d("getInfoBySystem","os.version ="+osVersion);
        String baseOs = Build.VERSION.BASE_OS;
        Log.d("getInfoBySystem","baseOs ="+baseOs);

        String sysABI = System.getProperty("os.arch");
        Log.d("getInfoBySystem","sysABI ="+sysABI);
    }
}
