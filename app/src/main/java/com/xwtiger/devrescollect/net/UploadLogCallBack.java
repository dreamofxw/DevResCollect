package com.xwtiger.devrescollect.net;

import java.io.IOException;

import okhttp3.Request;

/**
 * author:xw
 * Date:2018-06-11 16:56
 * Description:
 */
public interface UploadLogCallBack {
    public void onError(Request request, Exception e,String logkey);
    public void onRespone(String response,String logkey)throws IOException;
}
