package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.service.NewOldService;
import com.codingfairy.bl.vo.NewOldVo;
import com.codingfairy.data.dao.NewOldDao;
import com.codingfairy.data.entity.NewOldEntity;
import com.codingfairy.exception.ParamException;
import com.codingfairy.utils.constant.ServerCode;
import com.codingfairy.utils.enums.QueryThreshold;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2017-05-16.
 * new old service impl
 */
public class NewOldServiceImpl implements NewOldService {

    @Resource
    private NewOldDao newOldDao;

    @Override
    public List<NewOldVo> getNewOldStat(int startHour, QueryThreshold threshold, int startRange, String probeId) throws ParamException {
        if (threshold==null) throw new ParamException(ServerCode.PARAM_FORMAT,"threshold","threshold cannot be null");
        return newOldDao.getNewOldStat(startHour, threshold, startRange, probeId);
    }

    @Override
    public NewOldVo findByHourAndProbe(int hour, String probeId) {
        return new NewOldVo(newOldDao.findByHourAndProbe(hour, probeId));
    }

    @Override
    public NewOldVo findById(int id) {
        return new NewOldVo(newOldDao.findById(id));
    }

    @Override
    public NewOldVo save(NewOldEntity newOldEntity) {
        return new NewOldVo(newOldDao.save(newOldEntity));
    }
}
