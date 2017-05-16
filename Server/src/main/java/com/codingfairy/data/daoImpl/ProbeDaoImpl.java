package com.codingfairy.data.daoImpl;

import com.codingfairy.data.dao.ProbeDao;
import com.codingfairy.data.entity.ProbeEntity;
import com.codingfairy.data.repo.ProbeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by cuihao on 2017-05-15.
 * probe dao impl
 */
@Repository
public class ProbeDaoImpl implements ProbeDao {

    @Resource
    private ProbeRepository probeRepository;

    @Override
    public Page<ProbeEntity> findAll(int page, int size) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(page,size,sort);
        return probeRepository.findAll(pageable);
    }

    @Override
    public ProbeEntity findById(int id) {
        return probeRepository.findOne(id);
    }
}
