package com.codingfairy.data.daoImpl;

import com.codingfairy.bl.vo.StoreHoursVo;
import com.codingfairy.data.dao.StoreHoursDao;
import com.codingfairy.data.entity.StoreHoursEntity;
import com.codingfairy.data.repo.StoreHoursRepository;
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
 * store hours dao impl
 */
@Repository
public class StoreHoursDaoImpl implements StoreHoursDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private StoreHoursRepository storeHoursRepository;

    /**
     * Customer inStore hours statistic method
     *
     * @param startHour start hour
     * @param threshold {@link QueryThreshold} of query
     *                  sum value of threshold hours
     * @param statRange range <em>THRESHOLD</em> number of statistic(NOT hour number)
     * @param probeId   id of probe device
     * @return list of {@link StoreHoursVo} with size equals to statRange
     */
    @Override
    public List<StoreHoursVo> getStoreHoursStat(int startHour, QueryThreshold threshold, int statRange, String probeId) {
        String isProbeSelected = probeId==null || probeId.isEmpty()? "": "AND wifiProb = :probeId ";
        String sqlQuery = "SELECT wifiProb,DATE_FORMAT(hour,:dateFormat), " +
                "sum(data0),sum(data1),sum(data2),sum(data3),sum(data4),sum(data5),sum(data6),sum(data7),sum(data8),sum(data9) " +
                "FROM store_hours " +
                "WHERE UNIX_TIMESTAMP(hour) >= (:startHour*3600) " + isProbeSelected+
                " GROUP BY wifiProb,DATE_FORMAT(hour,:dateFormat) " +
                "LIMIT 0,:statRange";
        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("dateFormat", ThresholdUtil.convertToString(threshold));
        query.setParameter("startHour",startHour);
        if (!isProbeSelected.isEmpty()) query.setParameter("probeId",probeId);
        query.setParameter("statRange",statRange>=1? statRange: 10);
        List resultList = query.getResultList();
        List<StoreHoursVo> newOldVos = new LinkedList<>();
        for (Object object: resultList) {
            newOldVos.add((StoreHoursVo) ObjectMapper.arrayToObject(StoreHoursVo.class,object));
        }
        return newOldVos;
    }

    /**
     * find stat results by hour and probe id
     *
     * @param hour    hour
     * @param probeId probe id
     * @return {@link StoreHoursEntity}
     */
    @Override
    public StoreHoursEntity findByHourAndProbe(int hour, String probeId) {
        Timestamp timestamp = new Timestamp(((long) hour)*3600*1000);
        return storeHoursRepository.findByHourAndWifiProb(timestamp,probeId);
    }

    @Override
    public StoreHoursEntity findById(int id) {
        return storeHoursRepository.findOne(id);
    }

    @Override
    public StoreHoursEntity save(StoreHoursEntity entity) {
        return storeHoursRepository.save(entity);
    }
}
