package cn.nianzuochen.getajob.service.impl;

import cn.nianzuochen.getajob.domain.dao.UserInfo;
import cn.nianzuochen.getajob.mapper.UserInfoMapper;
import cn.nianzuochen.getajob.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public UserInfo selectByPrimaryKey(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserInfo selectByUsername(String username) {
        return userInfoMapper.selectUserByUsername(username);
    }
}
