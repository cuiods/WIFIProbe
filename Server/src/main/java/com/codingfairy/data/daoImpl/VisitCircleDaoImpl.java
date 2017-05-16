package com.codingfairy.data.daoImpl;

import com.codingfairy.bl.vo.VisitCircleVo;
import com.codingfairy.data.dao.VisitCircleDao;
import com.codingfairy.data.entity.VisitCircleEntity;
import com.codingfairy.data.repo.VisitingCircleRepository;
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
 * visit circle dao impl
 */
@Repository
public class VisitCircleDaoImpl implements VisitCircleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private VisitingCircleRepository visitingCircleRepository;
    /**
     * Customer visiting circle statistic method
     *
     * @param startHour start hour
     * @param threshold {@link QueryThreshold} of query
     *                  sum value of threshold hours
     * @param statRange range <em>THRESHOLD</em> number of statistic(NOT hour number)
     * @param probeId   id of probe device
     * @return list of {@link VisitCircleVo} with size equals to statRange
     */
    @Override
    public List<VisitCircleVo> getVisitCircleStat(int startHour, QueryThreshold threshold, int statRange, String probeId) {
        String isProbeSelected = probeId==null || probeId.isEmpty()? "": "AND wifiProb = :probeId ";
        String sqlQuery = "SELECT id,wifiProb,DATE_FORMAT(hour,:dateFormat), " +
                "sum(data0),sum(data1),sum(data2),sum(data3),sum(data4),sum(data5),sum(data6),sum(data7),sum(data8),sum(data9) " +
                "FROM store_hours " +
                "WHERE TO_SECONDS(hour) >= (:startHour*3600) " + isProbeSelected+
                " GROUP BY id,wifiProb,DATE_FORMAT(hour,:dateFormat) " +
                "LIMIT 0,:statRange";
        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("dateFormat", ThresholdUtil.convertToString(threshold));
        query.setParameter("startHour",startHour);
        if (!isProbeSelected.isEmpty()) query.setParameter("probeId",probeId);
        query.setParameter("statRange",statRange>=1? statRange: 10);
        List resultList = query.getResultList();
        List<VisitCircleVo> newOldVos = new LinkedList<>();
        for (Object object: resultList) {
            newOldVos.add((VisitCircleVo) ObjectMapper.arrayToObject(VisitCircleVo.class,object));
        }
        return newOldVos;
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
        Timestamp timestamp = new Timestamp(hour*3600*1000);
        return visitingCircleRepository.findByHourAndWifiProb(timestamp,probeId);
    }

    @Override
    public VisitCircleEntity findById(int id) {
        return visitingCircleRepository.findOne(id);
    }

    @Override
    public VisitCircleEntity save(VisitCircleEntity entity) {
        return visitingCircleRepository.save(entity);
    }
}
