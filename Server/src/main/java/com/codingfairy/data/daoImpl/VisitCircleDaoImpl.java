package com.codingfairy.data.daoImpl;

import com.codingfairy.data.dao.VisitCircleDao;
import com.codingfairy.data.entity.VisitCircleEntity;
import com.codingfairy.utils.enums.QueryThreshold;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cuihao on 2017-05-15.
 * visit circle dao impl
 */
@Repository
public class VisitCircleDaoImpl implements VisitCircleDao {
    /**
     * Customer visiting circle statistic method
     *
     * @param startHour start hour
     * @param threshold {@link QueryThreshold} of query
     *                  sum value of threshold hours
     * @param statRange range <em>THRESHOLD</em> number of statistic(NOT hour number)
     * @param probeId   id of probe device
     * @return list of {@link VisitCircleEntity} with size equals to statRange
     */
    @Override
    public List<VisitCircleEntity> getVisitCircleStat(int startHour, QueryThreshold threshold, int statRange, String probeId) {
        return null;
    }

    /**
     * find stat results by hour and probe id
     *
     * @param hour    hour
     * @param probeId probe id
     * @return {@link VisitCircleEntity}
     */
    @Override
    public VisitCircleEntity findByHourAndProbe(int hour, String probeId) {
        return null;
    }

    @Override
    public VisitCircleEntity findById(int id) {
        return null;
    }

    @Override
    public VisitCircleEntity save(VisitCircleEntity entity) {
        return null;
    }
}
