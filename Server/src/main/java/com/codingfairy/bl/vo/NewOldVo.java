package com.codingfairy.bl.vo;

import com.codingfairy.data.entity.NewOldEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * Created by cuihao on 2017-05-16.
 * new old customer vo
 */
@Data
public class NewOldVo {
    private int id;
    private String wifiProb;
    private int hour;
    private Integer newCustomer;
    private Integer oldCustomer;
    public NewOldVo(NewOldEntity entity) {
        BeanUtils.copyProperties(entity,this);
    }
}
