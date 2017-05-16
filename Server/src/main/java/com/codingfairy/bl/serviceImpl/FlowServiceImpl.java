package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.service.FlowService;
import com.codingfairy.bl.vo.FlowVo;
import com.codingfairy.data.dao.FlowDao;
import com.codingfairy.data.entity.FlowEntity;
import com.codingfairy.exception.ParamException;
import com.codingfairy.utils.constant.ServerCode;
import com.codingfairy.utils.enums.QueryThreshold;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2017-05-16.
 * flow service impl
 */
public class FlowServiceImpl implements FlowService {

    @Resource
    private FlowDao flowDao;

    @Override
    public List<FlowVo> getFStat(int startHour, QueryThreshold threshold, int startRange, String probeId) throws ParamException {
        if (threshold==null) throw new ParamException(ServerCode.PARAM_FORMAT,"threshold","threshold cannot be null");
        return flowDao.getFlowStat(startHour, threshold, startRange, probeId);
    }

    @Override
    public FlowVo findByHourAndProbe(int hour, String probeId) {
        return new FlowVo(flowDao.findByHourAndProbe(hour, probeId));
    }

    @Override
    public FlowVo findById(int id) {
        return new FlowVo(flowDao.findById(id));
    }

    @Override
    public FlowVo save(FlowEntity flowEntity) {
        return new FlowVo(flowDao.save(flowEntity));
    }
}
