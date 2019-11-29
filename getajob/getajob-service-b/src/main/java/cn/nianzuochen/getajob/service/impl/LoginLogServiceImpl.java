package cn.nianzuochen.getajob.service.impl;

import cn.nianzuochen.getajob.domain.dao.LoginLog;
import cn.nianzuochen.getajob.mapper.LoginLogMapper;
import cn.nianzuochen.getajob.service.LoginLogService;
import cn.nianzuochen.getajob.utils.TimeFrame;
import cn.nianzuochen.getajob.vo.request.LoadLoginLogReq;
import cn.nianzuochen.getajob.vo.response.LoginLogResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LoginLogServiceImpl implements LoginLogService {

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    LoginLogMapper loginLogMapper;


    @Override
    public int insertSelective(LoginLog record) {
        return loginLogMapper.insertSelective(record);
    }

    /**
     * 根据前台传递的 LoadLoginLog 对象中对于选择时间的限定
     * （0 按照选中的时间；1 选中当天；2 选中本周；3 选中本月；）
     * @param loadLoginLog
     * @return
     */
    @Override
    public LoginLogResp selectLoginLog(LoadLoginLogReq loadLoginLog) {
        Integer choose = loadLoginLog.getLimit();

//        System.out.println(loadLoginLog);

        if (choose != null && choose != 0) {
            Date now = new Date();
            if (choose == 1) {
                loadLoginLog.setStartDate(TimeFrame.startOfDay(now));
                loadLoginLog.setEndDate(TimeFrame.endOfDay(now));
            } else if (choose == 2) {
                loadLoginLog.setStartDate(TimeFrame.firstDateOfWeek(now));
                loadLoginLog.setEndDate(TimeFrame.lastDateOfWeek(now));
            } else if (choose == 3) {
                loadLoginLog.setStartDate(TimeFrame.firstDateOfWonth(now));
                loadLoginLog.setEndDate(TimeFrame.lastDateOfMonth(now));
            }
        }

//        System.out.println(loadLoginLog.getStartDate() + " " + loadLoginLog.getEndDate());

        List<LoginLog> loginLogs = loginLogMapper.selectLoginLog(loadLoginLog);

        LoginLogResp loginLogResp = new LoginLogResp();
        loginLogResp.setLoginLogList(loginLogs);
        loginLogResp.setTotalCount(loginLogMapper.countLoginLog(loadLoginLog));
        return loginLogResp;
    }

    @Override
    public int deleteByPrimaryKey(Integer loginid) {
        return  loginLogMapper.deleteByPrimaryKey(loginid);
    }
}
