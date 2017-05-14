package com.codingfairy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * General exception of server end
 * @author cuihao
 */
@Getter
@Setter
@AllArgsConstructor
public class ServerException extends Exception {
    private int code;
    private String message;
}
