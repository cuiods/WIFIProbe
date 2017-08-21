package com.codingfairy.web.json;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * probe data json
 */
@Data
@ToString
public class ProbeJson {
    //设备id
    private String id;
    //设备mac地址
    private String mmac;
    //发送频率
    private String rate;
    //null
    private String wssid;
    //null
    private String wmac;
    //时间
    private String time;
    //纬度
    private String lat;
    //经度
    private String lon;
    //null
    private String addr;
    //具体信息
    private List<PhoneJson> data;
}
