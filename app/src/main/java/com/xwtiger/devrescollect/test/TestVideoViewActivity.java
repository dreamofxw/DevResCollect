package com.xwtiger.devrescollect.test;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.test.view.FullScreenVideoView;

/**
 * Created by Busap-112 on 2017/12/1.
 */

public class TestVideoViewActivity extends AppCompatActivity {

    private FullScreenVideoView mVideoView;
    private boolean isOnlyOne = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_testvideoview);
        mVideoView = (FullScreenVideoView) findViewById(R.id.vidioview);
        startPlay();
    }


    private void startPlay(){
        //Uri localUri = Uri.parse("android.resource://" + getPackageName() + "/" + );
//        String videoPath = "file:///android_asset/defultvideo";
//        mVideoView.setVideoPath(videoPath);
        Uri localUri = Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.login_video_3);
        mVideoView.setVideoURI(localUri);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d("mVideoViewtest","onPrepared start");
            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d("mVideoViewtest","onCompletion end");
                Log.d("mVideoViewtest","onCompletion ispaly="+mVideoView.isPlaying());
                if(isOnlyOne){
                    isOnlyOne = false;
                    startPlay();
                }else{

                }
            }
        });

        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.d("mVideoViewtest","onError");
                if(mVideoView != null){
                    mVideoView.stopPlayback();
                }
                return false;
            }
        });
        mVideoView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                Log.d("mVideoViewtest","onKey v ="+v.toString());
                Log.d("mVideoViewtest","onKey keyCode ="+keyCode);
                Log.d("mVideoViewtest","onKey event.getKeyCode ="+event.getKeyCode());
                if(keyCode ==KeyEvent.KEYCODE_BACK){

                    return true;
                }

                return false;
            }
        });

        mVideoView.start();
        mVideoView.requestFocus();

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        startPlay();
    }
}
