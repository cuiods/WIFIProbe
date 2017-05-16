package com.codingfairy.data.daoImpl;

import com.codingfairy.bl.vo.FlowVo;
import com.codingfairy.data.dao.FlowDao;
import com.codingfairy.data.entity.FlowEntity;
import com.codingfairy.utils.enums.QueryThreshold;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cuihao on 2017-05-15.
 * flow dao impl
 */
@Repository
public class FlowDaoImpl implements FlowDao {
    /**
     * Customer flow statistic method
     *
     * @param startHour start hour
     * @param threshold {@link QueryThreshold} of query
     *                  sum value of threshold hours
     * @param statRange range <em>THRESHOLD</em> number of statistic(NOT hour number)
     * @param probeId   id of probe device
     * @return list of {@link FlowVo} with size equals to statRange
     */
    @Override
    public List<FlowVo> getFlowStat(int startHour, QueryThreshold threshold, int statRange, String probeId) {
        return null;
    }

    /**
     * find stat results by hour and probe id
     *
     * @param hour    hour
     * @param probeId probe id
     * @return {@link FlowEntity}
     */
    @Override
    public FlowEntity findByHourAndProbe(int hour, String probeId) {
        return null;
    }

    @Override
    public FlowEntity findById(int id) {
        return null;
    }

    @Override
    public FlowEntity save(FlowEntity entity) {
        return null;
    }
}
