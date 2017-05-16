package com.codingfairy.data.repo;

import com.codingfairy.data.entity.ProbeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by cuihao on 2017-05-16.
 * probe repository
 */
public interface ProbeRepository extends JpaRepository<ProbeEntity,Integer>{
}
