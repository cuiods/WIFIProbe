package com.codingfairy.exception;

import com.codingfairy.utils.constant.ServerCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by cuihao on 2017-05-16.
 * param check exception
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ParamException extends ServerException {
    private String paramName;
    private String reason;
    public ParamException(ServerCode code, String paramName, String reason) {
        super(code);
        this.paramName = paramName;
        this.reason = reason;
    }
}
