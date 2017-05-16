package com.codingfairy.bl.vo;

import com.codingfairy.data.entity.FlowEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * Created by cuihao on 2017-05-16.
 * Flow stat vo
 */
@Data
@NoArgsConstructor
public class FlowVo {
    private int id;
    private String wifiProb;
    private String hour;
    private Integer inNoOutWifi;
    private Integer inNoOutStore;
    private Integer outNoInWifi;
    private Integer outNoInStore;
    private Integer inAndOutWifi;
    private Integer intAndOutStore;
    private Integer stayInWifi;
    private Integer stayInStore;
    private Double jumpRate;
    private Double deepVisit;
    private Double inStoreRate;
    public FlowVo(FlowEntity entity) {
        BeanUtils.copyProperties(entity,this,"hour");
        hour = entity.getHour().toString();
    }
}
