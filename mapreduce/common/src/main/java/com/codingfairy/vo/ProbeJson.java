package com.codingfairy.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * probe data json
 */
@Data
public class ProbeJson implements Cloneable{
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

    @Override
    public ProbeJson clone() throws CloneNotSupportedException {
        ProbeJson cloned = (ProbeJson)super.clone();
        cloned.data = new ArrayList<PhoneJson>();
        return cloned;
    }
}
