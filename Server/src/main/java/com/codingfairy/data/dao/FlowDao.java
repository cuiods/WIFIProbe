package com.codingfairy.data.dao;

import com.codingfairy.data.entity.FlowEntity;
import com.codingfairy.utils.enums.QueryThreshold;

import java.util.List;

/**
 * Created by cuihao on 2017-05-15.
 * Customer flow stat dao
 */
public interface FlowDao {
    /**
     * Customer flow statistic method
     * @param startHour start hour
     * @param threshold {@link QueryThreshold} of query
     *                                        sum value of threshold hours
     * @param statRange range <em>THRESHOLD</em> number of statistic(NOT hour number)
     * @return list of {@link FlowEntity} with size equals to statRange
     */
    List<FlowEntity> getFlowStat(int startHour, QueryThreshold threshold, int statRange, String probeId);

    /**
     * find stat results by hour and probe id
     * @param hour hour
     * @param probeId probe id
     * @return {@link FlowEntity}
     */
    FlowEntity findByHourAndProbe(int hour, String probeId);

    FlowEntity findById(int id);

    FlowEntity save(FlowEntity entity);
}
