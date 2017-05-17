package com.codingfairy.web.json;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by cuihao on 2017-05-17.
 * login json
 */
@Data
public class LoginJson {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
