package cn.nianzuochen.getajob.mapper;

import cn.nianzuochen.getajob.domain.dao.Honor;

public interface HonorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Honor record);

    int insertSelective(Honor record);

    Honor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Honor record);

    int updateByPrimaryKey(Honor record);
}