package com.codingfairy.web.json;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by cuihao on 2017-05-17.
 * detail query json
 */
@Data
public class QueryJson {
    @NotNull
    private int hour;
    @NotNull
    private String probeId;
}
