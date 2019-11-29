package cn.nianzuochen.getajob.mapper;


import cn.nianzuochen.getajob.domain.dao.StuExp;

public interface StuExpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StuExp record);

    int insertSelective(StuExp record);

    StuExp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StuExp record);

    int updateByPrimaryKey(StuExp record);
}