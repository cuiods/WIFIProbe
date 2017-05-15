package com.codingfairy.data.dao;

import com.codingfairy.data.entity.StoreHoursEntity;
import com.codingfairy.utils.enums.QueryThreshold;

import java.util.List;

/**
 * Created by cuihao on 2017-05-15.
 * Cunstomer inStore hour statistic
 */
public interface StoreHoursDao {
    /**
     * Customer inStore hours statistic method
     * @param startHour start hour
     * @param threshold {@link QueryThreshold} of query
     *                                        sum value of threshold hours
     * @param statRange range <em>THRESHOLD</em> number of statistic(NOT hour number)
     * @param probeId id of probe device
     * @return list of {@link StoreHoursEntity} with size equals to statRange
     */
    List<StoreHoursEntity> getStoreHoursStat(int startHour, QueryThreshold threshold, int statRange, String probeId);

    /**
     * find stat results by hour and probe id
     * @param hour hour
     * @param probeId probe id
     * @return {@link StoreHoursEntity}
     */
    StoreHoursEntity findByHourAndProbe(int hour, String probeId);

    StoreHoursEntity findById(int id);

    StoreHoursEntity save(StoreHoursEntity entity);
}
