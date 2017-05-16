package com.codingfairy.data.daoImpl;

import com.codingfairy.bl.vo.FlowVo;
import com.codingfairy.data.dao.FlowDao;
import com.codingfairy.data.entity.FlowEntity;
import com.codingfairy.data.repo.FlowRepository;
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
 * flow dao impl
 */
@Repository
public class FlowDaoImpl implements FlowDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private FlowRepository flowRepository;

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
        String isProbeSelected = probeId==null || probeId.isEmpty()? "": "AND wifiProb = :probeId ";
        String sqlQuery = "SELECT id,wifiProb,DATE_FORMAT(hour,:dateFormat),sum(inNoOutWifi), " +
                "sum(inNoOutStore),sum(outNoInWifi),sum(outNoInStore),sum(inAndOutWifi),sum(intAndOutStore), " +
                "sum(stayInWifi), sum(stayInStore), avg(jumpRate), avg(deepVisit), avg(inStoreRate)" +
                "FROM flow " +
                "WHERE TO_SECONDS(hour) >= (:startHour*3600) " + isProbeSelected+
                " GROUP BY id,wifiProb,DATE_FORMAT(hour,:dateFormat) " +
                "LIMIT 0,:statRange";
        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("dateFormat", ThresholdUtil.convertToString(threshold));
        query.setParameter("startHour",startHour);
        if (!isProbeSelected.isEmpty()) query.setParameter("probeId",probeId);
        query.setParameter("statRange",statRange>=1? statRange: 10);
        List resultList = query.getResultList();
        List<FlowVo> flowVos = new LinkedList<>();
        for (Object object: resultList) {
            flowVos.add((FlowVo) ObjectMapper.arrayToObject(FlowVo.class,object));
        }
        return flowVos;
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
        Timestamp timestamp = new Timestamp(hour*1000*3600);
        return flowRepository.findByHourAndWifiProb(timestamp,probeId);
    }

    @Override
    public FlowEntity findById(int id) {
        return flowRepository.findOne(id);
    }

    @Override
    public FlowEntity save(FlowEntity entity) {
        return flowRepository.save(entity);
    }
}
