package com.codingfairy.bl.service;

import com.codingfairy.bl.vo.NewOldVo;
import com.codingfairy.data.entity.NewOldEntity;
import com.codingfairy.utils.enums.QueryThreshold;

import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2017-05-16.
 * new old customer service
 */
public interface NewOldService {

    List<Map> getNewOldStat(int startHour, QueryThreshold threshold, int startRange, String probeId);

    NewOldVo findByHourAndProbe(int hour, String probeId);

    NewOldVo findById(int id);

    NewOldVo save(NewOldEntity newOldEntity);
}
