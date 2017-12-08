package com.xwtiger.devrescollect.utils;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

/**
 * Created by Busap-112 on 2017/12/8.
 */

public class TabLayoutUtils {




    /**
     * 修改tablayout 线的宽度
     * @param tabs
     * @param leftDip
     * @param rightDip
     * @param dporpx true是dp,false是px
     *
     */
    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip,boolean dporpx) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(dporpx?TypedValue.COMPLEX_UNIT_DIP:TypedValue.COMPLEX_UNIT_PX, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(dporpx?TypedValue.COMPLEX_UNIT_DIP:TypedValue.COMPLEX_UNIT_PX, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;

            child.setLayoutParams(params);
            child.invalidate();
        }

    }


}
