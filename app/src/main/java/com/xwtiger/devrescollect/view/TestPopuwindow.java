package com.xwtiger.devrescollect.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.test.TestFragment;

/**
 * author:xw
 * Date:2018-12-06 10:25
 * Description:
 * //<editor-fold desc="">
 * //</editor-fold>
 */
public class TestPopuwindow extends PopupWindow{


    public TestPopuwindow(Context context){

        View inflate = View.inflate(context, R.layout.testdialog, null);

        setContentView(inflate);


        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        setWidth(displayMetrics.widthPixels);
        setHeight(displayMetrics.heightPixels);


        /*setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TestPopuwindow.this.dismiss();
                return false;
            }
        });*/

    }






}
