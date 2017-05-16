package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.service.StoreHoursService;
import com.codingfairy.bl.vo.StoreHoursVo;
import com.codingfairy.data.entity.StoreHoursEntity;
import com.codingfairy.utils.enums.QueryThreshold;

import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2017-05-16.
 * store hours service impl
 */
public class StoreHoursServiceImpl implements StoreHoursService {
    @Override
    public List<Map> getStoreHoursStat(int startHour, QueryThreshold threshold, int startRange, String probeId) {
        return null;
    }

    @Override
    public StoreHoursVo findByHourAndProbe(int hour, String probeId) {
        return null;
    }

    @Override
    public StoreHoursVo findById(int id) {
        return null;
    }

    @Override
    public StoreHoursVo save(StoreHoursEntity storeHoursEntity) {
        return null;
    }
}
