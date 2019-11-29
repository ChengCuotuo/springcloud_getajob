package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.domain.dao.LoginLog;
import cn.nianzuochen.getajob.vo.request.LoadLoginLogReq;
import cn.nianzuochen.getajob.vo.response.LoginLogResp;

/**
 * @author: chunlei.wang
 * @date: 2019/08/21
 */
public interface LoginLogService {

    public int insertSelective(LoginLog record);

    /**
     * @param loadLoginLog
     * @return
     */
    public LoginLogResp selectLoginLog(LoadLoginLogReq loadLoginLog);

    /**
     * 根据登录日志的 id 删除日志
     * @param loginid
     * @return
     */
    public int deleteByPrimaryKey(Integer loginid);
}
