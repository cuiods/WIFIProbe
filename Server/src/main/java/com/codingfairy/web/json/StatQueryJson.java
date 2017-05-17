package com.codingfairy.web.json;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

/**
 * Created by cuihao on 2017-05-17.
 * stat query json
 */
@Data
public class StatQueryJson {
    @Min(-1)
    private int startHour;
    @Pattern(regexp = "HOUR|DAY|WEEK|MONTH|YEAR")
    private String threshold;
    @Min(0)
    private int startRange;
    private String probeId;
}
