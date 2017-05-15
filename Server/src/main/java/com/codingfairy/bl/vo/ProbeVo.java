package com.codingfairy.bl.vo;

import com.codingfairy.data.entity.ProbeEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * probe id vo
 */
@Data
public class ProbeVo {
    private int id;
    private String probeId;
    public ProbeVo(ProbeEntity entity) {
        BeanUtils.copyProperties(entity,this);
    }
}
