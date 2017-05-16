package com.codingfairy.data.dao;

import com.codingfairy.bl.vo.ActivenessVo;
import com.codingfairy.data.entity.ActivenessEntity;
import com.codingfairy.utils.enums.QueryThreshold;

import java.util.List;

/**
 * Created by cuihao on 2017-05-15.
 * Customer activeness dao
 */
public interface ActivenessDao {
    /**
     * Customer activeness statistic method
     * @param startHour start hour
     * @param threshold {@link QueryThreshold} of query
     *                                        sum value of threshold hours
     * @param statRange range <em>THRESHOLD</em> number of statistic(NOT hour number)
     * @param probeId id of probe device
     * @return list of {@link ActivenessVo} with size equals to statRange
     */
    List<ActivenessVo> getActivenessStat(int startHour, QueryThreshold threshold, int statRange, String probeId);

    /**
     * find stat results by hour and probe id
     * @param hour hour
     * @param probeId probe id
     * @return {@link ActivenessEntity}
     */
    ActivenessEntity findByHourAndProbe(int hour, String probeId);

    ActivenessEntity findById(int id);

    ActivenessEntity save(ActivenessEntity entity);
}
