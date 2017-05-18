package com.codingfairy.web.json;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * Created by cuihao on 2017-05-17.
 * page json
 */
@Data
public class PageJson {
    @Min(0)
    private int page;
    @Min(1)
    private int size;
}
