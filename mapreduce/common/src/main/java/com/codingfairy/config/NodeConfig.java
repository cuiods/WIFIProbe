package com.codingfairy.config;


/**
 * @author darxyan
 */
public class NodeConfig {


    public static final String RECEIVER = "118.89.166.159:8080";

    public static final String NAME_NODE = "115.29.138.254";

    public static final Long MAX_WIFI_DATA_INTERVAL = 1000L*60L*10L;

    public static final String HDFS_PATH = "webhdfs://"+NAME_NODE+":50070/";
}
