package cn.nianzuochen.getajob.mapper;


import cn.nianzuochen.getajob.vo.request.OperateLogReq;
import cn.nianzuochen.getajob.domain.dao.OperateLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OperatelogMapper {

    int deleteByPrimaryKey(Integer operateid);

    int insert(OperateLog record);

    int insertSelective(OperateLog record);

    OperateLog selectByPrimaryKey(Integer operateid);

    int updateByPrimaryKeySelective(OperateLog record);

    int updateByPrimaryKey(OperateLog record);

    List<OperateLog> selectAllOperateLog(@Param(value = "operateLogReq") OperateLogReq operateLogReq);

    int countSelectOperateLogs(@Param(value = "operateLogReq") OperateLogReq operateLogReq);
}