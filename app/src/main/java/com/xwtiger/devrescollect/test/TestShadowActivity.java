package com.xwtiger.devrescollect.test;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.dialog.BottomerDialog;
import com.xwtiger.devrescollect.study.javaapi.FileStudy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TestShadowActivity extends BaseActivity{


    private RecyclerView recyclerview;

    private TextView tv_testshadow;

    private ImageView iv_note_rightimg;
    private ImageView iv_note_rightimg1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testshadow);
        init();



         String BasePath = Environment.getExternalStorageDirectory().getPath();

         Log.d("testpath", "onCreate: getExternalStorageDirectory="+BasePath);

         File cacheDir = this.getCacheDir();

         Log.d("testpath", "onCreate: cacheDir="+cacheDir.getPath());


         File externalCacheDir = this.getExternalCacheDir();

         Log.d("testpath", "onCreate: externalCacheDir="+externalCacheDir.getPath());

         //this.getExternalFilesDir();


        //tv_testshadow.getPaint().setFlags();





        //add datat


//        File externalCacheDirnew = this.getExternalCacheDir();
//
//
//        FileStudy.makeNewFile(externalCacheDirnew.getPath()+"/test/","123.txt");
//
//        FileStudy.makeNewFile(externalCacheDirnew.getPath()+"/test/","234.txt");
//        FileStudy.makeNewFile(externalCacheDirnew.getPath()+"/test/","345.txt");
//        FileStudy.makeNewFile(externalCacheDirnew.getPath(),"345.txt");







//        Map<String,Integer> map = new HashMap<>();
//        map.put("key1",10000000);
//        map.put("key2",90001831);
//        map.put("key3",9181);
//
//
//        String s = new Gson().toJson(map);
//
//        Log.d("testjsons", "onCreate: s="+s);
//
//
//        Map<String,String> mapresult = new Gson().fromJson(s,new TypeToken<Map<String,String>>(){}.getType());
//
//        Log.d("testjsons", "onCreate: --------------------");
//
//        Log.d("testjsons", "onCreate: --------------------");
//
//        Log.d("testjsons", "onCreate: mapresul="+mapresult);

        String url  ="http://img.youshu.cc/readwith/20190521/a7e04b5f863ccee110506e0edcaa7c1b.jpg@!hq350";
        String url2 = "http://img.youshu.cc/readwith/20190520/209acd64d0bf76842a28b14319e11800.jpeg@!hq350";
        loadRoundImage(this,getResources().getDimensionPixelSize(R.dimen.y20),url,0,iv_note_rightimg1);
    }

    @Override
    public void initView() {
        recyclerview = findViewById(R.id.recyclerview);
        tv_testshadow = findViewById(R.id.tv_testshadow);
        iv_note_rightimg = findViewById(R.id.iv_note_rightimg);
        iv_note_rightimg1 = findViewById(R.id.iv_note_rightimg1);


       // tv_testshadow.setBackgroundColor(Color.parseColor("#FF0000"));
        TextPaint paint = tv_testshadow.getPaint();
        paint.setColor(Color.parseColor("#ff0000"));
        paint.setStrokeWidth(20);

        tv_testshadow.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG |Paint.ANTI_ALIAS_FLAG);//文件中间加下划线，加上后面的属性字体更清晰一些

    }

    @Override
    public void initData() {
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
//        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerview.setLayoutManager(gridLayoutManager);
        recyclerview.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 10;
                outRect.right = 10;
                outRect.left = 10;
                //outRect.bottom;
            }
        });


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        gridLayoutManager.setOrientation(LinearLayout.VERTICAL);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position ==0){
                    return 3;
                }
                return 1;
            }
        });


        recyclerview.setLayoutManager(gridLayoutManager);


        recyclerview.setAdapter(new HeadImageAdapter());

        recyclerview.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public void setListener() {
        tv_testshadow.setOnClickListener(this);
    }

    private BottomerDialog dialog;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_testshadow:
                if(dialog ==null){
                    dialog = new BottomerDialog(this);
                }
                dialog.show();

                break;
        }
    }



    public static void loadRoundImage(final Context context, final int cornerRadius, String url, int resId, final ImageView imageView){
        try {
            Log.d("testglide", "setResource: start ");

            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .placeholder(resId)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT) //设置缓存
                    .into(new BitmapImageViewTarget(imageView){
                        @Override
                        protected void setResource(Bitmap resource) {
                            //super.setResource(resource);
                            Log.d("testglide", "setResource: ");
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCornerRadius(cornerRadius); //设置圆角弧度
                            imageView.setImageDrawable(circularBitmapDrawable);
                        }
                    });
        }catch (Exception e){
            Log.d("testglide", "setResource: "+e.getMessage());
            e.printStackTrace();
        }

    }



    class HeadImageAdapter extends RecyclerView.Adapter {

        private boolean isFirst = true;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = View.inflate(TestShadowActivity.this, R.layout.testitemsize, null);
            return new TestHolder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof TestHolder){
                final TestHolder holder1 = (TestHolder) holder;
//                if(position==0||position==3){
//                    holder1.tv_middle.setVisibility(View.VISIBLE);
//                }else{
//                    holder1.tv_middle.setVisibility(View.GONE);
//                }

                holder1.tv_first.setText("可以隐藏"+position);
                holder1.tv_middle.setText("中间展示"+position);
                holder1.tv_second.setText("一直显示"+position);

                if(position ==0&&isFirst){
                    isFirst = false;



                    holder1.rl_container.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            holder1.palyAnimation();

                        }
                    },10);
                }

            }
        }

        @Override
        public int getItemCount() {
            return 50;
        }
    }

    class TestHolder extends RecyclerView.ViewHolder{
        private TextView tv_first;
        private TextView tv_second;
        private TextView tv_middle;
        private RelativeLayout rl_container;
        private RelativeLayout animationview;
        private RelativeLayout rl_content;
        private View view_animation;
        public TestHolder(View itemView) {
            super(itemView);

            tv_first = itemView.findViewById(R.id.tv_first);
            tv_second = itemView.findViewById(R.id.tv_second);
            tv_middle = itemView.findViewById(R.id.tv_middle);
            rl_container = itemView.findViewById(R.id.rl_container);
            animationview = itemView.findViewById(R.id.animationview);
            view_animation = itemView.findViewById(R.id.view_animation);
            rl_content = itemView.findViewById(R.id.rl_content);
        }

        public void palyAnimation(){

            Log.d("testanimation", "palyAnimation: start");

            int duration1 = 500;
            int duration2 = 1500;
            int duration3 = 800;
            int duration4 = 1500;

            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(view_animation, View.ALPHA, 1, 1);
            objectAnimator1.setDuration(duration1);

            ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(view_animation, View.ALPHA, 0, 1);
            objectAnimator2.setDuration(duration2);

            objectAnimator2.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    view_animation.setBackgroundColor(Color.parseColor("#FFEBF8F3"));
                }

                @Override
                public void onAnimationEnd(Animator animation) {

                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });

            ObjectAnimator objectAnimato3 = ObjectAnimator.ofFloat(view_animation, View.ALPHA, 1,1);//View.ALPHA
            objectAnimato3.setDuration(duration3);


            ObjectAnimator objectAnimato4 = ObjectAnimator.ofFloat(view_animation, View.ALPHA, 1,0.8f,1, 0);//View.ALPHA
            objectAnimato4.setDuration(duration4);

            objectAnimato4.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    view_animation.setBackgroundColor(Color.parseColor("#ffffff"));

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });

            animatorSet.playSequentially(objectAnimator1,objectAnimator2,objectAnimato3,objectAnimato4);
            animatorSet.start();

        }



//        public int fade;
//
//        public int getFade() {
//            return fade;
//        }
//
//        public void setFade(int fade) {
//            this.fade = fade;
//        }
    }
}
