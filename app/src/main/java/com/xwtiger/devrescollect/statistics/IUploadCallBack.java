package com.xwtiger.devrescollect.statistics;

/**
 * author:xw
 * Date:2018-05-29 18:56
 * Description:
 */
public interface IUploadCallBack {

    public void uploadSuccess(String key);

    public void uploadFailure();

    public void uploadCancel();

}
