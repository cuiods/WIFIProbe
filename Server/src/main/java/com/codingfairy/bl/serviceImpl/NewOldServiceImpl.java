package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.service.NewOldService;
import com.codingfairy.bl.vo.NewOldVo;
import com.codingfairy.data.dao.NewOldDao;
import com.codingfairy.data.entity.NewOldEntity;
import com.codingfairy.exception.ParamException;
import com.codingfairy.exception.ServerException;
import com.codingfairy.utils.constant.ServerCode;
import com.codingfairy.utils.data.ObjectMapper;
import com.codingfairy.utils.enums.QueryThreshold;
import com.codingfairy.web.json.Tuple;
import com.codingfairy.web.json.analysis.element.NewOldCustomElement;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2017-05-16.
 * new old service impl
 */
@Service
public class NewOldServiceImpl implements NewOldService {

    @Resource
    private NewOldDao newOldDao;

    @Override
    public List<NewOldVo> getNewOldStat(int startHour, QueryThreshold threshold, int startRange, String probeId) throws ParamException {
        if (threshold==null) throw new ParamException(ServerCode.PARAM_FORMAT,"threshold","threshold cannot be null");
        return newOldDao.getNewOldStat(startHour, threshold, startRange, probeId);
    }

    @Override
    public List<Tuple<String, Number>> findByHourAndProbe(int hour, String probeId) {
        return ObjectMapper.convertToChartData(newOldDao.findByHourAndProbe(hour, probeId));
    }

    @Override
    public NewOldVo findById(int id) throws ServerException {
        NewOldEntity entity = newOldDao.findById(id);
        if (entity == null) throw new ServerException(ServerCode.NOT_FOUND);
        return new NewOldVo(entity);
    }

    @Override
    public NewOldVo save(NewOldCustomElement newOldCustomElement) {
        NewOldEntity entity = new NewOldEntity();
        entity.setWifiProb(newOldCustomElement.getWifiProb());
        entity.setHour(new Timestamp(newOldCustomElement.getHour()*3600*1000));
        entity.setNewCustomer(newOldCustomElement.getNewCustomer());
        entity.setOldCustomer(newOldCustomElement.getOldCustomer());
        return new NewOldVo(newOldDao.save(entity));
    }
}
