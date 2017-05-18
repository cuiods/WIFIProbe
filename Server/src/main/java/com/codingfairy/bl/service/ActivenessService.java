package com.codingfairy.bl.service;

import com.codingfairy.bl.vo.ActivenessVo;
import com.codingfairy.exception.ParamException;
import com.codingfairy.exception.ServerException;
import com.codingfairy.utils.enums.QueryThreshold;
import com.codingfairy.web.json.Tuple;
import com.codingfairy.web.json.analysis.element.CustomerActivenessElement;

import java.util.List;

/**
 * Created by cuihao on 2017-05-16.
 * Customer activeness service
 */
public interface ActivenessService {

    List<ActivenessVo> getActivenessStat(int startHour, QueryThreshold threshold, int startRange, String probeId) throws ParamException;

    List<Tuple<String,Number>> findByHourAndProbe(int hour, String probeId);

    ActivenessVo findById(int id) throws ServerException;

    ActivenessVo save(CustomerActivenessElement activenessElement);
}
