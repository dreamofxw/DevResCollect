package com.xwtiger.devrescollect.statistics;

import java.io.Serializable;

/**
 * author:xw
 * Date:2018-06-12 15:41
 * Description:
 */
public class ResultBean implements Serializable{

    //{"errno":0,"errmsg":"ok","data":{"count":2}}

    public String errno;
    public String errmsg;
    public CountBean data;
    static class CountBean{
        public String count;
    }
}
