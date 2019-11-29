package cn.nianzuochen.getajob.mapper;


import cn.nianzuochen.getajob.domain.dao.RoleRight;

public interface RoleRightMapper {
    int deleteByPrimaryKey(Integer rolerightid);

    int insert(RoleRight record);

    int insertSelective(RoleRight record);

    RoleRight selectByPrimaryKey(Integer rolerightid);

    int updateByPrimaryKeySelective(RoleRight record);

    int updateByPrimaryKey(RoleRight record);
}