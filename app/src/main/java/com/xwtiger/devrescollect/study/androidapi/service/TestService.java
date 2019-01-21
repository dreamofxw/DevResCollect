package com.xwtiger.devrescollect.study.androidapi.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 *
 *这里关于生命周期，调用方法不同，周期也不同。
 * 调用startService周期：onCreate()-onStartCommand()-onDestroy()
 * 调用bindService周期：onCreate()-onBind()-Unbind()-onDestroy()
 *
 *startService后，再绑定服务。当解绑服务时候，Service并没有回调onDestroy()函数，
 而绑定服务再解绑时，则会调用onDestroy()函数。

 这是因为第一种情况，Service是由startService创建的，不是bindService。而第二种情况，则是bindService创建的Service。从这里发现，当bindService时，如果服务已经创建，那么解绑只是解除访问者与Service的绑定，而Service并不会终止
 *
 *总结：服务由谁开启，就又谁关闭，中间的调用不影响整体的生命周期
 * 比如startService 开启的服务，中间不关调用了startService，bindService,最后结束服务还是stopService
 *
 * eg.通过startService 开启服务，之后在调用startService就只调用onStartCommand 不调用onCreate()
 *    2,通过bindService 开启服务，之后在调用bindService 就不调用任何方法，如果换了context来绑定
 *    则只调用se
 *
 *
 *
 *
 *
 * author:xw
 * Date:2019-01-02 15:40
 * Description:
 * //<editor-fold desc="">
 * //</editor-fold>
 */
public class TestService extends Service {

    public static String tag = "testService";

    private LocalBinder bind ;

    public static class LocalBinder extends Binder {

        private WeakReference<TestService> weakReference;

        public LocalBinder(TestService service){
            weakReference = new WeakReference<>(service);
        }

        public TestService getService() {
            return weakReference.get();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(tag,"onBind");
        bind = new LocalBinder(this);
        return bind;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(tag,"onStartCommand");
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(tag,"onCreate");
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(tag,"onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(tag,"onDestroy");
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(tag, "onRebind: onrebind");
    }
}
