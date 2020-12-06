package com.xwtiger.devrescollect.study.androidapi.event;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.xwtiger.devrescollect.utils.Utils;

/**
 * Created by Busap-112 on 2017/12/20.
 */

public class MyFrameLayout extends FrameLayout {
    public MyFrameLayout(@NonNull Context context) {
        super(context);
    }

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("testevent", "【省长】任务<" + Utils.actionToString(ev.getAction()) + "> : 需要分派");
        return super.dispatchTouchEvent(ev);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean bo = false;
        Log.d("testevent", "【省长】任务<" + Utils.actionToString(ev.getAction()) + "> : 拦截吗？" + bo);
        return bo;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean bo = false;
        Log.d("testevent", "【省长】任务<" + Utils.actionToString(ev.getAction()) + "> : 市长是个废物，下次再也不找你了，我自己来尝试一下。能解决？" + bo);
        return bo;
    }



}
