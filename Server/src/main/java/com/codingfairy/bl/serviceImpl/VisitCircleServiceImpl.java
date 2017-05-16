package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.service.VisitCircleService;
import com.codingfairy.bl.vo.VisitCircleVo;
import com.codingfairy.data.entity.VisitCircleEntity;
import com.codingfairy.utils.enums.QueryThreshold;

import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2017-05-16.
 * visit service impl
 */
public class VisitCircleServiceImpl implements VisitCircleService {
    @Override
    public List<Map> getVisitCircleStat(int startHour, QueryThreshold threshold, int startRange, String probeId) {
        return null;
    }

    @Override
    public VisitCircleVo findByHourAndProbe(int hour, String probeId) {
        return null;
    }

    @Override
    public VisitCircleVo findById(int id) {
        return null;
    }

    @Override
    public VisitCircleVo save(VisitCircleEntity visitCircleEntity) {
        return null;
    }
}
