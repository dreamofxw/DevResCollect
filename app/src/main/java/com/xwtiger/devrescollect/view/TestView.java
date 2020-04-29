package com.xwtiger.devrescollect.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class TestView extends RelativeLayout {

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private boolean swith = false;

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if(swith){
//            return true;
//        }else{
//            return super.dispatchTouchEvent(ev);
//        }
//    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(swith){
            return true;
        }else{
            return super.onInterceptTouchEvent(ev);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("testview", "onTouchEvent:getAction= "+event.getAction());
        return super.onTouchEvent(event);
    }

    public boolean isSwith() {
        return swith;
    }

    public void setSwith(boolean swith) {
        this.swith = swith;
    }
}
