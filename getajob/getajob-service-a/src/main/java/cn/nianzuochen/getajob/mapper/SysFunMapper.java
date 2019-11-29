package cn.nianzuochen.getajob.mapper;


import cn.nianzuochen.getajob.domain.dao.SysFun;

public interface SysFunMapper {
    int deleteByPrimaryKey(Integer nodeid);

    int insert(SysFun record);

    int insertSelective(SysFun record);

    SysFun selectByPrimaryKey(Integer nodeid);

    int updateByPrimaryKeySelective(SysFun record);

    int updateByPrimaryKey(SysFun record);

    SysFun selectByRoleId(Integer roleid);
}