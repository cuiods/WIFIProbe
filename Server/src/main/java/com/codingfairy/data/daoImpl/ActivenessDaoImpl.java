package com.codingfairy.data.daoImpl;

import com.codingfairy.bl.vo.ActivenessVo;
import com.codingfairy.data.dao.ActivenessDao;
import com.codingfairy.data.entity.ActivenessEntity;
import com.codingfairy.data.repo.ActivenessRepository;
import com.codingfairy.utils.data.ObjectMapper;
import com.codingfairy.utils.data.ThresholdUtil;
import com.codingfairy.utils.enums.QueryThreshold;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cuihao on 2017-05-15.
 * activeness dao impl
 */
@Repository
public class ActivenessDaoImpl implements ActivenessDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private ActivenessRepository activenessRepository;

    /**
     * Customer activeness statistic method
     *
     * @param startHour start hour
     * @param threshold {@link QueryThreshold} of query
     *                  sum value of threshold hours
     * @param statRange range <em>THRESHOLD</em> number of statistic(NOT hour number)
     * @param probeId   id of probe device
     * @return list of {@link ActivenessVo} with size equals to statRange
     */
    @Override
    public List<ActivenessVo> getActivenessStat(int startHour, QueryThreshold threshold, int statRange, String probeId) {
        String isProbeSelected = probeId==null || probeId.isEmpty()? "": "AND wifiProb = :probeId ";
        String sqlQuery = "SELECT id,wifiProb,DATE_FORMAT(hour,:dateFormat),sum(numOfHighActive)," +
                "sum(numOfMedianActive),sum(numOfLowActive),sum(numOfSleepActive) " +
                "FROM activeness " +
                "WHERE TO_SECONDS(hour) >= (:startHour*3600) " + isProbeSelected+
                " GROUP BY id,wifiProb,DATE_FORMAT(hour,:dateFormat) " +
                "LIMIT 0,:statRange";
        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("dateFormat", ThresholdUtil.convertToString(threshold));
        query.setParameter("startHour",startHour);
        if (!isProbeSelected.isEmpty()) query.setParameter("probeId",probeId);
        query.setParameter("statRange",statRange>=1? statRange: 10);
        List resultList = query.getResultList();
        List<ActivenessVo> activenessVos = new LinkedList<>();
        for (Object object: resultList) {
            activenessVos.add((ActivenessVo) ObjectMapper.arrayToObject(ActivenessVo.class,object));
        }
        return activenessVos;
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
        Timestamp timestamp = new Timestamp(hour*1000*60*60);
        return activenessRepository.findByHourAndWifiProb(timestamp,probeId);
    }

    @Override
    public ActivenessEntity findById(int id) {
        return activenessRepository.findOne(id);
    }

    @Override
    public ActivenessEntity save(ActivenessEntity entity) {
        return activenessRepository.save(entity);
    }
}
