package com.codingfairy.analysis.redis;

import com.codingfairy.web.json.ProbeJson;
import com.google.gson.Gson;
import redis.clients.jedis.Client;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by darxan on 2017/5/9.
 */
public class RedisListener extends JedisPubSub {


    private Gson gson;

    public RedisListener() {
        gson = new Gson();
    }

    @Override
    public void onMessage(String channel, String message) {
        ProbeJson probeJson = gson.fromJson(message, ProbeJson.class);
        System.out.print(probeJson);
    }

}
