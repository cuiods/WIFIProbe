package com.codingfairy.web.json;

import lombok.Data;

import java.util.List;

/**
 * probe data json
 */
@Data
public class ProbeJson {
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
}
