package com.codingfairy.bl.service;

import com.codingfairy.bl.vo.StoreHoursVo;
import com.codingfairy.data.entity.StoreHoursEntity;
import com.codingfairy.exception.ParamException;
import com.codingfairy.utils.enums.QueryThreshold;

import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2017-05-16.
 * in store hours stat service
 */
public interface StoreHoursService {

    List<StoreHoursVo> getStoreHoursStat(int startHour, QueryThreshold threshold, int startRange, String probeId) throws ParamException;

    StoreHoursVo findByHourAndProbe(int hour, String probeId);

    StoreHoursVo findById(int id);

    StoreHoursVo save(StoreHoursEntity storeHoursEntity);
}
