package com.codingfairy.bl.service;

import com.codingfairy.bl.vo.VisitCircleVo;
import com.codingfairy.exception.ParamException;
import com.codingfairy.utils.enums.QueryThreshold;
import com.codingfairy.web.json.Tuple;
import com.codingfairy.web.json.analysis.element.VisitingCycleElement;

import java.util.List;

/**
 * Created by cuihao on 2017-05-16.
 * visit circle service
 */
public interface VisitCircleService {

    List<VisitCircleVo> getVisitCircleStat(int startHour, QueryThreshold threshold, int startRange, String probeId) throws ParamException;

    List<Tuple<String,Number>> findByHourAndProbe(int hour, String probeId);

    VisitCircleVo findById(int id);

    VisitCircleVo save(VisitingCycleElement visitingCycleElement);
}
