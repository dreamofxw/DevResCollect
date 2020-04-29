package com.xwtiger.devrescollect.study.androidapi.draw.testdraw;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

public class TestFragmentLayout extends FrameLayout {


    private boolean isComplete = false;
    public TestFragmentLayout(@NonNull Context context) {
        super(context);
    }

    public TestFragmentLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestFragmentLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if(!isComplete){
            isComplete = true;
            if(listenerDraw !=null){
                listenerDraw.onDrawEnd();
            }
        }
        Log.d("testdrawprocess", "TestFragmentLayout dispatchDraw: ");
    }

    private IListenerDraw listenerDraw;

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public void setListenerDraw(IListenerDraw listenerDraw) {
        this.listenerDraw = listenerDraw;
    }

    public interface IListenerDraw{
        boolean onDrawEnd();
    }
}
