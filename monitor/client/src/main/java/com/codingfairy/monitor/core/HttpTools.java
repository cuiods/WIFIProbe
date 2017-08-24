package com.codingfairy.monitor.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;

/**
 * Created by cuihao on 2017-08-24.
 * Http connect tools
 */
public class HttpTools {

    public static URLConnection sendGet(String url) {
        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setConnectTimeout(200);
            connection.connect();
            return connection;
        } catch (IOException e) {
            return null;
        }
    }

    public static String getLocalIp() {
        InputStream inputStream = HttpTools.class.getClassLoader().getResourceAsStream("ipconfig");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String config = reader.readLine();
            Enumeration<NetworkInterface> netInterfaces;
            try {
                netInterfaces = NetworkInterface.getNetworkInterfaces();
            } catch (SocketException e) {
                e.printStackTrace();
                return null;
            }
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                if (ni.getDisplayName().equals(config)) {
                    Enumeration<InetAddress> ia = ni.getInetAddresses();
                    while (ia.hasMoreElements()) {
                        InetAddress ip = ia.nextElement();
                        String ipStr = ip.getHostAddress();
                        if (ipStr.length() < 17)
                            return ipStr;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
