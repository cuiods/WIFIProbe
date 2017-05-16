package com.codingfairy.data.daoImpl;

import com.codingfairy.bl.vo.StoreHoursVo;
import com.codingfairy.data.dao.StoreHoursDao;
import com.codingfairy.data.entity.StoreHoursEntity;
import com.codingfairy.utils.enums.QueryThreshold;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cuihao on 2017-05-15.
 * store hours dao impl
 */
@Repository
public class StoreHoursDaoImpl implements StoreHoursDao {
    /**
     * Customer inStore hours statistic method
     *
     * @param startHour start hour
     * @param threshold {@link QueryThreshold} of query
     *                  sum value of threshold hours
     * @param statRange range <em>THRESHOLD</em> number of statistic(NOT hour number)
     * @param probeId   id of probe device
     * @return list of {@link StoreHoursVo} with size equals to statRange
     */
    @Override
    public List<StoreHoursVo> getStoreHoursStat(int startHour, QueryThreshold threshold, int statRange, String probeId) {
        return null;
    }

    /**
     * find stat results by hour and probe id
     *
     * @param hour    hour
     * @param probeId probe id
     * @return {@link StoreHoursEntity}
     */
    @Override
    public StoreHoursEntity findByHourAndProbe(int hour, String probeId) {
        return null;
    }

    @Override
    public StoreHoursEntity findById(int id) {
        return null;
    }

    @Override
    public StoreHoursEntity save(StoreHoursEntity entity) {
        return null;
    }
}
