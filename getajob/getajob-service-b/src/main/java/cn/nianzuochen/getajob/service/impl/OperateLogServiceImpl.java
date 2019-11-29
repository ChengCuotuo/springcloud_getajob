package cn.nianzuochen.getajob.service.impl;


import cn.nianzuochen.getajob.domain.dao.OperateLog;
import cn.nianzuochen.getajob.mapper.OperatelogMapper;
import cn.nianzuochen.getajob.service.OperateLogService;
import cn.nianzuochen.getajob.utils.TimeFrame;
import cn.nianzuochen.getajob.vo.request.OperateLogReq;
import cn.nianzuochen.getajob.vo.response.OperateLogResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    OperatelogMapper operatelogMapper;

    /**
     * 添加操作日志
     * @param record
     * @return
     */
    @Override
    public int insert(OperateLog record) {
        return  operatelogMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer operateid) {
        return operatelogMapper.deleteByPrimaryKey(operateid);
    }

    @Override
    public OperateLogResp selectAllOperateLog(OperateLogReq operateLogReq) {
        OperateLogResp operateLogResp = new OperateLogResp();
        Integer choose = operateLogReq.getLimit();
        if (choose != null) {
            Date now = new Date();
            if (choose == 1) {
                operateLogReq.setStartDate(TimeFrame.startOfDay(now));
                operateLogReq.setEndDate(TimeFrame.endOfDay(now));
            } else if (choose == 2) {
                operateLogReq.setStartDate(TimeFrame.firstDateOfWeek(now));
                operateLogReq.setEndDate(TimeFrame.lastDateOfWeek(now));
            } else if (choose == 3) {
                operateLogReq.setStartDate(TimeFrame.firstDateOfWonth(now));
                operateLogReq.setEndDate(TimeFrame.lastDateOfMonth(now));
            }
        }
        List<OperateLog> operateLogList = operatelogMapper.selectAllOperateLog(operateLogReq);
        operateLogResp.setOperateLogList(operateLogList);
        operateLogResp.setTotalCount(operatelogMapper.countSelectOperateLogs(operateLogReq));

        return operateLogResp;
    }
}
