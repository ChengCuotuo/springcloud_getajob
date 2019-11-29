package cn.nianzuochen.getajob.mapper;


import cn.nianzuochen.getajob.domain.dao.Recom;

public interface RecomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Recom record);

    int insertSelective(Recom record);

    Recom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Recom record);

    int updateByPrimaryKey(Recom record);
}