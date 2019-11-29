package cn.nianzuochen.getajob.mapper;


import cn.nianzuochen.getajob.domain.dao.RoleInfo;

public interface RoleInfoMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(RoleInfo record);

    int insertSelective(RoleInfo record);

    RoleInfo selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(RoleInfo record);

    int updateByPrimaryKey(RoleInfo record);
}