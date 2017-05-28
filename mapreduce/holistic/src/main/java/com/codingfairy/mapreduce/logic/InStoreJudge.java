package com.codingfairy.mapreduce.logic;

import com.codingfairy.tool.Logger;
import com.codingfairy.vo.PhoneJson;

/**
 * Created by darxan on 17-5-17.
 */
public class InStoreJudge {

    public static double STORE_RANGE = 100;

    public static boolean isInStore(PhoneJson phoneJson) {
        try {
            double range = Double.parseDouble(phoneJson.getRange());
            if (range<STORE_RANGE) {
                return true;
            }
        }catch (Exception e ){
        }
        return false;
    }
}
