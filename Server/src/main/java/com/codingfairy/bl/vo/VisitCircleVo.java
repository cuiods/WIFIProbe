package com.codingfairy.bl.vo;

import com.codingfairy.data.entity.VisitCircleEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * Created by cuihao on 2017-05-16.
 * visit circle vo
 */
@Data
public class VisitCircleVo {
    private int id;
    private String wifiProb;
    private int hour;
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
        BeanUtils.copyProperties(entity,this);
    }
}
