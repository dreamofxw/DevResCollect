package com.xwtiger.devrescollect.statistics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * author:xw
 * Date:2018-06-12 9:26
 * Description:
 */
public class ActionLogBean implements Serializable{

    public String id;//日志uuid
    public String et;//埋点key
    public String ed;//埋点额外数据
    public String v;//
    public String ts;//时间戳版本
    public String ec;//埋点分类
    public String uid;//用户id
    public String sign;


    public ActionLogBean(String et,String ed,String ec){
        this.id = UUID.randomUUID().toString();
        this.et = et;
        this.ed = ed;
        this.ec= ec;
        this.uid ="123456";
        this.v = "4.2.0";
        this.ts = String.valueOf(System.currentTimeMillis());

        Map<String,String> map = new HashMap<String,String>();
        map.put("id",id);
        map.put("et",et);
        map.put("ed",ed);
        map.put("v",v);
        map.put("ts",ts);
        map.put("uid",uid);
        map.put("ec",ec);
        Set<String> keys = map.keySet();
        List<String> list = new ArrayList(keys);
        Collections.sort(list);
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<list.size();i++){
            sb.append("|");
            sb.append(map.get(list.get(i)));
        }
        String secret ="76440b15b8d6907fd3df95933945be47a7e98c42";
        this.sign = MD5Util.encodeBy32BitMD5(secret+sb.toString());
    }

}
