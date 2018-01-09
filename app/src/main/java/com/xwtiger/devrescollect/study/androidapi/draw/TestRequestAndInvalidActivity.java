package com.xwtiger.devrescollect.study.androidapi.draw;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;

/**
 * Created by Busap-112 on 2018/1/2.
 */

public class TestRequestAndInvalidActivity extends BaseActivity {

    private TextView tv_req;
//    private TextView tv_inv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_testreqandinv);

        Log.d("TestTextView","-------print size --------");
        Log.d("TestTextView","testdraw_120 ="+getResources().getDimensionPixelSize(R.dimen.testdraw_120));
        Log.d("TestTextView","testdraw_left ="+getResources().getDimensionPixelSize(R.dimen.testdraw_left));
        Log.d("TestTextView","testdraw_top ="+getResources().getDimensionPixelSize(R.dimen.testdraw_top));
        Log.d("TestTextView","testdraw_right ="+getResources().getDimensionPixelSize(R.dimen.testdraw_right));
        Log.d("TestTextView","testdraw_bottomer ="+getResources().getDimensionPixelSize(R.dimen.testdraw_bottomer));

    }

    @Override
    public void initView() {
        tv_req = (TextView) findViewById(R.id.tv_req);
//        tv_inv = (TextView) findViewById(R.id.tv_inv);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {
        tv_req.setOnClickListener(this);
//        tv_inv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_req:
                //tv_req.requestLayout();
                Log.d("TestTextView","tv_req.getWidth() ="+tv_req.getWidth());
                Log.d("TestTextView","tv_req.getHeight ="+tv_req.getHeight());
                break;
           /* case R.id.tv_inv:
                tv_inv.invalidate();
                break;*/
        }
    }
}
