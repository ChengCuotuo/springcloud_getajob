package cn.nianzuochen.getajob.service;


import cn.nianzuochen.getajob.domain.dao.Position;

public interface PositionService {
    Position selectByPrimaryKey(Integer id);
}
