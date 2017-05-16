package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.service.ActivenessService;
import com.codingfairy.bl.vo.ActivenessVo;
import com.codingfairy.data.dao.ActivenessDao;
import com.codingfairy.data.entity.ActivenessEntity;
import com.codingfairy.exception.ParamException;
import com.codingfairy.utils.constant.ServerCode;
import com.codingfairy.utils.enums.QueryThreshold;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2017-05-16.
 * activeness service impl
 */
public class ActivenessServiceImpl implements ActivenessService {

    @Resource
    private ActivenessDao activenessDao;

    @Override
    public List<ActivenessVo> getActivenessStat(int startHour, QueryThreshold threshold, int startRange, String probeId)
            throws ParamException {
        if (threshold==null) throw new ParamException(ServerCode.PARAM_FORMAT,"threshold","threshold cannot be null");
        return activenessDao.getActivenessStat(startHour, threshold, startRange, probeId);
    }

    @Override
    public ActivenessVo findByHourAndProbe(int hour, String probeId) {
        return new ActivenessVo(activenessDao.findByHourAndProbe(hour, probeId));
    }

    @Override
    public ActivenessVo findById(int id) {
        return new ActivenessVo(activenessDao.findById(id));
    }

    @Override
    public ActivenessVo save(ActivenessEntity activenessEntity) {
        return new ActivenessVo(activenessDao.save(activenessEntity));
    }
}
