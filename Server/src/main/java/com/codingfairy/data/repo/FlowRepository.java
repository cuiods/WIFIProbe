package com.codingfairy.data.repo;

import com.codingfairy.data.entity.FlowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

/**
 * Created by cuihao on 2017-05-16.
 * Flow repository
 */
public interface FlowRepository extends JpaRepository<FlowEntity,Integer>{
    FlowEntity findByHourAndWifiProb(Timestamp hour, String wifiProb);
}
