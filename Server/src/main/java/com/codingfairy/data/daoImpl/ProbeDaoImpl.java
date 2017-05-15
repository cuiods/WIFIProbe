package com.codingfairy.data.daoImpl;

import com.codingfairy.data.dao.ProbeDao;
import com.codingfairy.data.entity.ProbeEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

/**
 * Created by cuihao on 2017-05-15.
 * probe dao impl
 */
@Repository
public class ProbeDaoImpl implements ProbeDao {
    @Override
    public Page<ProbeEntity> findAll(int offset, int size) {
        return null;
    }

    @Override
    public ProbeEntity findById(int id) {
        return null;
    }
}
