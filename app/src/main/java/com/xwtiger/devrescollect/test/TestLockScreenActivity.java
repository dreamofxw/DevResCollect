package com.xwtiger.devrescollect.test;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;

/**
 * author:xw
 * Date:2018-07-17 9:13
 * Description:
 */
public class TestLockScreenActivity extends BaseActivity {


    private ScreenBroadcastReceiver mScreenReceiver;

    private ScreenStateListener mScreenStateListener;

    public static String TAG = "TestLockScreen123";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_testlockscreen);
        init();
        registerListener();
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
    public void onClick(View view) {

    }

    public void makeKeyguardLock(Context context, int i) {
        KeyguardManager mManager = (KeyguardManager)context.getSystemService(context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock mKeyguardLock = mManager.newKeyguardLock("Lock");
        switch(i) {
            case 1:
                Log.d(TAG, "disableKeyguard: ");
                mKeyguardLock.disableKeyguard();
                break;
            case 2:
                Log.d(TAG, "reenableKeyguard: ");
                mKeyguardLock.reenableKeyguard();
                break;
            default: break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mScreenReceiver);
    }

    /**
     * Status screen broadcast receivers
     */
    private class ScreenBroadcastReceiver extends BroadcastReceiver {
        private String action = null;

        @Override
        public void onReceive(Context context, Intent intent) {
            action = intent.getAction();
            if (Intent.ACTION_SCREEN_ON.equals(action)) { // Open screen
                Log.d(TAG, "onReceive: ACTION_SCREEN_ON");
            } else if (Intent.ACTION_SCREEN_OFF.equals(action)) { // Lock screen
                Log.d(TAG, "onReceive: ACTION_SCREEN_OFF");
            } else if (Intent.ACTION_USER_PRESENT.equals(action)) { // Unlock 解锁
                Log.d(TAG, "onReceive: ACTION_USER_PRESENT");
            }
        }
    }

    public void registerListener() {
        mScreenReceiver = new ScreenBroadcastReceiver();
        mScreenStateListener = new ScreenStateListener() {
            @Override
            public void onScreenOn() {
                Log.d(TAG, "onScreenOn: ScreenStateListener");
            }

            @Override
            public void onScreenOff() {
                Log.d(TAG, "onScreenOff: ScreenStateListener");
            }

            @Override
            public void onUserPresent() {
                Log.d(TAG, "onUserPresent: ScreenStateListener");
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(mScreenReceiver, filter);
        getScreenState();
    }

    public interface ScreenStateListener {// 返回给调用者屏幕状态信息
        public void onScreenOn();

        public void onScreenOff();

        //解锁
        public void onUserPresent();
    }

    /**
     * 获取screen状态
     */
    private void getScreenState() {
        PowerManager manager = (PowerManager) mContext
                .getSystemService(Context.POWER_SERVICE);
        if (manager.isInteractive()) {
            if (mScreenStateListener != null) {
                mScreenStateListener.onScreenOn();
            }
        } else {
            if (mScreenStateListener != null) {
                mScreenStateListener.onScreenOff();
            }
        }
    }



}
