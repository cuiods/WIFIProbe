package com.codingfairy.web.handler;

import com.codingfairy.bl.vo.ResultVo;
import com.codingfairy.exception.ArgInvalidResult;
import com.codingfairy.exception.ParamException;
import com.codingfairy.exception.ServerException;
import com.codingfairy.utils.constant.ServerCode;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihao on 2017-05-17.
 * handle exceptions
 */
@ControllerAdvice(basePackages = "com.codingfairy.web.controller")
@ResponseBody
public class ServerExceptionHandler {

    @ExceptionHandler(value=MethodArgumentNotValidException.class)
    public ResultVo<List> methodArgumentNotValidHandler(MethodArgumentNotValidException exception) throws Exception {
        List<ArgInvalidResult> invalidArguments = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            ArgInvalidResult invalidArgument = new ArgInvalidResult();
            invalidArgument.setDefaultMessage(error.getDefaultMessage());
            invalidArgument.setField(error.getField());
            invalidArgument.setRejectedValue(error.getRejectedValue());
            invalidArguments.add(invalidArgument);
        });
        return new ResultVo<>(ServerCode.PARAM_FORMAT,invalidArguments);
    }

    @ExceptionHandler(value = ServerException.class)
    public ResultVo<String> serverExceptionHandler(ServerException exception) throws Exception {
        return new ResultVo<>(exception,exception.getMessage());
    }

    @ExceptionHandler(value = ParamException.class)
    public ResultVo<ServerException> paramExceptionHandler(ParamException exception) throws Exception {
        return new ResultVo<>(exception,exception);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResultVo<String> accessDeniedExceptionHandler(AccessDeniedException exception)
            throws Exception {
        return new ResultVo<>(ServerCode.ACCESS_DENIED,exception.getReason() +":"+exception.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResultVo<String> unknownExceptionHandler(Exception exception)
            throws Exception {
        return new ResultVo<>(ServerCode.UNKNOWN_ERROR,exception.getMessage());
    }
}
