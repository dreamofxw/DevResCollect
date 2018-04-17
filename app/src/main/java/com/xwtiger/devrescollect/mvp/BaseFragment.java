package com.xwtiger.devrescollect.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xwtiger.devrescollect.R;

/**
 * Created by xwadmin on 2018/4/17.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getResid(), container,false);
        init(inflate);
        return inflate;
    }

    public void init(View view){
        initView(view);
        initData();
        setListern();
    };

    public abstract  void setListern();
    public abstract  void initView(View view);
    public abstract void initData();
    public abstract int getResid();


}
