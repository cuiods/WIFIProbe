package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.service.ActivenessService;
import com.codingfairy.bl.vo.ActivenessVo;
import com.codingfairy.data.dao.ActivenessDao;
import com.codingfairy.data.entity.ActivenessEntity;
import com.codingfairy.exception.ParamException;
import com.codingfairy.utils.constant.ServerCode;
import com.codingfairy.utils.data.ObjectMapper;
import com.codingfairy.utils.enums.QueryThreshold;
import com.codingfairy.web.json.Tuple;
import com.codingfairy.web.json.analysis.element.CustomerActivenessElement;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2017-05-16.
 * activeness service impl
 */
@Service
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
    public List<Tuple<String, Number>> findByHourAndProbe(int hour, String probeId) {
        ActivenessEntity activenessEntity = activenessDao.findByHourAndProbe(hour, probeId);
        return ObjectMapper.convertToChartData(activenessEntity);
    }

    @Override
    public ActivenessVo findById(int id) {
        return new ActivenessVo(activenessDao.findById(id));
    }

    @Override
    public ActivenessVo save(CustomerActivenessElement activenessElement) {
        ActivenessEntity entity = new ActivenessEntity();
        entity.setWifiProb(activenessElement.getWifiProb());
        entity.setHour(new Timestamp(activenessElement.getHour()*3600*1000));
        entity.setNumOfHighActive(activenessElement.getNumOfHighActive());
        entity.setNumOfMedianActive(activenessElement.getNumOfMedianActive());
        entity.setNumOfLowActive(activenessElement.getNumOfLowActive());
        entity.setNumOfSleepActive(activenessElement.getNumOfSleepActive());
        return new ActivenessVo(activenessDao.save(entity));
    }

}
