package com.codingfairy.data.daoImpl;

import com.codingfairy.data.dao.ActivenessDao;
import com.codingfairy.data.entity.ActivenessEntity;
import com.codingfairy.utils.enums.QueryThreshold;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cuihao on 2017-05-15.
 * activeness dao impl
 */
@Repository
public class ActivenessDaoImpl implements ActivenessDao {
    /**
     * Customer activeness statistic method
     *
     * @param startHour start hour
     * @param threshold {@link QueryThreshold} of query
     *                  sum value of threshold hours
     * @param statRange range <em>THRESHOLD</em> number of statistic(NOT hour number)
     * @param probeId   id of probe device
     * @return list of {@link ActivenessEntity} with size equals to statRange
     */
    @Override
    public List<ActivenessEntity> getActivenessStat(int startHour, QueryThreshold threshold, int statRange, String probeId) {
        return null;
    }

    /**
     * find stat results by hour and probe id
     *
     * @param hour    hour
     * @param probeId probe id
     * @return {@link ActivenessEntity}
     */
    @Override
    public ActivenessEntity findByHourAndProbe(int hour, String probeId) {
        return null;
    }

    @Override
    public ActivenessEntity findById(int id) {
        return null;
    }

    @Override
    public ActivenessEntity save(ActivenessEntity entity) {
        return null;
    }
}
