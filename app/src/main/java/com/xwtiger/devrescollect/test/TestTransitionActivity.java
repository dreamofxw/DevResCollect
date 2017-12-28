package com.xwtiger.devrescollect.test;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.utils.CopressImageUtil;

import static android.R.attr.animation;

/**
 * Created by Busap-112 on 2017/12/28.
 */

public class TestTransitionActivity extends BaseActivity {

    private ImageView iv_top;
    private ImageView iv_middle;
    private ImageView iv_bottomer;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testtransition);
    }

    @Override
    public void initView() {
        iv_top = (ImageView) findViewById(R.id.iv_top);
        iv_middle = (ImageView) findViewById(R.id.iv_middle);
        iv_bottomer = (ImageView) findViewById(R.id.iv_bottomer);
    }

    @Override
    public void initData() {
        mBitmap = CopressImageUtil.createBitmap(mContext, 0.2f, 0.2f);
        iv_top.setImageBitmap(mBitmap);
        iv_middle.setImageBitmap(mBitmap);
        iv_bottomer.setImageBitmap(mBitmap);

    }

    @Override
    public void setListener() {
        iv_top.setOnClickListener(this);
        iv_middle.setOnClickListener(this);
        iv_bottomer.setOnClickListener(this);
    }

    boolean istop = true;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_top:
                Intent intent = new Intent(this,TestTransitonDitailActivity.class);
                ActivityOptionsCompat activityoptions =  ActivityOptionsCompat.makeSceneTransitionAnimation(this,iv_top,"iv_top");
                ActivityCompat.startActivity(mContext,intent,activityoptions.toBundle());
                //overridePendingTransition(R.anim.enterac,R.anim.exitac);
//                if(istop){
//                    istop = false;
//                    Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.enterac);
//                    animation.setFillAfter(true);
//                    iv_top.startAnimation(animation);
//                }else{
//                    istop = true;
//                    Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.exitac);
//                    iv_top.startAnimation(animation);
//                }
               /* if(istop){
                    istop = false;
                    AnimatorSet animatorset = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,R.animator.enteractivity);
                    animatorset.setTarget(iv_top);
                    animatorset.start();
                }else{
                    istop = true;
                    AnimatorSet animatorset_exit = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,R.animator.exitactivity);
                    animatorset_exit.setTarget(iv_top);
                    animatorset_exit.start();
                }*/
                break;
            case R.id.iv_middle:
                Intent intent_mid = new Intent(this,TestTransitonDitailActivity.class);
                startActivity(intent_mid);
                break;
            case R.id.iv_bottomer:
                Intent intent_bott = new Intent(this,TestTransitonDitailActivity.class);
                startActivity(intent_bott);
                break;
        }
    }
}
