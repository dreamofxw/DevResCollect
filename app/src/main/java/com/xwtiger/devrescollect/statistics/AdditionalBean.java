package com.xwtiger.devrescollect.statistics;

import java.io.Serializable;

/**
 * author:xw
 * Date:2018-06-12 10:04
 * Description:
 */
public class AdditionalBean implements Serializable{

    public String fuser_id;
    public String desc;

    public AdditionalBean(String uid,String desc){
        this.fuser_id= uid;
        this.desc = desc;
    }
}
