package com.codingfairy.web.json;

import lombok.Data;

/**
 * Detected phone data json
 */
@Data
class PhoneJson {
    //手机的mac地址
    private String mac;
    //手机的信号强度，暂时不清楚后面的123什么意思，可能是阶段时间的强度变化
    private String rssi;
    private String rssi1;
    private String rssi2;
    private String rssi3;
    //距离
    private String range;
    //ts、 tmc、 tc 三个字段表示手机连接的 WIFI ssid,手机连接的 WIFI 的 mac 地址，手机是否连接此wifi
    //没有则为null
    private String ts;
    private String tmc;
    private String tc;
    //Y为手机是睡眠的，null为不是睡眠
    private String ds;
    //曾经连接过的wifi，基本上都是null
    private String essid0;
    private String essid1;
    private String essid2;
    private String essid3;
    private String essid4;
    private String essid5;
    private String essid6;
}
