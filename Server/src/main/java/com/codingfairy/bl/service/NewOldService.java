package com.codingfairy.bl.service;

import com.codingfairy.bl.vo.NewOldVo;
import com.codingfairy.exception.ParamException;
import com.codingfairy.utils.enums.QueryThreshold;
import com.codingfairy.web.json.Tuple;
import com.codingfairy.web.json.analysis.element.NewOldCustomElement;

import java.util.List;

/**
 * Created by cuihao on 2017-05-16.
 * new old customer service
 */
public interface NewOldService {

    List<NewOldVo> getNewOldStat(int startHour, QueryThreshold threshold, int startRange, String probeId) throws ParamException;

    List<Tuple<String,Number>> findByHourAndProbe(int hour, String probeId);

    NewOldVo findById(int id);

    NewOldVo save(NewOldCustomElement newOldCustomElement);
}
