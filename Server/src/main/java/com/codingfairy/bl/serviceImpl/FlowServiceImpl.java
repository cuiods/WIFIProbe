package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.service.FlowService;
import com.codingfairy.bl.vo.FlowVo;
import com.codingfairy.data.dao.FlowDao;
import com.codingfairy.data.entity.FlowEntity;
import com.codingfairy.exception.ParamException;
import com.codingfairy.exception.ServerException;
import com.codingfairy.utils.constant.ServerCode;
import com.codingfairy.utils.data.ObjectMapper;
import com.codingfairy.utils.enums.QueryThreshold;
import com.codingfairy.web.json.Tuple;
import com.codingfairy.web.json.analysis.element.CustomerFlowElement;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2017-05-16.
 * flow service impl
 */
@Service
public class FlowServiceImpl implements FlowService {

    @Resource
    private FlowDao flowDao;

    @Override
    public List<FlowVo> getFStat(int startHour, QueryThreshold threshold, int startRange, String probeId) throws ParamException {
        if (threshold==null) throw new ParamException(ServerCode.PARAM_FORMAT,"threshold","threshold cannot be null");
        return flowDao.getFlowStat(startHour, threshold, startRange, probeId);
    }

    @Override
    public List<Tuple<String, Number>> findByHourAndProbe(int hour, String probeId) {
        return ObjectMapper.convertToChartData(flowDao.findByHourAndProbe(hour, probeId));
    }

    @Override
    public FlowVo findById(int id) throws ServerException {
        FlowEntity entity = flowDao.findById(id);
        if (entity==null) throw new ServerException(ServerCode.NOT_FOUND);
        return new FlowVo(entity);
    }

    @Override
    public FlowVo save(CustomerFlowElement flowElement) {
        FlowEntity flowEntity = new FlowEntity();
        flowEntity.setWifiProb(flowElement.getWifiProb());
        flowEntity.setHour(new Timestamp(flowElement.getHour()*3600*1000));
        flowEntity.setInNoOutWifi(flowElement.getInNoOutWifi());
        flowEntity.setInNoOutStore(flowElement.getInNoOutStore());
        flowEntity.setOutNoInWifi(flowElement.getOutNoInWifi());
        flowEntity.setOutNoInStore(flowElement.getOutNoInStore());
        flowEntity.setInAndOutWifi(flowElement.getInAndOutWifi());
        flowEntity.setIntAndOutStore(flowElement.getInAndOutStore());
        flowEntity.setStayInWifi(flowElement.getStayInWifi());
        flowEntity.setStayInStore(flowElement.getStayInStore());
        flowEntity.setJumpRate(flowElement.getJumpRate());
        flowEntity.setInStoreRate(flowElement.getInStoreRate());
        flowEntity.setDeepVisit(flowElement.getDeepVisit());
        return new FlowVo(flowDao.save(flowEntity));
    }

}
