package com.codingfairy.bl.vo;

import com.codingfairy.data.entity.VisitCircleEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * Created by cuihao on 2017-05-16.
 * visit circle vo
 */
@Data
@NoArgsConstructor
public class VisitCircleVo {
    private int id;
    private String wifiProb;
    private String hour;
    private Integer data0;
    private Integer data1;
    private Integer data2;
    private Integer data3;
    private Integer data4;
    private Integer data5;
    private Integer data6;
    private Integer data7;
    private Integer data8;
    private Integer data9;
    public VisitCircleVo(VisitCircleEntity entity) {
        BeanUtils.copyProperties(entity,this,"hour");
        hour = entity.getHour().toString();
    }
}
