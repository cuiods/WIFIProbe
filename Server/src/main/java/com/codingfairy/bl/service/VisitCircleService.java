package com.codingfairy.bl.service;

import com.codingfairy.bl.vo.VisitCircleVo;
import com.codingfairy.data.entity.VisitCircleEntity;
import com.codingfairy.utils.enums.QueryThreshold;

import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2017-05-16.
 * visit circle service
 */
public interface VisitCircleService {

    List<Map> getVisitCircleStat(int startHour, QueryThreshold threshold, int startRange, String probeId);

    VisitCircleVo findByHourAndProbe(int hour, String probeId);

    VisitCircleVo findById(int id);

    VisitCircleVo save(VisitCircleEntity visitCircleEntity);
}
