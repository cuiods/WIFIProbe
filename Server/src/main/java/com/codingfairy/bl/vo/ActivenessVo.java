package com.codingfairy.bl.vo;

import com.codingfairy.data.entity.ActivenessEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * Created by cuihao on 2017-05-16.
 * activeness vo
 */
@Data
public class ActivenessVo {
    private int id;
    private String wifiProb;
    private int hour;
    private Integer numOfHighActive;
    private Integer numOfMedianActive;
    private Integer numOfLowActive;
    private Integer numOfSleepActive;
    public ActivenessVo(ActivenessEntity entity) {
        BeanUtils.copyProperties(entity,this);
    }
}
