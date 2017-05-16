package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.service.VisitCircleService;
import com.codingfairy.bl.vo.VisitCircleVo;
import com.codingfairy.data.dao.VisitCircleDao;
import com.codingfairy.data.entity.VisitCircleEntity;
import com.codingfairy.exception.ParamException;
import com.codingfairy.utils.constant.ServerCode;
import com.codingfairy.utils.enums.QueryThreshold;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2017-05-16.
 * visit service impl
 */
public class VisitCircleServiceImpl implements VisitCircleService {

    @Resource
    private VisitCircleDao visitCircleDao;

    @Override
    public List<VisitCircleVo> getVisitCircleStat(int startHour, QueryThreshold threshold, int startRange, String probeId) throws ParamException {
        if (threshold==null) throw new ParamException(ServerCode.PARAM_FORMAT,"threshold","threshold cannot be null");
        return visitCircleDao.getVisitCircleStat(startHour, threshold, startRange, probeId);
    }

    @Override
    public VisitCircleVo findByHourAndProbe(int hour, String probeId) {
        return new VisitCircleVo(visitCircleDao.findByHourAndProbe(hour, probeId));
    }

    @Override
    public VisitCircleVo findById(int id) {
        return new VisitCircleVo(visitCircleDao.findById(id));
    }

    @Override
    public VisitCircleVo save(VisitCircleEntity visitCircleEntity) {
        return new VisitCircleVo(visitCircleDao.save(visitCircleEntity));
    }
}
