package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.service.StoreHoursService;
import com.codingfairy.bl.vo.StoreHoursVo;
import com.codingfairy.data.dao.StoreHoursDao;
import com.codingfairy.data.entity.StoreHoursEntity;
import com.codingfairy.exception.ParamException;
import com.codingfairy.utils.constant.ServerCode;
import com.codingfairy.utils.enums.QueryThreshold;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2017-05-16.
 * store hours service impl
 */
public class StoreHoursServiceImpl implements StoreHoursService {

    @Resource
    private StoreHoursDao storeHoursDao;

    @Override
    public List<StoreHoursVo> getStoreHoursStat(int startHour, QueryThreshold threshold, int startRange, String probeId) throws ParamException {
        if (threshold==null) throw new ParamException(ServerCode.PARAM_FORMAT,"threshold","threshold cannot be null");
        return storeHoursDao.getStoreHoursStat(startHour, threshold, startRange, probeId);
    }

    @Override
    public StoreHoursVo findByHourAndProbe(int hour, String probeId) {
        return new StoreHoursVo(storeHoursDao.findByHourAndProbe(hour, probeId));
    }

    @Override
    public StoreHoursVo findById(int id) {
        return new StoreHoursVo(storeHoursDao.findById(id));
    }

    @Override
    public StoreHoursVo save(StoreHoursEntity storeHoursEntity) {
        return new StoreHoursVo(storeHoursDao.save(storeHoursEntity));
    }
}
