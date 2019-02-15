package com.xwtiger.devrescollect.act;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;

public class ActivityFromWeb extends BaseActivity{

    private TextView tv_showmsg;
    private StringBuilder sb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(sb ==null){
            sb = new StringBuilder();
        }
        Intent intent = getIntent();
        if(intent !=null){
            String scheme = intent.getScheme();//获得scheme名称

            addString("getScheme",scheme);

            String dataString = intent.getDataString();//获得uri全部路径

            addString("getDataString ",dataString);
            Uri data = intent.getData();
            String user;
            if(data !=null){
                user = data.getQueryParameter("user");
                addString("user ",user);
            }
        }

        setContentView(R.layout.act_fromweb);
        init();

    }

    @Override
    public void initView() {
        tv_showmsg = findViewById(R.id.tv_showmsg);

    }

    @Override
    public void initData() {
        tv_showmsg.setText(sb.toString());
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {

    }


    public void addString(String key,String value){
        if(!TextUtils.isEmpty(value)){
            sb.append(key+" ="+value);
            sb.append("\n");
        }

    }

    public void initMW(){
//        MWConfiguration config = new MWConfiguration(this);
//        config.setLogEnable(true);//打开魔窗Log信息
//        MagicWindowSDK.initSDK(config);
    }
}
