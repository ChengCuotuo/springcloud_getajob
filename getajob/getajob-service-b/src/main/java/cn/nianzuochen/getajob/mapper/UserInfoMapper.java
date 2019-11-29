package cn.nianzuochen.getajob.mapper;

import cn.nianzuochen.getajob.domain.dao.UserInfo;

public interface UserInfoMapper {

    int deleteByPrimaryKey(Integer userid);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userid);

    UserInfo selectByIdentifier(String identifier);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    UserInfo selectUserByUsername(String username);

}