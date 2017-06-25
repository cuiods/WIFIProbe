package com.codingfairy.bl.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author darxyan
 */
public class NodeConfig {


    public static final String HDFS_PATH = "webhdfs://118.89.169.238:50070";
//    public static final String HDFS_PATH = "hdfs://118.89.169.238:9000";
    public static final String HADOOP_SERVER = "http://118.89.169.238:9000";
    public static final String ANALYSIS_SERVER = "118.89.169.238";
    public static final String REDIS_SERVER = "118.89.169.238";
    public static final int REDIS_PORT = 6379;
    public static final String REDIS_AUTH = "cuihaoyuanyangyang";

    public static final String CHAN_PROBJSON = "CHAN_PROBJSON";

}
