package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.vo.request.OperateLogReq;
import cn.nianzuochen.getajob.vo.response.OperateLogResp;
import cn.nianzuochen.getajob.domain.dao.OperateLog;


public interface OperateLogService {

    public int insert(OperateLog record);

    /**
     * 根据操作日志的 id 删除操作日志
     * @param operateid
     * @return
     */
    int deleteByPrimaryKey(Integer operateid);

    /**
     * 查询所有的操作日志
     * @return
     */
    OperateLogResp selectAllOperateLog(OperateLogReq operateLogReq);
}
