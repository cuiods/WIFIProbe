package com.codingfairy.data.dao;

import com.codingfairy.bl.vo.NewOldVo;
import com.codingfairy.data.entity.NewOldEntity;
import com.codingfairy.utils.enums.QueryThreshold;

import java.util.List;

/**
 * Created by cuihao on 2017-05-15.
 * New old customer statistic dao
 */
public interface NewOldDao {
    /**
     * New old customer statistic method
     * @param startHour start hour
     * @param threshold {@link QueryThreshold} of query
     *                                        sum value of threshold hours
     * @param statRange range <em>THRESHOLD</em> number of statistic(NOT hour number)
     * @param probeId id of probe device
     * @return list of {@link NewOldVo} with size equals to statRange
     */
    List<NewOldVo> getNewOldStat(int startHour, QueryThreshold threshold, int statRange, String probeId);

    /**
     * find stat results by hour and probe id
     * @param hour hour
     * @param probeId probe id
     * @return {@link NewOldEntity}
     */
    NewOldEntity findByHourAndProbe(int hour, String probeId);

    NewOldEntity findById(int id);

    NewOldEntity save(NewOldEntity entity);
}
