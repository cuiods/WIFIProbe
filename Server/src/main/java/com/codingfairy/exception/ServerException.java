package com.codingfairy.exception;

import com.codingfairy.utils.constant.ServerCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * General exception of server end
 * @author cuihao
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServerException extends Exception {
    private int code;
    private String message;
    public ServerException(ServerCode serverCode) {
        this.code = serverCode.getCode();
        this.message = serverCode.getMsg();
    }
}
