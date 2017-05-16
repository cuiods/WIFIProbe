package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.service.ActivenessService;
import com.codingfairy.bl.vo.ActivenessVo;
import com.codingfairy.data.entity.ActivenessEntity;
import com.codingfairy.utils.enums.QueryThreshold;

import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2017-05-16.
 * activeness service impl
 */
public class ActivenessServiceImpl implements ActivenessService {
    @Override
    public List<Map> getActivenessStat(int startHour, QueryThreshold threshold, int startRange, String probeId) {
        return null;
    }

    @Override
    public ActivenessVo findByHourAndProbe(int hour, String probeId) {
        return null;
    }

    @Override
    public ActivenessVo findById(int id) {
        return null;
    }

    @Override
    public ActivenessVo save(ActivenessEntity activenessEntity) {
        return null;
    }
}
