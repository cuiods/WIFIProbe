package com.codingfairy.vo;

import lombok.Data;

/**
 * Real time statistic json
 */
@Data
public class RealTimeJson {
    private String osName;
    private String osArch;
    private String osVersion;
    private int bufferSize;
    private String serverName;
    private int connectNum;
    private String latestCommit;
}
