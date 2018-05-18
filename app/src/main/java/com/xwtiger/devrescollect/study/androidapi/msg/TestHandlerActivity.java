package com.xwtiger.devrescollect.study.androidapi.msg;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;

/**
 * author:xw
 * Date:2018-05-12 16:49
 * Description:
 */
public class TestHandlerActivity extends BaseActivity {


    public static int count = 0;
    private TextView mTv_rootcount;
    private TestLinearlayout mTll;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("testhandlemsg","activity handlemsg"+msg.getTarget().toString());
            mTv_rootcount.setText(String.valueOf(count));
            count++;
            startCount();
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_testhandlemsg);
        init();
    }

    @Override
    public void initView() {
        mTv_rootcount = (TextView) findViewById(R.id.tv_rootcount);
        mTll = (TestLinearlayout) findViewById(R.id.tll);
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

    public void startCount(){
        handler.sendEmptyMessageDelayed(0,4000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startCount();
        mTll.startCount();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        mTll.stopCount();
        super.onDestroy();
    }
}
