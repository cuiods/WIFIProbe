package com.codingfairy.web.json;

import lombok.Data;

/**
 * Detected phone data json
 */
@Data
class PhoneJson {
    private String mac;
    private String rssi;
    private String range;
    private String ts;
    private String tmc;
    private String tc;
    private String ds;
    private String essid0;
    private String essid1;
    private String essid2;
    private String essid3;
    private String essid4;
    private String essid5;
    private String essid6;
}
