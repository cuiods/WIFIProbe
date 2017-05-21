package com.codingfairy.mock;

import com.google.gson.Gson;

/**
 * Json tool
 * @author cuihao
 */
public class GsonTool {

    private static Gson gson = new Gson();

    public static String convertObjectToJson(Object object) {
        return gson.toJson(object);
    }
}
