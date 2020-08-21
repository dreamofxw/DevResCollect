package com.xwtiger.devrescollect.study.androidapi.img;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.study.androidapi.img.view.MovingImageView;

/**
 *
 * Created by Busap-112 on 2017/11/14.
 *
 */

public class ImgActivity extends Activity implements View.OnClickListener{

    private Button btn_translate;
    private Button btn_scale;
    private MovingImageView iv_dest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);

        btn_translate = (Button) findViewById(R.id.btn_translate);
        btn_scale = (Button) findViewById(R.id.btn_scale);
        iv_dest = (MovingImageView) findViewById(R.id.iv_zi);

        btn_translate.setOnClickListener(this);
        btn_scale.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_translate:
                //MatrixStudy.bitmapScale(iv_dest,2f,2f);
                //MatrixStudy.testMatix(iv_dest,2f,2f);
                iv_dest.start();
                break;
            case R.id.btn_scale:

                break;
        }

    }
}
