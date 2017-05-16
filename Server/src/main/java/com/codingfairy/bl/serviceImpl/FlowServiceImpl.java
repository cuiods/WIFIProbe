package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.service.FlowService;
import com.codingfairy.bl.vo.FlowVo;
import com.codingfairy.data.entity.FlowEntity;
import com.codingfairy.utils.enums.QueryThreshold;

import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2017-05-16.
 * flow service impl
 */
public class FlowServiceImpl implements FlowService {
    @Override
    public List<Map> getFStat(int startHour, QueryThreshold threshold, int startRange, String probeId) {
        return null;
    }

    @Override
    public FlowVo findByHourAndProbe(int hour, String probeId) {
        return null;
    }

    @Override
    public FlowVo findById(int id) {
        return null;
    }

    @Override
    public FlowVo save(FlowEntity flowEntity) {
        return null;
    }
}
