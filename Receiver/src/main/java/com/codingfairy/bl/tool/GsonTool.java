package com.codingfairy.bl.tool;

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

    public static <T> T convertJsonToObject(Class<T> clazz, String json) {
        return gson.fromJson(json,clazz);
    }
}
