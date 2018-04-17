package com.xwtiger.devrescollect.refresh.pullview;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.widget.TextView;

public class AbViewUtil {

	/**
	 * 描述：根据屏幕大小缩放.
	 *
	 * @param context the context
	 * @param pxValue the px value
	 * @return the int
	 */
	public static int scaleValue(Context context, float value) {
		DisplayMetrics mDisplayMetrics = getDisplayMetrics(context);
		//为了兼容尺寸小密度大的情况
		int width = mDisplayMetrics.widthPixels;
		int height = mDisplayMetrics.heightPixels;
		//解决横屏比例问题
		if(width > height){
			width = mDisplayMetrics.heightPixels;
			height = mDisplayMetrics.widthPixels;
		}
		if(mDisplayMetrics.scaledDensity == GTConfig.UIDesignConstant.UI_DENSITY){
			//密度
			if(width > GTConfig.UIDesignConstant.UI_WIDTH){
				value = value*(1.3f - 1.0f/mDisplayMetrics.scaledDensity);
			}else if(width < GTConfig.UIDesignConstant.UI_WIDTH){
				value = value*(1.0f - 1.0f/mDisplayMetrics.scaledDensity);
			}
		}else{
			//密度小屏幕大:缩小比例
			float offset = GTConfig.UIDesignConstant.UI_DENSITY-mDisplayMetrics.scaledDensity;
			if(offset > 0.5f){
				value = value * 0.9f;
			}else{
				value = value * 0.95f;
			}
			
		}
		return scale(mDisplayMetrics.widthPixels,mDisplayMetrics.heightPixels, value);
	}
	
	/**
	 * 描述：根据屏幕大小缩放.
	 *
	 * @param displayWidth the display width
	 * @param displayHeight the display height
	 * @param pxValue the px value
	 * @return the int
	 */
	public static int scale(int displayWidth, int displayHeight, float pxValue) {
		if(pxValue == 0 ){
			return 0;
		}
		float scale = 1;
		try {
			int width = displayWidth;
			int height = displayHeight;
			//解决横屏比例问题
			if(width > height){
				width = displayHeight;
				height = displayWidth;
			}
			float scaleWidth = (float) width / GTConfig.UIDesignConstant.UI_WIDTH;
			float scaleHeight = (float) height / GTConfig.UIDesignConstant.UI_HEIGHT;
			scale = Math.min(scaleWidth, scaleHeight);
		} catch (Exception e) {
		}
		return Math.round(pxValue * scale + 0.5f);
	}
	
	 /**
     * 获取屏幕尺寸与密度.
     *
     * @param context the context
     * @return mDisplayMetrics
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        Resources mResources;
        if (context == null){
            mResources = Resources.getSystem();
            
        }else{
            mResources = context.getResources();
        }
        //DisplayMetrics{density=1.5, width=480, height=854, scaledDensity=1.5, xdpi=160.421, ydpi=159.497}
        //DisplayMetrics{density=2.0, width=720, height=1280, scaledDensity=2.0, xdpi=160.42105, ydpi=160.15764}
        DisplayMetrics mDisplayMetrics = mResources.getDisplayMetrics();
        return mDisplayMetrics;
    }
    
    /**
     * 缩放文字大小,这样设置的好处是文字的大小不和密度有关，
     * 能够使文字大小在不同的屏幕上显示比例正确
     * @param textView button
     * @param sizePixels px值
     * @return
     */
    public static void setTextSize(TextView textView,float sizePixels) {
    	float scaledSize = scaleTextValue(textView.getContext(),sizePixels);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,scaledSize);
    }
    
    /**
	 * 描述：根据屏幕大小缩放文本.
	 *
	 * @param context the context
	 * @param pxValue the px value
	 * @return the int
	 */
	public static int scaleTextValue(Context context, float value) {
		return scaleValue(context, value);
	}
	
	/**
	 * 设置PX padding.
	 *
	 * @param view the view
	 * @param left the left padding in pixels
     * @param top the top padding in pixels
     * @param right the right padding in pixels
     * @param bottom the bottom padding in pixels
	 */
	public static void setPadding(View view, int left,
			int top, int right, int bottom) {
		int scaledLeft = scaleValue(view.getContext(), left);
		int scaledTop = scaleValue(view.getContext(), top);
		int scaledRight = scaleValue(view.getContext(), right);
		int scaledBottom = scaleValue(view.getContext(), bottom);
		view.setPadding(scaledLeft, scaledTop, scaledRight, scaledBottom);
	}
	
	/**
	 * 测量这个view
	 * 最后通过getMeasuredWidth()获取宽度和高度.
	 * @param view 要测量的view
	 * @return 测量过的view
	 */
	public static void measureView(View view) {
		ViewGroup.LayoutParams p = view.getLayoutParams();
		if (p == null) {
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}

		int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
		int lpHeight = p.height;
		int childHeightSpec;
		if (lpHeight > 0) {
			childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
					MeasureSpec.EXACTLY);
		} else {
			childHeightSpec = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
		}
		view.measure(childWidthSpec, childHeightSpec);
	}
}
