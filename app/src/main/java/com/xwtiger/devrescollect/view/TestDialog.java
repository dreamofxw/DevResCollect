package com.xwtiger.devrescollect.view;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.test.TestFragment;

/**
 * author:xw
 * Date:2018-12-06 9:49
 * Description:
 * //<editor-fold desc="">
 * //</editor-fold>
 */
public class TestDialog extends Dialog {

    private Activity mActivity;

    public TestDialog(@NonNull Context context) {
        super(context);
        if(context instanceof  Activity){
            mActivity = (Activity) context;
        }
    }

    public TestDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.testdialog);


    }
}
