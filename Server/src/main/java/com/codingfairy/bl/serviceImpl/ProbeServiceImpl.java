package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.service.ProbeService;
import com.codingfairy.bl.vo.ProbeVo;
import com.codingfairy.data.dao.ProbeDao;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by cuihao on 2017-05-15.
 * probe service impl
 */
@Service
public class ProbeServiceImpl implements ProbeService {

    @Override
    public Page<ProbeVo> findAll(int offset, int size) {
        return null;
    }

    @Override
    public ProbeVo findById(int id) {
        return null;
    }
}
