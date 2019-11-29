package cn.nianzuochen.getajob.mapper;

import cn.nianzuochen.getajob.domain.dao.LoginLog;
import cn.nianzuochen.getajob.vo.request.LoadLoginLogReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoginLogMapper {

    int deleteByPrimaryKey(Integer loginid);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(Integer loginid);

    /**
     * 使用 java.utils.Date 时间格式是 年月日
     * @return
     */
    List<LoginLog> selectLoginLog(@Param("loadLoginLog") LoadLoginLogReq loadLoginLogReq);

    Integer countLoginLog(@Param("loadLoginLog") LoadLoginLogReq loadLoginLogReq);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);

}