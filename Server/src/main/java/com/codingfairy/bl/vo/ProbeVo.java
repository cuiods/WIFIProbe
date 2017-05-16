package com.codingfairy.bl.vo;

import com.codingfairy.data.entity.ProbeEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * probe id vo
 */
@Data
@NoArgsConstructor
public class ProbeVo {
    private int id;
    private String probeId;
    public ProbeVo(ProbeEntity entity) {
        BeanUtils.copyProperties(entity,this);
    }
}
