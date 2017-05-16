package com.codingfairy.data.repo;

import com.codingfairy.data.entity.VisitCircleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

/**
 * Created by cuihao on 2017-05-16.
 * visit circle repository
 */
public interface VisitingCircleRepository extends JpaRepository<VisitCircleEntity,Integer> {
    VisitCircleEntity findByHourAndWifiProb(Timestamp hour, String wifiProb);
}
