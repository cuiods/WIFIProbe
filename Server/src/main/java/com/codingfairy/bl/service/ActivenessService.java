package com.codingfairy.bl.service;

import com.codingfairy.bl.vo.ActivenessVo;
import com.codingfairy.data.entity.ActivenessEntity;
import com.codingfairy.exception.ParamException;
import com.codingfairy.utils.enums.QueryThreshold;

import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2017-05-16.
 * Customer activeness service
 */
public interface ActivenessService {

    List<ActivenessVo> getActivenessStat(int startHour, QueryThreshold threshold, int startRange, String probeId) throws ParamException;

    ActivenessVo findByHourAndProbe(int hour, String probeId);

    ActivenessVo findById(int id);

    ActivenessVo save(ActivenessEntity activenessEntity);
}
