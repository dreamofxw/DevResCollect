package com.xwtiger.devrescollect.test;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xwtiger.devrescollect.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by xwadmin on 2018/3/14.
 */

public class TestObservableActivity extends BaseActivity {

    private static String TAG = "TestObservableActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("test observable");
        setContentView(tv);
       /* new Thread(){
            @Override
            public void run() {
                Log.d(TAG,"new Thread "+getThreadName());
                testObervable1();
            }
        }.start();*/
        testObservable12();
    }

    public void testObservable12(){

        /*List<? super Number> list = new ArrayList();
        list.add(12);
        list.add(153.06);


        List<? extends Number> list2 = new ArrayList();
        Number number = list2.get(0);

        List<?> list3 = new ArrayList();
        Object o = list3.get(0);*/


        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        }).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s+"abc";
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d("testObservable12","onCompleted ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("testObservable12","onError");
            }

            @Override
            public void onNext(String s) {
                Log.d("testObservable12","onNext s ="+s);
            }
        });


    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {

    }

    private List<String> getList(){
        List<String> list = new ArrayList<>();
        for(int i =0;i<10;i++){
            list.add("String "+i);
        }
        return list;

    }
    private void testObervable(){

        Observable.from(getList()).
                observeOn(Schedulers.computation())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        Log.d(TAG,"map  s = "+s);
                        Log.d(TAG,"map = "+getThreadName());
                        return null;
                    }
                }).subscribeOn(Schedulers.io()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG,"call s = "+ s);
                Log.d(TAG,"call = "+getThreadName());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d(TAG,"Throwable = "+getThreadName());
            }
        });

    }

    private void testObervable1(){
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.d(TAG,"create = "+getThreadName());
                subscriber.onNext("haha");
                subscriber.onCompleted();
            }
        }).observeOn(Schedulers.io()).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                Log.d(TAG,"map s = "+s);
                Log.d(TAG,"map = "+getThreadName());

                return "chang";
            }
        }).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                Log.d(TAG,"doOnSubscribe call = "+getThreadName());
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG,"call s = "+s);
                Log.d(TAG,"call = "+getThreadName());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d(TAG,"throwable = "+getThreadName());
            }
        });
    }

    public String getThreadName(){
        return Thread.currentThread().getName();
    }
}
