package cn.nianzuochen.getajob.mapper;


import cn.nianzuochen.getajob.domain.dao.WorkExp;

public interface WorkExpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkExp record);

    int insertSelective(WorkExp record);

    WorkExp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkExp record);

    int updateByPrimaryKey(WorkExp record);
}