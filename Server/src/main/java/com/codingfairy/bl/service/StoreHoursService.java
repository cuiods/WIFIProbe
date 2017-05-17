package com.codingfairy.bl.service;

import com.codingfairy.bl.vo.StoreHoursVo;
import com.codingfairy.exception.ParamException;
import com.codingfairy.utils.enums.QueryThreshold;
import com.codingfairy.web.json.Tuple;
import com.codingfairy.web.json.analysis.element.InStoreHoursElement;

import java.util.List;

/**
 * Created by cuihao on 2017-05-16.
 * in store hours stat service
 */
public interface StoreHoursService {

    List<StoreHoursVo> getStoreHoursStat(int startHour, QueryThreshold threshold, int startRange, String probeId) throws ParamException;

    List<Tuple<String,Number>> findByHourAndProbe(int hour, String probeId);

    StoreHoursVo findById(int id);

    StoreHoursVo save(InStoreHoursElement storeHoursElement);
}
