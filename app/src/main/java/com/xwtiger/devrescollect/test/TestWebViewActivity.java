package com.xwtiger.devrescollect.test;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.broadcast.ScreenBroadcastReceiver;

public class TestWebViewActivity extends BaseActivity {


    private WebView webview;

    private TextView tv_click;

    String url ="http://m.laidan.com/weixin/app/noteDetail?note_id=90002499";
    String url1 ="http://m.laidan.com/weixin/app/noteDetail?note_id=11391305";
    String url2 ="http://m.laidan.com/weixin/app/noteDetail?note_id=11381486";
    //	https://zaiadev.laidan.com/p/vue_pages/community?id=75514
    String url3 ="https://zaiadev.laidan.com/p/vue_pages/community?id=75514";



    String url4 ="http://m.laidan.com/weixin/app/noteDetail?note_id=11408886";
    String url5 ="http://m.laidan.com/pages/community/index.html?from_ys_source=H5&id=75514";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_testwebview);
        init();
    }

    private boolean isLoad = false;

    @Override
    public void initView() {
        webview = findViewById(R.id.webview);
        tv_click = findViewById(R.id.tv_click);
        //note_info_url	String	http://m.laidan.com/weixin/app/noteDetail?note_id=11391305

        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.d("testwebview1", "onPageStarted: getMeasuredHeight="+view.getMeasuredHeight());

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d("testwebview1", "onPageFinished:getMeasuredHeight "+view.getMeasuredHeight());
            }
        });

        webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //Log.d("testwebview1", "onProgressChanged: newProgress="+newProgress);
                if(newProgress ==100&&!isLoad){
                    isLoad = true;
                    int height = view.getHeight();
                    int contentHeight = view.getContentHeight();

                    CharSequence contentDescription = view.getContentDescription();
                    Log.d("testwebview1", "onProgressChanged: height="+height);
                    Log.d("testwebview1", "onProgressChanged: contentHeight="+contentHeight);
                    Log.d("testwebview1", "onProgressChanged: contentDescription="+(contentDescription !=null?contentDescription.length():"null"));
                    ViewGroup.LayoutParams layoutParams = webview.getLayoutParams();

                    layoutParams.height =height/3;

                    webview.setLayoutParams(layoutParams);
                }else if(newProgress <30){
                    isLoad = false;
                }
            }


        });

        webview.loadUrl(url5);


        tv_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.loadUrl(url);
            }
        });


    }

    ScreenBroadcastReceiver screenBroadcastReceiver;
    @Override
    public void initData() {
        screenBroadcastReceiver = new ScreenBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        getApplicationContext().registerReceiver(screenBroadcastReceiver, filter);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getApplicationContext().unregisterReceiver(screenBroadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Log.d("testlockscreen", "onResume: 可见");
        //screenBroadcastReceiver.state =0;
    }
}
