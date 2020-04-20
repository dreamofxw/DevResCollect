package com.xwtiger.devrescollect.test.live;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class CustomerFrameLayout extends LinearLayout {
    public CustomerFrameLayout(@NonNull Context context) {
        super(context);
    }

    public CustomerFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomerFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(iInterceptEvent !=null){
            Log.d("testclickoutside", "dispatchTouchEvent: ");
            iInterceptEvent.interceptEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }




    public IInterceptEvent iInterceptEvent;

    public void setiInterceptEvent(IInterceptEvent iInterceptEvent) {
        this.iInterceptEvent = iInterceptEvent;
    }

    public interface IInterceptEvent{
         void interceptEvent(MotionEvent ev);
    }
}
