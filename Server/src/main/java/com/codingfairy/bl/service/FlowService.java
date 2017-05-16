package com.codingfairy.bl.service;

import com.codingfairy.bl.vo.FlowVo;
import com.codingfairy.data.entity.FlowEntity;
import com.codingfairy.utils.enums.QueryThreshold;

import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2017-05-16.
 * flow service
 */
public interface FlowService {

    List<Map> getFStat(int startHour, QueryThreshold threshold, int startRange, String probeId);

    FlowVo findByHourAndProbe(int hour, String probeId);

    FlowVo findById(int id);

    FlowVo save(FlowEntity flowEntity);
}
