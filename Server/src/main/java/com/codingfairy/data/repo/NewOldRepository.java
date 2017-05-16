package com.codingfairy.data.repo;

import com.codingfairy.data.entity.NewOldEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

/**
 * Created by cuihao on 2017-05-16.
 * new old data repository
 */
public interface NewOldRepository extends JpaRepository<NewOldEntity,Integer>{
    NewOldEntity findByHourAndWifiProb(Timestamp hour, String wifiProb);
}
