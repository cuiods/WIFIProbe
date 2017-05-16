package com.codingfairy.data.daoImpl;

import com.codingfairy.bl.vo.NewOldVo;
import com.codingfairy.data.dao.NewOldDao;
import com.codingfairy.data.entity.NewOldEntity;
import com.codingfairy.utils.enums.QueryThreshold;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cuihao on 2017-05-15.
 * new old customer dao impl
 */
@Repository
public class NewOldDaoImpl implements NewOldDao {
    /**
     * New old customer statistic method
     *
     * @param startHour start hour
     * @param threshold {@link QueryThreshold} of query
     *                  sum value of threshold hours
     * @param statRange range <em>THRESHOLD</em> number of statistic(NOT hour number)
     * @param probeId   id of probe device
     * @return list of {@link NewOldVo} with size equals to statRange
     */
    @Override
    public List<NewOldVo> getNewOldStat(int startHour, QueryThreshold threshold, int statRange, String probeId) {
        return null;
    }

    /**
     * find stat results by hour and probe id
     *
     * @param hour    hour
     * @param probeId probe id
     * @return {@link NewOldEntity}
     */
    @Override
    public NewOldEntity findByHourAndProbe(int hour, String probeId) {
        return null;
    }

    @Override
    public NewOldEntity findById(int id) {
        return null;
    }

    @Override
    public NewOldEntity save(NewOldEntity entity) {
        return null;
    }
}
