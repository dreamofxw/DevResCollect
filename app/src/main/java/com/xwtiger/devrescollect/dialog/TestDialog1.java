package com.xwtiger.devrescollect.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.xwtiger.devrescollect.R;

public class TestDialog1 extends Dialog{
    private Context mContext;

    public TestDialog1(@NonNull Context context) {
        super(context, R.style.CommonDialogStyle);
        mContext = context;
    }

    public TestDialog1(@NonNull Context context, int themeResId) {
        super(context, R.style.CommonDialogStyle);

    }

    protected TestDialog1(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_testtranslate);
        initView();
        initValues();
        setListener();

    }

    private void initView() {
    }

    private void initValues() {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        //lp.width = (dm.widthPixels) * 3 / 4;// 让dialog的宽占满屏幕的宽
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);

    }

    private void setListener() {

    }
}
