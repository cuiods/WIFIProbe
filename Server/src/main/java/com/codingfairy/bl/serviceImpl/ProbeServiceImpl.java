package com.codingfairy.bl.serviceImpl;

import com.codingfairy.bl.service.ProbeService;
import com.codingfairy.bl.vo.ProbeVo;
import com.codingfairy.data.dao.ProbeDao;
import com.codingfairy.data.entity.ProbeEntity;
import com.codingfairy.exception.ServerException;
import com.codingfairy.utils.constant.ServerCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.stream.Collectors;

/**
 * Created by cuihao on 2017-05-15.
 * probe service impl
 */
@Service
public class ProbeServiceImpl implements ProbeService {

    @Resource
    private ProbeDao probeDao;

    @Override
    public Page<ProbeVo> findAll(int page, int size) {
        Page<ProbeEntity> probeEntities = probeDao.findAll(page, size);
        return new PageImpl<>(probeEntities.getContent().stream().map(ProbeVo::new).collect(Collectors.toList()),
                probeEntities.nextPageable(),probeEntities.getTotalElements());
    }

    @Override
    public ProbeVo findById(int id) throws ServerException {
        ProbeEntity probeEntity = probeDao.findById(id);
        if (probeEntity==null) throw new ServerException(ServerCode.NOT_FOUND);
        return new ProbeVo(probeEntity);
    }
}
