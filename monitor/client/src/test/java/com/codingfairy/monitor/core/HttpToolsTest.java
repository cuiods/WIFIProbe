package com.codingfairy.monitor.core;

import org.junit.Test;

import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by cuihao on 2017-08-24.
 *
 */
public class HttpToolsTest {
    @Test
    public void sendGet() throws Exception {
        URLConnection connection = HttpTools.sendGet("http://192.168.31.1");
        Map<String, List<String>> map = connection.getHeaderFields();
        for (String key : map.keySet()) {
            System.out.println(key + "--->" + map.get(key));
        }
    }

    @Test
    public void getLocalIp() throws Exception {
        System.out.println(HttpTools.getLocalIp());
    }

}