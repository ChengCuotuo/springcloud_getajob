package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.domain.dao.UserInfo;

public interface UserInfoService{
    UserInfo selectByPrimaryKey(Integer id);

    UserInfo selectByUsername(String username);
}
