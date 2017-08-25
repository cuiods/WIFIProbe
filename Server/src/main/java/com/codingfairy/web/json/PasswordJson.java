package com.codingfairy.web.json;

import lombok.Data;
import lombok.NonNull;

/**
 * Created by yyy on 2017/8/25.
 */
@Data
public class PasswordJson {

    @NonNull
    private String username;
    @NonNull
    private String oldPassword;
    @NonNull
    private String newPassword;
}
