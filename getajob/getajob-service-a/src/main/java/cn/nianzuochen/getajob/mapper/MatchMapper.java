package cn.nianzuochen.getajob.mapper;


import cn.nianzuochen.getajob.domain.dao.Match;

public interface MatchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Match record);

    int insertSelective(Match record);

    Match selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Match record);

    int updateByPrimaryKey(Match record);
}