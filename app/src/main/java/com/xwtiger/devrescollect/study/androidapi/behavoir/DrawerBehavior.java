package com.xwtiger.devrescollect.study.androidapi.behavoir;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DrawerBehavior extends CoordinatorLayout.Behavior<TextView>{


    public static final String TAG ="testbehavior";

//    public DrawerBehavior() {
//    }

    public DrawerBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private int mStartY;

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
        //记录开始的Y坐标  也就是toolbar起始Y坐标
        if(mStartY == 0) {
            mStartY = (int) dependency.getY();
        }

        //计算toolbar从开始移动到最后的百分比
        float percent = dependency.getY()/mStartY;
        Log.d("testbehavior", "onDependentViewChanged: percent="+percent);

        //改变child的坐标(从消失，到可见)
        child.setY(child.getHeight()*(1-percent) - child.getHeight());


        return true;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        //Log.d(TAG, "layoutDependsOn: "+(dependency instanceof Toolbar));
        //Log.d(TAG, "layoutDependsOn: dendency ="+dependency.getClass().getSimpleName());
        return dependency instanceof android.support.v7.widget.Toolbar;
    }
}
