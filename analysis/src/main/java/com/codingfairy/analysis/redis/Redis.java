package com.codingfairy.analysis.redis;

import com.codingfairy.analysis.config.NodeConfig;
import redis.clients.jedis.Jedis;

/**
 * Created by darxan on 2017/5/9.
 */
public class Redis {

    private Jedis jedis;

    public void start() {
        jedis = new Jedis(NodeConfig.REDIS_SERVER, NodeConfig.REDIS_PORT);
        jedis.auth(NodeConfig.REDIS_AUTH);
        setSubscribers();
    }

    private void setSubscribers() {
        jedis.subscribe(new RedisListener(), NodeConfig.CHAN_PROBJSON);
    }
}
