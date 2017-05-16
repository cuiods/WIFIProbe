package com.codingfairy.data.dao;

import com.codingfairy.data.entity.ProbeEntity;
import org.springframework.data.domain.Page;

/**
 * Created by cuihao on 2017-05-15.
 * probe dao
 */
public interface ProbeDao {

    Page<ProbeEntity> findAll(int page, int size);

    ProbeEntity findById(int id);
}
