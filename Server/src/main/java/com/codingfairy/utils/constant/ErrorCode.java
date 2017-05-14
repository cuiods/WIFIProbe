package com.codingfairy.utils.constant;

/**
 * Server system error code definition
 * @author cuihao
 */
public enum ErrorCode {

    USER_NOT_FOUND(1000,"Cannot find user, make sure you have registered."),
    ERROR_PASSWORD(1001,"Error password, please check again.");

    private int code;
    private String msg;
    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
