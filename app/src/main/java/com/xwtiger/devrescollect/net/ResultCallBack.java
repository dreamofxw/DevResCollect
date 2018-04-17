package com.xwtiger.devrescollect.net;

import java.io.IOException;
import java.util.concurrent.Executor;

import okhttp3.Request;

/**
 * Created by xwadmin on 2018/3/30.
 */

public interface ResultCallBack {
    public void onError(Request request, Exception e);
    public void onRespone(String str)throws IOException;
}
