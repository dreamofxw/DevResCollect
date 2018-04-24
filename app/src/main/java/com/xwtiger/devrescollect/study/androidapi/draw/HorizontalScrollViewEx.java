package com.xwtiger.devrescollect.study.androidapi.draw;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by xwadmin on 2018/4/22.
 */

public class HorizontalScrollViewEx extends ViewGroup {

    private static final String TAG = "HorizontalScrollViewEx";

    private Scroller mScroller;

    private VelocityTracker mVelocityTracker;

    private int mLastXIntercepter;

    private int mLastYIntercepter;

    private int mLastX;

    private int mLastY;

    private int mChildWidth;

    private int mChildIndex =0;

    private int mChildrenSize;


    public HorizontalScrollViewEx(Context context) {
        super(context);
        init();
    }

    public HorizontalScrollViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HorizontalScrollViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mScroller = new Scroller(getContext());
        mVelocityTracker = VelocityTracker.obtain();
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("testIntercept","onInterceptTouchEvent ="+ev.getAction());
        boolean isIntercepter = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN://0
                isIntercepter = false;
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                    isIntercepter = true;
                }
                break;
            case MotionEvent.ACTION_MOVE://2
                int deltaX = x -mLastXIntercepter;
                int deltaY = y - mLastYIntercepter;
                if(Math.abs(deltaX)> Math.abs(deltaY)){
                    isIntercepter = true;
                }else{
                    isIntercepter = false;
                }
                break;
            case MotionEvent.ACTION_UP://1
                isIntercepter = false;
                break;
            default :
                break;
        }
        mLastXIntercepter = x;
        mLastYIntercepter = y;
        //这里要计算上次滑动的位置
        mLastX = x;
        mLastY = y;
        Log.d("testIntercept","onInterceptTouchEvent ="+ev.getAction()+",mlastx ="+mLastX);
        return isIntercepter;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();
        Log.d("testIntercept","onTouchEvent ="+event.getAction()+",x="+x);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x-mLastX;
                int deltaY = y -mLastY;
                Log.d("testOnTouchEvent","deltaX ="+deltaX);
                scrollBy(-deltaX,0);
                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                int scrollToChildIndex = scrollX/mChildWidth;
                mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = mVelocityTracker.getXVelocity();
                if(Math.abs(xVelocity) >=50){
                    mChildIndex = xVelocity >0?((mChildIndex -1)<0?0:(mChildIndex -1)):mChildIndex+1;
                }else{
                    mChildIndex = (scrollX+mChildWidth/2)/mChildWidth;
                }
                mChildIndex = Math.max(0,Math.min(mChildIndex,mChildrenSize-1));
                int dx = mChildIndex *mChildWidth-scrollX;
                smoothScrollBy(dx,0);
                mVelocityTracker.clear();
                Log.d("mChildIndex","mChildIndex ="+mChildIndex);
                Log.d("mChildIndex","before scrollX ="+scrollX);
                Log.d("mChildIndex","after scrollX ="+getScrollX());
                Log.d("mChildIndex","mChildWidth ="+mChildWidth);
                Log.d("mChildIndex","xVelocity ="+xVelocity);
                Log.d("mChildIndex","--------------------------- =");
                break;

        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }else{
            Log.d("mChildIndex","computeScroll getscrollx ="+getScrollX());
        }
    }

    public void smoothScrollBy(int dx,int dy){
        mScroller.startScroll(getScrollX(),0,dx,0,500);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int measureWidth = 0;
        int measureHeight = 0;
        final int childCount = getChildCount();

        //测量子view
        measureChildren(widthMeasureSpec,heightMeasureSpec);

        final View childview = getChildAt(0);

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecsize = MeasureSpec.getSize(heightMeasureSpec);

        if(childCount ==0){
            setMeasuredDimension(0,0);
        }else if(widthSpecMode ==MeasureSpec.AT_MOST && heightSpecMode ==MeasureSpec.AT_MOST){
            measureWidth = childview.getMeasuredWidth()*childCount;
            measureHeight= childview.getMeasuredHeight();
            setMeasuredDimension(measureWidth,measureHeight);
        }else if(heightSpecMode == MeasureSpec.AT_MOST){
            measureHeight = childview.getMeasuredHeight();
            setMeasuredDimension(widthSpecSize,measureHeight);
        }else if(widthSpecMode == MeasureSpec.AT_MOST){
            measureWidth = childview.getMeasuredWidth() *childCount;
            setMeasuredDimension(measureWidth,heightSpecsize);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childleft =0;
        int childCount = getChildCount();
        mChildrenSize = childCount;
        for(int i =0;i < childCount;i++){
            View childAt = getChildAt(i);
            if(childAt.getVisibility() !=View.GONE){
                int measuredWidth = childAt.getMeasuredWidth();
                mChildWidth = measuredWidth;
                childAt.layout(childleft,0,childleft+measuredWidth,childAt.getMeasuredHeight());
                childleft += measuredWidth;
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        mVelocityTracker.recycle();
        super.onDetachedFromWindow();
    }

    public int getmChildIndex() {
        return mChildIndex;
    }
}
