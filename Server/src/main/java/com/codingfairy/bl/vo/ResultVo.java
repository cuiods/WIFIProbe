package com.codingfairy.bl.vo;

import com.codingfairy.exception.ServerException;
import com.codingfairy.utils.constant.ServerCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by cuihao on 2017-05-16.
 * server response
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVo<T> {
    private int code;
    private String msg;
    private T data;
    public ResultVo(ServerCode code, T data) {
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.data = data;
    }
    public ResultVo(ServerException e, T data) {
        this.code = e.getCode();
        this.msg = e.getClass().getName();
        this.data = data;
    }
}
