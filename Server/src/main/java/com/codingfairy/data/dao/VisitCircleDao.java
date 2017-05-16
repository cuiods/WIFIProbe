package com.codingfairy.data.dao;

import com.codingfairy.bl.vo.VisitCircleVo;
import com.codingfairy.data.entity.VisitCircleEntity;
import com.codingfairy.utils.enums.QueryThreshold;

import java.util.List;

/**
 * Created by cuihao on 2017-05-15.
 * Customer visiting circle statistic dao
 */
public interface VisitCircleDao {
    /**
     * Customer visiting circle statistic method
     * @param startHour start hour
     * @param threshold {@link QueryThreshold} of query
     *                                        sum value of threshold hours
     * @param statRange range <em>THRESHOLD</em> number of statistic(NOT hour number)
     * @param probeId id of probe device
     * @return list of {@link VisitCircleVo} with size equals to statRange
     */
    List<VisitCircleVo> getVisitCircleStat(int startHour, QueryThreshold threshold, int statRange, String probeId);

    /**
     * find stat results by hour and probe id
     * @param hour hour
     * @param probeId probe id
     * @return {@link VisitCircleEntity}
     */
    VisitCircleEntity findByHourAndProbe(int hour, String probeId);

    VisitCircleEntity findById(int id);

    VisitCircleEntity save(VisitCircleEntity entity);
}
