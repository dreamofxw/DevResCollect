package com.xwtiger.devrescollect.study.androidapi.event;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.xwtiger.devrescollect.utils.Utils;

/**
 * Created by Busap-112 on 2017/12/20.
 */

public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("testevent", "【市长】任务<" + Utils.actionToString(ev.getAction()) + "> : 需要分派");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean bo = true;
        Log.d("testevent", "【市长】任务<" + Utils.actionToString(ev.getAction()) + "> : 拦截吗？" + bo);
        return bo;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean bo = false;
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN :

                bo = false;
              break;
            case MotionEvent.ACTION_MOVE:
                bo = false;
                break;
            case MotionEvent.ACTION_UP:
                bo = false;
                break;
        }
        Log.d("testevent", "【市长】任务<" + Utils.actionToString(ev.getAction()) + "> : 农民真没用，下次再也不找你了，我自己来尝试一下。能解决？" + bo);
        return bo;
    }
}
