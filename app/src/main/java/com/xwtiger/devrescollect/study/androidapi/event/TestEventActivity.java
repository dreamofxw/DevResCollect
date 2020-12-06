package com.xwtiger.devrescollect.study.androidapi.event;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.utils.Utils;

import okhttp3.internal.Util;


/**
 * Created by Busap-112 on 2017/12/20.
 */

public class TestEventActivity extends BaseActivity {




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_testevent);





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
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("testevent", "【总统】任务<" + Utils.actionToString(ev.getAction()) + "> : 需要分派");
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean bo = false;
        Log.d("testevent", "【总统】任务<" + Utils.actionToString(event.getAction()) + "> : 下面都解决不了，下次再也不能靠你们了，哼…只能自己尝试一下啦。能解决？" + bo);
        return bo;
    }

}
