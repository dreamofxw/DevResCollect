package com.xwtiger.devrescollect.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.utils.SharedPreferencesUtils;

/**
 *
 * Created by Busap-112 on 2018/1/9.
 *
 */

public class TestNightModeActivity extends BaseActivity {

    private Button btn_mode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testnightmode);

        if(SharedPreferencesUtils.getNightMode(mContext)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    public void initView() {
        btn_mode = (Button) findViewById(R.id.btn_changemode);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {
        btn_mode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_changemode:
                changNightMode();
                break;
        }
    }

    public void changNightMode(){

        if(SharedPreferencesUtils.getNightMode(mContext)){
            SharedPreferencesUtils.saveNightMode(mContext,false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }else{
            SharedPreferencesUtils.saveNightMode(mContext,true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        Log.d("TestNightMode_out","isNightMode ="+SharedPreferencesUtils.getNightMode(mContext));
    }
}
