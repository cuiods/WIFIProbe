package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.service.NewOldService;
import com.codingfairy.bl.vo.NewOldVo;
import com.codingfairy.data.entity.NewOldEntity;
import com.codingfairy.utils.enums.QueryThreshold;

import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2017-05-16.
 * new old service impl
 */
public class NewOldServiceImpl implements NewOldService {
    @Override
    public List<Map> getNewOldStat(int startHour, QueryThreshold threshold, int startRange, String probeId) {
        return null;
    }

    @Override
    public NewOldVo findByHourAndProbe(int hour, String probeId) {
        return null;
    }

    @Override
    public NewOldVo findById(int id) {
        return null;
    }

    @Override
    public NewOldVo save(NewOldEntity newOldEntity) {
        return null;
    }
}
