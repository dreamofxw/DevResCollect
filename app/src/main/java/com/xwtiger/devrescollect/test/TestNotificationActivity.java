package com.xwtiger.devrescollect.test;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xwtiger.devrescollect.MyException;
import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class TestNotificationActivity extends BaseActivity{


    public final static int requestcode_notify = 10000;

    private TextView tv_opennotifypage;
    private TextView tv_openmarketpage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_testnotification);

        init();
    }

    @Override
    public void initView() {
        tv_opennotifypage = findViewById(R.id.tv_opennotifypage);
        tv_openmarketpage = findViewById(R.id.tv_openmarketpage);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {
        tv_opennotifypage.setOnClickListener(this);
        tv_openmarketpage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_opennotifypage:
                //jumpToNotifyPage();
                //jumpToNotifyPage1();
                isHasNotifyEnable();
                jumpToNotifyPage2();
                break;
            case R.id.tv_openmarketpage:
                //goToMarket(this,this.getPackageName());
                //getInstallAppMarkets(this);
                test();
                getFilterInstallMarkets(this,getInstallAppMarkets(this));
                break;
        }
    }


    public static void goToMarket(Context context, String packageName) {
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {

            MyException.printStr(e);
        }
    }

    public void isHasNotifyEnable(){
        NotificationManagerCompat notification = NotificationManagerCompat.from(this);
        boolean isEnabled = notification.areNotificationsEnabled();
        Log.d(TAG, "isHasNotifyEnable: isEnabled ="+isEnabled);
    }


    public void jumpToNotifyPage2(){
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//26
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", TestNotificationActivity.this.getPackageName());
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {  //5.0
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", TestNotificationActivity.this.getPackageName());
            intent.putExtra("app_uid", TestNotificationActivity.this.getApplicationInfo().uid);
            //startActivity(intent);
            startActivityForResult(intent,requestcode_notify);
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {  //4.4
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setData(Uri.parse("package:" + TestNotificationActivity.this.getPackageName()));
        } else if (Build.VERSION.SDK_INT >= 15) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", TestNotificationActivity.this.getPackageName(), null));
        }
        //startActivity(intent);
        startActivityForResult(intent,requestcode_notify);
    }


    public void jumpToNotifyPage1(){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("请在“通知”中打开通知权限")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intent = new Intent();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//26
                            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                            intent.putExtra("android.provider.extra.APP_PACKAGE", TestNotificationActivity.this.getPackageName());
                        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {  //5.0
                            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                            intent.putExtra("app_package", TestNotificationActivity.this.getPackageName());
                            intent.putExtra("app_uid", TestNotificationActivity.this.getApplicationInfo().uid);
                            startActivity(intent);
                        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {  //4.4
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.addCategory(Intent.CATEGORY_DEFAULT);
                            intent.setData(Uri.parse("package:" + TestNotificationActivity.this.getPackageName()));
                        } else if (Build.VERSION.SDK_INT >= 15) {
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                            intent.setData(Uri.fromParts("package", TestNotificationActivity.this.getPackageName(), null));
                        }
                        startActivity(intent);

                    }
                })
                .create();
        alertDialog.show();
        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        isHasNotifyEnable();
        Log.d(TAG, "onActivityResult: requestCode ="+requestCode);
        Log.d(TAG, "onActivityResult: data ="+(data ==null));
        if(data !=null){
            Log.d(TAG, "onActivityResult: data ="+(data.getData().toString()));
        }
        switch (requestCode){
            case requestcode_notify:
                Log.d(TAG, "onActivityResult: resultCode ="+resultCode);

                break;
        }
    }


    /**
     * 获取已安装应用商店的包名列表
     * 获取有在AndroidManifest 里面注册<category android:name="android.intent.category.APP_MARKET" />的app
     * @param context
     * @return
     */
    public ArrayList<String> getInstallAppMarkets(Context context) {
        //默认的应用市场列表，有些应用市场没有设置APP_MARKET通过隐式搜索不到
        ArrayList<String> pkgList = new ArrayList<>();
        pkgList.add("com.xiaomi.market");
        pkgList.add("com.qihoo.appstore");
        pkgList.add("com.wandoujia.phoenix2");
        pkgList.add("com.tencent.android.qqdownloader");
        pkgList.add("com.huawei.appmarket");
        pkgList.add("com.taptap");
        ArrayList<String> pkgs = new ArrayList<String>();
        if (context == null)
            return pkgs;
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.APP_MARKET");
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
        Log.d(TAG, "getInstallAppMarkets: infos="+infos.size());
        if (infos == null || infos.size() == 0)
            return pkgs;
        int size = infos.size();
        for (int i = 0; i < size; i++) {
            String pkgName = "";
            try {
                ActivityInfo activityInfo = infos.get(i).activityInfo;
                pkgName = activityInfo.packageName;
            } catch (Exception e) {
                MyException.printStr(e);
            }
            if (!TextUtils.isEmpty(pkgName))
                pkgs.add(pkgName);
        }
        //取两个list并集,去除重复
        Log.d(TAG, "getInstallAppMarkets: pkgs ="+pkgs);
        pkgList.removeAll(pkgs);
        Log.d(TAG, "getInstallAppMarkets: pkgList ="+pkgList);
        pkgs.addAll(pkgList);
        Log.d(TAG, "getInstallAppMarkets: after pkgs ="+pkgs);

        return pkgs;
    }



    /**
     * 过滤出已经安装的包名集合
     * @param context
     * @param pkgs 待过滤包名集合
     * @return 已安装的包名集合
     */
    public ArrayList<String> getFilterInstallMarkets(Context context,ArrayList<String> pkgs) {
        //appInfos.clear();
        Log.d(TAG, "getFilterInstallMarkets: ------------------------分割线-------------------------");
        ArrayList<String> appList = new ArrayList<String>();
        if (context == null || pkgs == null || pkgs.size() == 0)
            return appList;
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> installedPkgs = pm.getInstalledPackages(0);
        Log.d(TAG, "getFilterInstallMarkets: installedPkgs="+installedPkgs);
        Log.d(TAG, "getFilterInstallMarkets: installedPkgs.size()="+installedPkgs.size());
        int li = installedPkgs.size();
        int lj = pkgs.size();
        for (int j = 0; j < lj; j++) {
            for (int i = 0; i < li; i++) {
                String installPkg = "";
                String checkPkg = pkgs.get(j);
                PackageInfo packageInfo = installedPkgs.get(i);
                try {
                    installPkg = packageInfo.packageName;

                } catch (Exception e) {
                    MyException.printStr(e);
                }
                if (TextUtils.isEmpty(installPkg))
                    continue;
                if (installPkg.equals(checkPkg)) {
                    // 如果非系统应用，则添加至appList,这个会过滤掉系统的应用商店，如果不需要过滤就不用这个判断
                    //if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                        //将应用相关信息缓存起来，用于自定义弹出应用列表信息相关用
//                        AppInfo appInfo = new AppInfo();
//                        appInfo.setAppName(packageInfo.applicationInfo.loadLabel(getPackageManager()).toString());
//                        appInfo.setAppIcon(packageInfo.applicationInfo.loadIcon(getPackageManager()));
//                        appInfo.setPackageName(packageInfo.packageName);
//                        appInfo.setVersionCode(packageInfo.versionCode);
//                        appInfo.setVersionName(packageInfo.versionName);
//                        appInfos.add(appInfo);
                        appList.add(installPkg);
                    //}
                    break;
                }

            }
        }

        Log.d(TAG, "getFilterInstallMarkets: last  appList ="+appList);
        return appList;
    }


    public void test(){
        /**
         *  <intent-filter>
         <action android:name="com.huawei.appmarket.appmarket.intent.action.appmanager"/>
         <category android:name="android.intent.category.DEFAULT"/>
         </intent-filter>
         */
//        Intent intent =new Intent();
//        intent.setAction("com.huawei.appmarket.appmarket.intent.action.appmanager");
//        intent.addCategory("android.intent.category.DEFAULT");
//        startActivity(intent);

        /**
         * <intent-filter>
         <action android:name="android.intent.action.VIEW"/>
         <category android:name="android.intent.category.DEFAULT"/>
         <category android:name="android.intent.category.BROWSABLE"/>
         <data android:host="details" android:scheme="market"/>
         </intent-filter>
         */


        Intent intent=new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        //intent.addCategory("android.intent.category.BROWSABLE");
        intent.setData(Uri.parse("market://details?id="+getPackageName()));
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }


}
