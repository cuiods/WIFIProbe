package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.service.StoreHoursService;
import com.codingfairy.bl.vo.StoreHoursVo;
import com.codingfairy.data.dao.StoreHoursDao;
import com.codingfairy.data.entity.StoreHoursEntity;
import com.codingfairy.exception.ParamException;
import com.codingfairy.utils.constant.ServerCode;
import com.codingfairy.utils.data.ObjectMapper;
import com.codingfairy.utils.enums.QueryThreshold;
import com.codingfairy.web.json.Tuple;
import com.codingfairy.web.json.analysis.element.InStoreHoursElement;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2017-05-16.
 * store hours service impl
 */
@Service
public class StoreHoursServiceImpl implements StoreHoursService {

    @Resource
    private StoreHoursDao storeHoursDao;

    @Override
    public List<StoreHoursVo> getStoreHoursStat(int startHour, QueryThreshold threshold, int startRange, String probeId) throws ParamException {
        if (threshold==null) throw new ParamException(ServerCode.PARAM_FORMAT,"threshold","threshold cannot be null");
        return storeHoursDao.getStoreHoursStat(startHour, threshold, startRange, probeId);
    }

    @Override
    public List<Tuple<String, Number>> findByHourAndProbe(int hour, String probeId) {
        return ObjectMapper.convertToChartData(storeHoursDao.findByHourAndProbe(hour, probeId));
    }

    @Override
    public StoreHoursVo findById(int id) {
        return new StoreHoursVo(storeHoursDao.findById(id));
    }

    @Override
    public StoreHoursVo save(InStoreHoursElement storeHoursElement) {
        StoreHoursEntity entity = new StoreHoursEntity();
        entity.setWifiProb(storeHoursElement.getWifiProb());
        entity.setHour(new Timestamp(storeHoursElement.getHour()*3600*1000));
        List<Tuple<Long,Integer>> data = storeHoursElement.getStatistic();
        int setIndex = 0;
        Field[] fields = StoreHoursEntity.class.getDeclaredFields();
        for (;setIndex<fields.length-3 && setIndex < data.size(); setIndex++) {
            Field field = fields[setIndex+3];
            field.setAccessible(true);
            try {
                field.set(entity,data.get(setIndex).getY());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            field.setAccessible(false);
        }
        return new StoreHoursVo(storeHoursDao.save(entity));
    }
}
