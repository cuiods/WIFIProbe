package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.service.VisitCircleService;
import com.codingfairy.bl.vo.StoreHoursVo;
import com.codingfairy.bl.vo.VisitCircleVo;
import com.codingfairy.data.dao.VisitCircleDao;
import com.codingfairy.data.entity.VisitCircleEntity;
import com.codingfairy.exception.ParamException;
import com.codingfairy.exception.ServerException;
import com.codingfairy.utils.constant.ServerCode;
import com.codingfairy.utils.data.ObjectMapper;
import com.codingfairy.utils.enums.QueryThreshold;
import com.codingfairy.web.json.Tuple;
import com.codingfairy.web.json.analysis.element.VisitingCycleElement;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2017-05-16.
 * visit service impl
 */
@Service
public class VisitCircleServiceImpl implements VisitCircleService {

    @Resource
    private VisitCircleDao visitCircleDao;

    @Override
    public List<VisitCircleVo> getVisitCircleStat(int startHour, QueryThreshold threshold, int startRange, String probeId) throws ParamException {
        if (threshold==null) throw new ParamException(ServerCode.PARAM_FORMAT,"threshold","threshold cannot be null");
        return visitCircleDao.getVisitCircleStat(startHour, threshold, startRange, probeId);
    }

    @Override
    public List<Tuple<String, Number>> findByHourAndProbe(int hour, String probeId) {
        return ObjectMapper.convertToChartData(visitCircleDao.findByHourAndProbe(hour, probeId));
    }

    @Override
    public VisitCircleVo findById(int id) throws ServerException {
        VisitCircleEntity visitCircleEntity = visitCircleDao.findById(id);
        if (visitCircleEntity==null) throw new ServerException(ServerCode.NOT_FOUND);
        return new VisitCircleVo(visitCircleEntity);
    }

    @Override
    public VisitCircleVo save(VisitingCycleElement visitingCycleElement) {
        VisitCircleEntity entity = new VisitCircleEntity();
        entity.setWifiProb(visitingCycleElement.getWifiProb());
        entity.setHour(new Timestamp(visitingCycleElement.getHour()*3600*1000));
        List<Tuple<Long,Integer>> data = visitingCycleElement.getStatistic();
        int setIndex = 0;
        Field[] fields = VisitCircleEntity.class.getDeclaredFields();
        for (;setIndex<fields.length-3 && setIndex < data.size(); setIndex++) {
            Field field = fields[setIndex+3];
            field.setAccessible(true);
            try {
                field.set(entity,data.get(setIndex).getY());
            } catch (IllegalAccessException e) {
                System.out.println();
            }
            field.setAccessible(false);
        }
        return new VisitCircleVo(visitCircleDao.save(entity));
    }

}
