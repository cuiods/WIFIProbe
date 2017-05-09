package com.codingfairy.web.json;

import java.io.Serializable;
import java.util.List;

/**
 * probe data json
 */
public class ProbeJson implements Serializable{

    private String id;
    private String mmac;
    private String rate;
    private String wssid;
    private String wmac;
    private String time;
    private String lat;
    private String lon;
    private String addr;

    private List<PhoneJson> data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMmac() {
        return mmac;
    }

    public void setMmac(String mmac) {
        this.mmac = mmac;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getWssid() {
        return wssid;
    }

    public void setWssid(String wssid) {
        this.wssid = wssid;
    }

    public String getWmac() {
        return wmac;
    }

    public void setWmac(String wmac) {
        this.wmac = wmac;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public List<PhoneJson> getData() {
        return data;
    }

    public void setData(List<PhoneJson> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ProbeJson{" +
                "id='" + id + '\'' +
                ", mmac='" + mmac + '\'' +
                ", rate='" + rate + '\'' +
                ", wssid='" + wssid + '\'' +
                ", wmac='" + wmac + '\'' +
                ", time='" + time + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", addr='" + addr + '\'' +
                ", data=" + data.size() +
                '}';
    }
}
