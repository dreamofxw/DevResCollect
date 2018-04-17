package com.xwtiger.devrescollect.mvp.testmvp;

import android.util.Log;

/**
 * Created by xwadmin on 2018/4/17.
 */

public class TestPresenter implements TestContrant.Presenter {

    private TestContrant.View mView;

    public TestPresenter(TestContrant.View view){
        this.mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        Log.d("mvptest","subscribe onresume");
    }

    @Override
    public void unSubscribe() {
        Log.d("mvptest","unSubscribe onpause");
    }

    private int count =0;
    private int flag =0;
    @Override
    public void testPresenterFirst() {
        Log.d("mvptest","testPresenterFirst");
        if(count %2==0){
            mView.testViewOnew();
        }
        count ++;
    }

    @Override
    public void testPresenterTwo() {
        Log.d("mvptest","testPresenterTwo");
        if(flag %2==0){
            mView.testViewTwo();
        }
        flag ++;
    }
}
