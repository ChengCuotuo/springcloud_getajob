package cn.nianzuochen.getajob.service.impl;

import cn.nianzuochen.getajob.domain.dao.Position;
import cn.nianzuochen.getajob.mapper.PositionMapper;
import cn.nianzuochen.getajob.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    PositionMapper positionMapper;

    @Override
    public Position selectByPrimaryKey(Integer id) {
        return positionMapper.selectByPrimaryKey(id);
    }
}
