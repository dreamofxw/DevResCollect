package com.xwtiger.devrescollect.study.androidapi.notify;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;

import com.xwtiger.devrescollect.R;


public class NotifyManager {



    public void createNotify(){













    }

    public void sendSimpleNotification(Context context, NotificationManager nm) {
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.O){
            //创建通知
            Notification.Builder nb = new Notification.Builder(context,"add")
                    //设置通知左侧的小图标
                    .setSmallIcon(R.drawable.ic_launcher)
                    //设置通知标题
                    .setContentTitle("Simple notification")
                    //设置通知内容
                    .setContentText("Demo for simple notification !")
                    //设置点击通知后自动删除通知
                    .setAutoCancel(true)
                    //设置显示通知时间
                    .setShowWhen(true)
                    //设置通知右侧的大图标
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher));

            //发送通知
            //nm.notify("",nb.build());
        }

    }



}
