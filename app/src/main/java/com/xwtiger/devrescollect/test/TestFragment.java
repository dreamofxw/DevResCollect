package com.xwtiger.devrescollect.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseLazyFragment;

/**
 * author:xw
 * Date:2018-12-06 9:57
 * Description:
 * //<editor-fold desc="">
 * //</editor-fold>
 */
public class TestFragment extends BaseLazyFragment{


    public static TestFragment newInstance(){
        TestFragment testFragment = new TestFragment();
        Log.d("testlift","newInstance");
        return testFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("testlift","onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.testfragment, container, false);
        Log.d("testlift","onCreateView");
        return inflate;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("testlift","onResume");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("testlift","onDestroyView");
    }

    @Override
    protected void lazyLoad() {
        Log.d("testlift","lazyLoad");
    }
}
