package com.xwtiger.devrescollect.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.xwtiger.devrescollect.R;


public class BottomerDialog extends Dialog implements View.OnClickListener {


    private Context mContext;



    public BottomerDialog(Context context, int themeResId) {
        super(context, R.style.CommonDialogStyle);
        mContext = context;
    }

    public BottomerDialog(Context context) {
        this(context, R.style.CommonDialogStyle);
        Log.d("testdialog2", "BottomerDialog: 构造函数");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_exit);

        initView();
        initValues();
        setListener();
        Log.d("testdialog2", "BottomerDialog: onCreate 初始化");

    }

    private void initView() {

        
    }

    private void initValues() {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        lp.width = (dm.widthPixels) *7/8;// 让dialog的宽占满屏幕的宽
        lp.gravity = Gravity.BOTTOM;
        window.setWindowAnimations(R.style.AnimBottom);
        window.setAttributes(lp);

    }

    public void setListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


        }
        dismiss();
    }


    @Override
    public void show() {
        super.show();
        Log.d("testdialog2", "BottomerDialog: show ");

    }
}
