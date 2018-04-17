package com.xwtiger.devrescollect.mvp.testmvp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.mvp.BaseFragment;

/**
 * Created by xwadmin on 2018/4/17.
 */

public class TestMvpFragment extends BaseFragment implements TestContrant.View {

    private TestContrant.Presenter mPresenter;

    private Button btn1;
    private Button btn2;

    @Override
    public void setListern() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    public void initView(View view){
        btn1 = (Button) view.findViewById(R.id.btn1);
        btn2 = (Button) view.findViewById(R.id.btn2);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getResid() {
        return R.layout.frag_mvptest;
    }

    @Override
    public void setPresenter(TestContrant.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void testViewOnew() {
        Log.d("mvptest","testViewOnew");
    }

    @Override
    public void testViewTwo() {
        Log.d("mvptest","testViewTwo");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                    mPresenter.testPresenterFirst();
                break;
                case R.id.btn2:
                    mPresenter.testPresenterTwo();
                break;
        }
    }
}
