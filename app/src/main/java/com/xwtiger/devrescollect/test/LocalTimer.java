package com.xwtiger.devrescollect.test;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

public class LocalTimer {

    private HandlerThread handlerThread;

    private Handler localHandler;

    private boolean isInvertedOrder = false;//是否倒序

    private int count = 0;

    private int threshold = 30;

    private volatile TimerState timerState;

    private IListerTimer listerTimer;

    public LocalTimer(String threadname){
        handlerThread = new HandlerThread(threadname);
        handlerThread.start();
        localHandler = new Handler(handlerThread.getLooper());
        timerState = TimerState.Init;

    }


    public void destroy(){
        Log.d("testlocaltimer", "destroy: destroy");
        timerState = TimerState.End;
        localHandler.removeCallbacks(timerRunalbe);
        if(handlerThread !=null){
            handlerThread.quitSafely();
        }
    }


    public void start(){

        localHandler.removeCallbacks(timerRunalbe);
        timerState = TimerState.Timering;
        localHandler.postDelayed(timerRunalbe,1000);
        Log.d("testlocaltimer", "start: 调用start");


    }

    public void pause(){
        timerState = TimerState.Puaser;
        Log.d("testlocaltimer", "pause: pause");

    }

    public void end(){
        timerState = TimerState.End;
        Log.d("testlocaltimer", "end: end");
    }

    public void setInvertedOrder(boolean invertedOrder) {
        isInvertedOrder = invertedOrder;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    Runnable timerRunalbe = new Runnable() {
        @Override
        public void run() {
            if(timerState ==TimerState.Puaser){
                localHandler.removeCallbacks(timerRunalbe);
                Log.d("testlocaltimer", "run: 暂停");
                if(listerTimer !=null){
                    listerTimer.pause();
                }
                return;
            }else if(timerState ==TimerState.End){
                localHandler.removeCallbacks(timerRunalbe);
                Log.d("testlocaltimer", "run: 结束1");
                if(listerTimer !=null){
                    listerTimer.end();
                }
                return;
            }

            if(isInvertedOrder){
                if(count <=0){
                    //结束
                    timerState = TimerState.End;
                    if(listerTimer !=null){
                        listerTimer.end();
                    }
                    Log.d("testlocaltimer", "run: 结束2");
                    return;
                }
                count --;
            }else{
                if(count >threshold){
                    //结束
                    timerState = TimerState.End;
                    if(listerTimer !=null){
                        listerTimer.end();
                    }
                    Log.d("testlocaltimer", "run: 结束2");
                    return;
                }
                count ++;
            }
            timerState = TimerState.Timering;
            localHandler.postDelayed(timerRunalbe,1000);
            Log.d("testlocaltimer", "run: 计算中---count ="+count);
            if(listerTimer !=null){
                listerTimer.update(count);
            }
        }
    };

    public void setListerTimer(IListerTimer listerTimer) {
        this.listerTimer = listerTimer;
    }

    enum TimerState{
        Init,Timering,Puaser,End;
    }

}
