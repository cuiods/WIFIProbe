package com.codingfairy.bl.vo;

import com.codingfairy.data.entity.NewOldEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * Created by cuihao on 2017-05-16.
 * new old customer vo
 */
@Data
@NoArgsConstructor
public class NewOldVo {
    private int id;
    private String wifiProb;
    private String hour;
    private Integer newCustomer;
    private Integer oldCustomer;
    public NewOldVo(NewOldEntity entity) {
        BeanUtils.copyProperties(entity,this,"hour");
        hour = entity.getHour().toString();
    }
}
