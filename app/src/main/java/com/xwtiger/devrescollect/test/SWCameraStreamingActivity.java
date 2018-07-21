package com.xwtiger.devrescollect.test;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.qiniu.pili.droid.streaming.AVCodecType;
import com.qiniu.pili.droid.streaming.CameraStreamingSetting;
import com.qiniu.pili.droid.streaming.MediaStreamingManager;
import com.qiniu.pili.droid.streaming.StreamingProfile;
import com.qiniu.pili.droid.streaming.StreamingState;
import com.qiniu.pili.droid.streaming.StreamingStateChangedListener;
import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.view.CameraPreviewFrameView;

import java.net.URISyntaxException;

/**
 * author:xw
 * Date:2018-07-11 20:35
 * Description:
 */
public class SWCameraStreamingActivity  extends BaseActivity  implements StreamingStateChangedListener{


    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {

    }


    private MediaStreamingManager mMediaStreamingManager;
    private StreamingProfile mProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_testlivestream);
        init();
        CameraPreviewFrameView cameraPreviewFrameView = (CameraPreviewFrameView) findViewById(R.id.cameraPreview_surfaceView);
        String publishURLFromServer = "rtmp://pili-publish.youshu.cc/youshu/live_2?key=3f78e4f4bb10ec53";

        initProfile(cameraPreviewFrameView,this);
//        try {
//            mProfile = new StreamingProfile();
//            mProfile.setQuicEnable(true);
//            try {
//                mProfile.setVideoQuality(StreamingProfile.VIDEO_QUALITY_HIGH1)
//                        .setAudioQuality(StreamingProfile.AUDIO_QUALITY_MEDIUM2)
//                        .setEncodingSizeLevel(StreamingProfile.VIDEO_ENCODING_HEIGHT_480)
//                        .setEncoderRCMode(StreamingProfile.EncoderRCModes.QUALITY_PRIORITY)
//                        .setPublishUrl(publishURLFromServer);  // You can invoke this before startStreaming, but not in initialization phase.
//            } catch (URISyntaxException e) {
//                e.printStackTrace();
//            }
//            CameraStreamingSetting setting = new CameraStreamingSetting();
//            setting.setCameraId(Camera.CameraInfo.CAMERA_FACING_FRONT)
//                    .setContinuousFocusModeEnabled(true)
//                    .setCameraPrvSizeLevel(CameraStreamingSetting.PREVIEW_SIZE_LEVEL.MEDIUM)
//                    .setCameraPrvSizeRatio(CameraStreamingSetting.PREVIEW_SIZE_RATIO.RATIO_16_9);
//            mMediaStreamingManager = new MediaStreamingManager(this, cameraPreviewFrameView, AVCodecType.SW_VIDEO_WITH_SW_AUDIO_CODEC);  // soft codec
//            mMediaStreamingManager.prepare(setting, mProfile);
//            mMediaStreamingManager.setStreamingStateListener(this);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }



    public void initProfile(CameraPreviewFrameView cameraPreviewFrameView,Context context){

        mProfile = new StreamingProfile();
        mProfile.setQuicEnable(true);
        try {
            //mProfile.setQuicEnable(true);
            mProfile.setEncodingOrientation(StreamingProfile.ENCODING_ORIENTATION.LAND);//不设置的情况下， Encoding Orientation 会依赖 Activity 的 Orientation
            mProfile.setVideoQuality(StreamingProfile.VIDEO_QUALITY_MEDIUM1);//设置视频质量 30fps,512kbps
            mProfile.setAudioQuality(StreamingProfile.AUDIO_QUALITY_MEDIUM1);//设置音频质量 32kbps 44100HZ
            mProfile.setEncodingSizeLevel(StreamingProfile.VIDEO_ENCODING_HEIGHT_480);//编码时候的size，即播放端不做处理时候看到视频的size
            //质量优先，实际的码率可能高于设置的码率
            mProfile.setEncoderRCMode(StreamingProfile.EncoderRCModes.QUALITY_PRIORITY);//
            mProfile.setBitrateAdjustMode(StreamingProfile.BitrateAdjustMode.Auto);
            //推流策略 200-2000
            if(mProfile.getBitrateAdjustMode() ==StreamingProfile.BitrateAdjustMode.Auto){
                mProfile.setVideoAdaptiveBitrateRange(200* 1024,  2000* 1024);
            }
            String publishURLFromServer = "rtmp://pili-publish.youshu.cc/youshu/live_2?key=3f78e4f4bb10ec53";
//            mProfile.setYuvFilterMode(StreamingProfile.YuvFilterMode.Linear);
            mProfile.setPublishUrl(publishURLFromServer);  // You can invoke this before startStreaming, but not in initialization phase.
            //mProfile.setDnsManager(getMyDnsManager());
            ////推流信息的反馈,来设定其反馈的频率 ;setStreamStatusCallback ，每隔3秒回调 StreamStatus 信息
//            mProfile.setStreamStatusConfig(new StreamingProfile.StreamStatusConfig(3));
//            mProfile.setSendingBufferProfile(new StreamingProfile.SendingBufferProfile(0.2f, 0.8f, 3.0f, 20 * 1000));
            //mProfile.setPictureStreamingResourceId(R.drawable.default_img);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        CameraStreamingSetting setting = new CameraStreamingSetting();
        setting.setCameraId(Camera.CameraInfo.CAMERA_FACING_FRONT);//设置摄像头
        setting.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);//设置对焦模式
        setting.setContinuousFocusModeEnabled(true);//默认开启，自动对焦
        //以下两个设置共同设置预览size
        setting.setCameraPrvSizeLevel(CameraStreamingSetting.PREVIEW_SIZE_LEVEL.MEDIUM);
        setting.setCameraPrvSizeRatio(CameraStreamingSetting.PREVIEW_SIZE_RATIO.RATIO_16_9);
//        setting.setResetTouchFocusDelayInMs(3000);//设置自动对焦模式时可以设置该参数
        setting.setBuiltInFaceBeautyEnabled(true);//开启内置美颜
//        setting.setVideoFilter(CameraStreamingSetting.VIDEO_FILTER_TYPE.VIDEO_FILTER_BEAUTY);
//        setting.setFaceBeautySetting(new CameraStreamingSetting.FaceBeautySetting(1.0f, 1.0f, 0.8f));//自定义美颜
        setting.setFrontCameraMirror(true);//设置前置摄像头镜像

        mMediaStreamingManager = new MediaStreamingManager(mContext, cameraPreviewFrameView, AVCodecType.SW_VIDEO_WITH_SW_AUDIO_CODEC);  // soft codec
        //mMediaStreamingManager.setPreviewMirror(true);//设置预览镜像
        mMediaStreamingManager.setEncodingMirror(true);//设置编码镜像
        mMediaStreamingManager.prepare(setting, mProfile);
        mMediaStreamingManager.setStreamingStateListener(this);
//        mMediaStreamingManager.setStreamingSessionListener(this);

//        Intent intent = new Intent(mContext, LiveCommentService.class);
//        mContext.bindService(intent,this, Service.BIND_AUTO_CREATE);

    }
    @Override
    protected void onResume() {
        super.onResume();
        mMediaStreamingManager.resume();
        //mProfile.setQuicEnable(true);
    }
    @Override
    protected void onPause() {
        super.onPause();
        // You must invoke pause here.
        mMediaStreamingManager.pause();
    }
    @Override
    public void onStateChanged(StreamingState streamingState, Object extra) {
        switch (streamingState) {
            case PREPARING:
                break;
            case READY:
                // start streaming when READY
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (mMediaStreamingManager != null) {
                            Log.d(TAG, "run: startStreaming");
                            mMediaStreamingManager.startStreaming();
                        }
                    }
                }).start();
                break;
            case CONNECTING:
                break;
            case STREAMING:
                // The av packet had been sent.
                break;
            case SHUTDOWN:
                // The streaming had been finished.
                break;
            case IOERROR:
                // Network connect error.
                break;
            case OPEN_CAMERA_FAIL:
                // Failed to open camera.
                break;
            case DISCONNECTED:
                // The socket is broken while streaming
                break;
        }
    }



}
