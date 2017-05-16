package com.codingfairy.data.daoImpl;

import com.codingfairy.bl.vo.NewOldVo;
import com.codingfairy.data.dao.NewOldDao;
import com.codingfairy.data.entity.NewOldEntity;
import com.codingfairy.data.repo.NewOldRepository;
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
 * new old customer dao impl
 */
@Repository
public class NewOldDaoImpl implements NewOldDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private NewOldRepository newOldRepository;
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
        String isProbeSelected = probeId==null || probeId.isEmpty()? "": "AND wifiProb = :probeId ";
        String sqlQuery = "SELECT id,wifiProb,DATE_FORMAT(hour,:dateFormat),sum(newCustomer),sum(oldCustomer)" +
                "FROM new_old " +
                "WHERE TO_SECONDS(hour) >= (:startHour*3600) " + isProbeSelected+
                " GROUP BY id,wifiProb,DATE_FORMAT(hour,:dateFormat) " +
                "LIMIT 0,:statRange";
        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("dateFormat", ThresholdUtil.convertToString(threshold));
        query.setParameter("startHour",startHour);
        if (!isProbeSelected.isEmpty()) query.setParameter("probeId",probeId);
        query.setParameter("statRange",statRange>=1? statRange: 10);
        List resultList = query.getResultList();
        List<NewOldVo> newOldVos = new LinkedList<>();
        for (Object object: resultList) {
            newOldVos.add((NewOldVo) ObjectMapper.arrayToObject(NewOldVo.class,object));
        }
        return newOldVos;
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
        Timestamp timestamp = new Timestamp(hour*3600*1000);
        return newOldRepository.findByHourAndWifiProb(timestamp, probeId);
    }

    @Override
    public NewOldEntity findById(int id) {
        return newOldRepository.findOne(id);
    }

    @Override
    public NewOldEntity save(NewOldEntity entity) {
        return newOldRepository.save(entity);
    }
}
