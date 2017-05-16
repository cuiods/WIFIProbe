package com.codingfairy.bl.vo;

import com.codingfairy.data.entity.ActivenessEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * Created by cuihao on 2017-05-16.
 * activeness vo
 */
@Data
@NoArgsConstructor
public class ActivenessVo {
    private int id;
    private String wifiProb;
    private String hour;
    private Integer numOfHighActive;
    private Integer numOfMedianActive;
    private Integer numOfLowActive;
    private Integer numOfSleepActive;
    public ActivenessVo(ActivenessEntity entity) {
        BeanUtils.copyProperties(entity,this,"name");
        hour = entity.getHour().toString();
    }
}
