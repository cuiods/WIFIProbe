package com.codingfairy.data.repo;

import com.codingfairy.data.entity.StoreHoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

/**
 * Created by cuihao on 2017-05-16.
 * in store hours repository
 */
public interface StoreHoursRepository extends JpaRepository<StoreHoursEntity,Integer> {
    StoreHoursEntity findByHourAndWifiProb(Timestamp hour, String wifiProb);
}
