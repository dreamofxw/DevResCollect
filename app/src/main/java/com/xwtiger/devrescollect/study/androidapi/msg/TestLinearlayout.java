package com.xwtiger.devrescollect.study.androidapi.msg;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xwtiger.devrescollect.R;

/**
 * author:xw
 * Date:2018-05-12 16:53
 * Description:
 */
public class TestLinearlayout extends LinearLayout {

    private TextView mTv_Count;
    private static int count = 0;

    public TestLinearlayout(Context context) {
        super(context);
        init(context);
    }

    public TestLinearlayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TestLinearlayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("testhandlemsg","TestLinearlayout handlemsg"+msg.getTarget().toString());
            mTv_Count.setText(String.valueOf(count));
            count++;
            startCount();
        }
    };

    private void init(Context context){
        View inflate = inflate(context, R.layout.view_testhandlemsg, this);
        mTv_Count = (TextView) inflate.findViewById(R.id.tv_count);
    }


    public void startCount(){
        handler.sendEmptyMessageDelayed(0,4000);
    }

    public void stopCount(){
        handler.removeCallbacksAndMessages(null);
    }


}
