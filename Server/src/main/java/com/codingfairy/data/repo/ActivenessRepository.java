package com.codingfairy.data.repo;

import com.codingfairy.data.entity.ActivenessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

/**
 * Created by cuihao on 2017-05-16.
 * Activeness repository
 */
public interface ActivenessRepository extends JpaRepository<ActivenessEntity,Integer> {
    ActivenessEntity findByHourAndWifiProb(Timestamp hour, String wifiProb);
}
