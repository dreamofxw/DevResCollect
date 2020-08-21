package com.xwtiger.devrescollect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.xwtiger.devrescollect.base.BaseActivity;

import rx.Observable;
import rx.functions.Action1;

/**
 * @author : 胥文
 * @date : 2020/8/19 17:43
 * @desc :
 */

public class TestRxActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_testrx);
        init();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Observable.just("1","2","3","4","5","6","7","8","9").skip(5).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("testrxjava12", "call: s="+s);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
