package com.xwtiger.devrescollect.act.deeplink;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;

public class TestDeepLink extends BaseActivity{


    private WebView webview;

    private String testPath = "file:///android_asset/www/123.html";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_testdeeplink);
        init();
    }

    @Override
    public void initView() {
        webview = findViewById(R.id.webview);
    }

    @Override
    public void initData() {
        webview.loadUrl(testPath);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
